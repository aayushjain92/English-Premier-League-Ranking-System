package com.epl.model;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Team {
	//home team
	private String homeTeam;
	
	private int score;
	
	//away teams and home team goals - away team goals
	private Map<String, List> matches;

	public Team(String homeTeam){
		score = 0;
		this.homeTeam = homeTeam;
		matches = new HashMap<>();
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public int getScore() {
		return score;
	}

	public Map<String, List> getMatches() {
		return matches;
	}

	
	
	
	
}
