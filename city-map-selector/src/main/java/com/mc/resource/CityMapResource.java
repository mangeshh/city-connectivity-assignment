
package com.mc.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.exception.CityConnectivityException;

import io.swagger.annotations.ApiOperation;

@RestController
public class CityMapResource {
	
 	@Autowired
	public PathFinderResourceProxy proxy;
	
	@Value("${service.strategy}")
	public String strategy;
	
	@ApiOperation(value =  "Check city connectivity using [DISJOINT|ADJ_MATRIX|FAST_ADJ_MATRIX]", response = String.class)
	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String isConnected(@RequestParam(name = "origin", required = true) String origin, @RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		System.out.println(">> selected strategy is ["+strategy+"]");
		if(strategy == null) {
			strategy = "DISJOINT";
		}
		switch(strategy) {
			case "DISJOINT":
				return proxy.isConnectedByDisJointSet(origin, destination);
			case "ADJ_MATRIX":
				return proxy.isConnectedByAdjMatrix(origin, destination);
			case "FAST_ADJ_MATRIX": 	
				return proxy.isConnectedByFastAdjMatrix(origin, destination);
			default:
				return proxy.isConnectedByDisJointSet(origin, destination);
		}
 
	}
	
	@RequestMapping(value = "/feign/connected-by-adj-matrix", method = RequestMethod.GET)
	public String isConnectedByAdjMatrix(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		return proxy.isConnectedByAdjMatrix(origin, destination);
	}
	
	@RequestMapping(value = "/feign/connected-by-fast-adj-matrix", method = RequestMethod.GET)
	public String isConnectedByFastAdjMatrix(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		return proxy.isConnectedByFastAdjMatrix(origin, destination);
	}
	
	@RequestMapping(value = "/feign/connected-by-disjoint", method = RequestMethod.GET)
	public String isConnectedByDisJointSet(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		return proxy.isConnectedByDisJointSet(origin, destination);
	}

}
