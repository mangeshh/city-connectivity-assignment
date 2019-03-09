package com.mc.cache;

public interface CityMap {
	
	public void put(String key, String value);

	public String isConnectedByAdjMatrix(String origin, String dest);
	
	public String isConnectedByFastAdjMatrix(String origin, String dest);

}