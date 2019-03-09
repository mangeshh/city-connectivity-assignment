package com.mc.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 
 * map of node(each node has parent. This is kind of linked list, connected by parent-child relationship)
 * 
 * @author mange
 *
 */

@Component
public class DisjointSet {

    private Map<String, Node> map = new HashMap<>();

    public Map<String, Node> getMap(){
        return map;
    }

    public static class Node{
        public Node(){
            super();
        }

        String city;
        int rank;
        Node parent;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + city +
                    ", rank=" + rank +
                    '}';
        }
    }

    public void makeSet(String city){
    	if(map.get(city)!=null) {
    		return;
    	}
    	
        Node node = new Node();
        node.city = city;
        if(node.parent == null){
            node.parent = node;
        }
        node.rank = 0;
        map.put(node.city, node);
    }

    public void union(String c1, String c2){

        Node parent1 = findSet(map.get(c1));
        Node parent2 = findSet(map.get(c2));
        
        if(parent1 == null || parent2 == null) {
        	return;
        }
        
        if(parent1.rank == parent2.rank) {
            parent1.rank = parent1.rank + 1;
            parent2.parent = parent1;
        }
        if(parent1.rank > parent2.rank){
            parent2.parent = parent1;
        }else{
            parent1.parent = parent2;
        }
    }

    public Node findSet(Node nd){
    	if(nd == null) return null;
        if(nd.parent == nd){
            return nd;
        }
        else{
            return findSet(nd.parent);
        }
    }

    public Node findSet(String city){
        return findSet(map.get(city));
    }

 }
