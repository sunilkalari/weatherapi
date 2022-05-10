package com.organisation.weather.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organisation.weather.dto.WeatherDto;
import com.organisation.weather.dto.WeatherResponseDto;
import com.organisation.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController{
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponseDto> get(@RequestBody WeatherDto dto) {
    	HttpStatus status = HttpStatus.OK; 
    	WeatherResponseDto responseDto = null;
    	try {
    		responseDto = weatherService.getWhetherData(dto);
    	}catch (NoSuchElementException e) {
			 status = HttpStatus.NOT_FOUND;
		}
    	if (responseDto == null) {
			status = HttpStatus.NOT_FOUND;
		}
        return new ResponseEntity<>(responseDto, status);
    }

}
