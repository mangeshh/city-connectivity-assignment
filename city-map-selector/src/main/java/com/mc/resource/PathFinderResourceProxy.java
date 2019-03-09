
package com.mc.resource;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mc.exception.CityConnectivityException;

//@FeignClient(name = "city-map-provider")
@FeignClient(name = "api-gateway")
@RibbonClient(name = "city-map-provider")
public interface PathFinderResourceProxy {

	@RequestMapping(value = "/city-map-provider/connected-by-disjoint", method = RequestMethod.GET)
	public String isConnectedByDisJointSet(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException;
	
	@RequestMapping(value = "/city-map-provider/connected-by-adj-matrix", method = RequestMethod.GET)
	public String isConnectedByAdjMatrix(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException;

	@RequestMapping(value = "/city-map-provider/connected-by-fast-adj-matrix", method = RequestMethod.GET)
	public String isConnectedByFastAdjMatrix(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException ;
	
 
}
