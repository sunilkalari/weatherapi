package com.organisation.weather.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class WeatherDto {
	
	@NotEmpty
    private String country;
	
	@NotBlank
    private String city;
    
	@NotBlank
    private ApiKey apiKey;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public ApiKey getApiKey() {
		return apiKey;
	}

	public void setApiKey(ApiKey apiKey) {
		this.apiKey = apiKey;
	}
}
