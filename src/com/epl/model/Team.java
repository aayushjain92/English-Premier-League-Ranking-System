package com.epl.model;

import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Team implements Comparable<Team> {
	//home team
	private String name;
	
	private int score;
	
	//away teams and home team goals - away team goals
	private Map<String, List> matches;

	public Team(String homeTeam){
		score = 0;
		this.name = homeTeam;
		matches = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public Map<String, List> getMatches() {
		return matches;
	}

	public void setScore(int score) {
		this.score = score;
	}

	
	// override equals and hashCode
    @Override
    public int compareTo(Team team) {
        return (int)(team.getScore() - this.getScore());
    }
	
	
}
