package com.organisation.weather.dto;

import java.time.LocalDateTime;


public class ApiKeyUtilDto {
	
    private int count;
    
    private LocalDateTime time;
    
    public ApiKeyUtilDto() {
		super();
	}

	public ApiKeyUtilDto(int count, LocalDateTime time) {
		super();
		this.count = count;
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

}
