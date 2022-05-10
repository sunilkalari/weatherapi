package com.organisation.weather.domain;

import java.io.Serializable;
import java.util.Objects;


public class WeatherId implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6189660851850382148L;
	

	private String country;
	
    private String city;

    
	public WeatherId() {
		super();
	}

	public WeatherId(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(city, country);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherId other = (WeatherId) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country);
	}

}
