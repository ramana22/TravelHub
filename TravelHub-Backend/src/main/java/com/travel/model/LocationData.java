package com.travel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "location_data")
public class LocationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "detailed_name")
    private String detailedName;

    @Column(name = "iata_code")
    private String iataCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "region_code")
    private String regionCode;

    @Column(name = "time_zone_offset")
    private String timeZoneOffset;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetailedName() {
		return detailedName;
	}

	public void setDetailedName(String detailedName) {
		this.detailedName = detailedName;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}
    
}
