package com.organisation.weather.dao;

import com.organisation.weather.domain.WeatherEntity;
import com.organisation.weather.dto.WeatherDto;

public interface WeatherDao {
	public WeatherEntity getWeatherData(WeatherDto dto);
	public void save(WeatherEntity entity);
}
