package com.organisation.weather.dto;

public class WeatherResponseDto {
	
    private String description;

    public WeatherResponseDto() {
		super();
	}

	public WeatherResponseDto(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
