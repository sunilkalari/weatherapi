package com.organisation.weather.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.organisation.weather.dao.WeatherDao;
import com.organisation.weather.domain.WeatherEntity;
import com.organisation.weather.dto.ApiKey;
import com.organisation.weather.dto.ApiKeyUtilDto;
import com.organisation.weather.dto.WeatherDto;
import com.organisation.weather.dto.WeatherResponseDto;

@Transactional
@Service
public class WeatherServiceImpl implements WeatherService {
	public static Map<ApiKey,ApiKeyUtilDto> apiKeyDtos = new HashMap<ApiKey,ApiKeyUtilDto>();
	
	@Autowired
    private WeatherDao weatherDao;
	
	public WeatherResponseDto getWhetherData(WeatherDto dto) {
		if(canCallWhetherMap(dto.getApiKey())){
			callWhetherMap(dto); 
		}
		
		return copyToDto(weatherDao.getWeatherData(dto));
	}
	
	private boolean canCallWhetherMap(ApiKey apiKey) {
		ApiKeyUtilDto apiKeyUtilDto = apiKeyDtos.get(apiKey);
		if( apiKeyUtilDto == null ) {
			apiKeyDtos.put(apiKey, new ApiKeyUtilDto(1, LocalDateTime.now()));
			return true;
		} else if(apiKeyUtilDto.getCount() < 5) {
			apiKeyUtilDto.setCount(apiKeyUtilDto.getCount()+1);
			return true;
		} else {
		    if(Duration.between(LocalDateTime.now(), apiKeyUtilDto.getTime()).toHours() < 1) {
		    	return false;
		    }else {
				apiKeyUtilDto.setCount(1);
				return true;
		    }
		}
	}
	
	private void callWhetherMap(WeatherDto dto)  {
		WebClient webClient = WebClient.create("https://samples.openweathermap.org/data/2.5/weather?q="+dto.getCity()+","+dto.getCountry()+"&appid=e27511e28a2b0bb3619045c6177a0e4d");

		String jsonWheter = webClient.get()
				.exchange()
                .block()
                .bodyToMono(String.class)
                .block();
		
		if(jsonWheter != null && jsonWheter.length() > 0 ) {
			ObjectNode node;
			try {
				node = new ObjectMapper().readValue(jsonWheter, ObjectNode.class);
				if (node.has("weather") && node.get("weather").isArray()) {
					JsonNode weatherNodes = node.get("weather");
					boolean newRecord = false;
					for (final JsonNode objNode : weatherNodes) {
						if (objNode.has("description") && objNode.get("description").asText().trim().length() > 0) {
							newRecord = false;
							WeatherEntity entity = weatherDao.getWeatherData(dto);
							if(entity==null) {
								entity = new WeatherEntity();
								newRecord = true;
							}
							entity.setCity(dto.getCity());
							entity.setCountry(dto.getCountry());
							entity.setDescription(objNode.get("description").asText().trim());
							if(newRecord) {
								weatherDao.save(entity);								
							}
						}
					}
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private WeatherResponseDto copyToDto(WeatherEntity entity) {
		
		if(entity == null) {
			return null;
		}
		
		return new WeatherResponseDto(entity.getDescription());
	}
}
