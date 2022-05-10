package com.organisation.weather.service;

import com.organisation.weather.dto.WeatherDto;
import com.organisation.weather.dto.WeatherResponseDto;

public interface WeatherService{
	public WeatherResponseDto getWhetherData(WeatherDto dto);
}
