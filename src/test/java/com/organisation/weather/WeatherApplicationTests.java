package com.organisation.weather;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organisation.weather.dto.WeatherDto;
import com.organisation.weather.dto.WeatherResponseDto;
import com.organisation.weather.service.WeatherService;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes=WhetherApplication.class)
public class WeatherApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private WeatherService weatherService;

    private static ObjectMapper mapper = new ObjectMapper();
    

    
    @Test
    public void getWeather() throws Exception {
    	
    	WeatherDto dto = new WeatherDto();
        dto.setCountry("uk");
        dto.setCity("London");
        String json = mapper.writeValueAsString(dto);
        

        WeatherResponseDto responseDto = new WeatherResponseDto();
        responseDto.setDescription("Cloudy");
        
        Mockito.when(weatherService.getWhetherData(ArgumentMatchers.any()))
        .thenReturn(responseDto);
        
        mockMvc.perform(get("/weather").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(
                        status().isOk())
                .andExpect(jsonPath("$.description", Matchers.equalTo("Cloudy")))
                ;
    }
    

    }
