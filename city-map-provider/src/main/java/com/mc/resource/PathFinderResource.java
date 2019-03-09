
package com.mc.resource;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mc.cache.CityConnectivityMap;
import com.mc.cache.DisjointSet;
import com.mc.cache.LRUCache;
import com.mc.exception.CityConnectivityException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.swagger.annotations.ApiOperation;

@RestController
@EnableCircuitBreaker
public class PathFinderResource {


	@Autowired
	public CityConnectivityMap citiConnectivityMap;

	@Autowired
	DisjointSet disjointSet;
	
	@Autowired
	public LRUCache lruCache;
	
	@ApiOperation(value =  "Check connectivity using adj matrix iteration with high density and less memory & tracability", response = String.class)
	@RequestMapping(value = "/connected-by-adj-matrix", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "isConnectedByAdjMatrixFallback", commandProperties = {
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public String isConnectedByAdjMatrix(@RequestParam(name = "origin", required = true) String origin, @RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		String returnResult;
		System.out.println("executed [isConnectedByAdjMatrix]");

		if ((returnResult = lruCache.get(origin, destination)) != null) {
			return returnResult;
		}
		returnResult = citiConnectivityMap.isConnectedByAdjMatrix(origin, destination);
		lruCache.put(origin, destination, returnResult);
		return returnResult;
	}
	
	@ApiOperation(value = "Check connectivity using adj matrix linked list with high density & less memory & tracability.", response = String.class)
	@RequestMapping(value = "/connected-by-fast-adj-matrix", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "isConnectedByFastAdjMatrixFallback", commandProperties = {
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public String isConnectedByFastAdjMatrix(@RequestParam(name = "origin", required = true) String origin, @RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		String returnResult;
		System.out.println("executed [isConnectedByFastAdjMatrix]");
		if ((returnResult = lruCache.get(origin, destination)) != null) {
			return returnResult;
		}
		returnResult = citiConnectivityMap.isConnectedByFastAdjMatrix(origin, destination);
		lruCache.put(origin, destination, returnResult);
		return returnResult;
	}
	
	@ApiOperation(value = "Check connectivity using disjointset.On performance & memory front better than [ DFS/BFS ]", response = String.class)
	@RequestMapping(value = "/connected-by-disjoint", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "isConnectedByDisJointSetFallback", commandProperties = {
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000") })
	public String isConnectedByDisJointSet(@RequestParam(name = "origin", required = true) String origin, @RequestParam(name = "destination", required = true) String destination) throws CityConnectivityException {
		String returnResult;
		System.out.println("executed [isConnectedByDisJointSet]");
		if(disjointSet.findSet(origin) == disjointSet.findSet(destination)){
			returnResult = "yes";
		} else {
			returnResult = "no";
		}
 		return returnResult;
	}

	public String isConnectedByAdjMatrixFallback(String origin, String destination) {
		System.out.println("fallbackMethod:isConnectedByAdjMatrixFallback");
		return "INTERNAL SERVER ERROR";
	}
	
	public String isConnectedByFastAdjMatrixFallback(String origin, String destination) {
		System.out.println("fallbackMethod:isConnectedByFastAdjMatrix");
		return "INTERNAL SERVER ERROR";
	}
	
	public String isConnectedByDisJointSetFallback(String origin, String destination) {
		System.out.println("fallbackMethod:isConnectedByDisJointSet");
		return "INTERNAL SERVER ERROR";
	}

	@RequestMapping("/test")
	public String check() {
		return "UP..";
	}

	@ExceptionHandler(CityConnectivityException.class)
	public void handleCityConnectivityIssues(CityConnectivityException exp, HttpServletResponse response)
			throws IOException {
		System.out.println("handleCityConnectivityIssues is being called!");
		response.sendError(HttpStatus.EXPECTATION_FAILED.value(), exp.getMessage());
	}

}
