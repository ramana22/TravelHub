package com.travel.model;

import java.util.List;

public class LocationResponse {
    private List<LocationData> data;

	public List<LocationData> getData() {
		return data;
	}

	public void setData(List<LocationData> data) {
		this.data = data;
	}
    
}




class LocationData {
    private String type;
    private String subType;
    private String name;
    private String detailedName;
    private String id;
    private String timeZoneOffset;
    private String iataCode;
    private Address address;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}
	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
    
 
}








