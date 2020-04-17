package com.epl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epl.services.LoadCSVData;

public class League {

	private Map<String, Team> teamDirectory;
	
	private Map<String, Map<String, List<Integer>>> matchesPlayed;
	
	private Map<String, Map<String, List<Integer>>> remainingMatches;
	
	//initialize league
	public League(){
		matchesPlayed = new HashMap<>();
		remainingMatches = new HashMap<>();
		
		teamDirectory = new HashMap<>();
		
		// Participating Teams in the year 2019-2020
		String[] CURRENT_TEAMS = {"Liverpool","Brighton","Aston Villa","Norwich",
				"Sheffield United","West Ham","Newcastle","Leicester","Burnley","Tottenham",
				"Bournemouth","Man United","Crystal Palace","Watford","Southampton","Wolves",
				"Arsenal","Chelsea","Everton","Man City"};		
		
		for(String str: CURRENT_TEAMS) {
			addTeam(str);
		}
		populateRemainingMatches(CURRENT_TEAMS, CURRENT_TEAMS);
		
				
	}

	private void addTeam(String str) {
		// TODO Auto-generated method stub
		Team team = new Team(str);
		teamDirectory.put(str, team);
		
	}

	public void populateRemainingMatches(String[] TEAMS1, String[] TEAMS2) {
		
		List<Integer> list = new ArrayList<>();
		for(String team1: TEAMS1) {
			Map<String, List<Integer>> map = new HashMap<>();
			for(String team2: TEAMS2) {
				if(!team1.equals(team2)) {
					map.put(team2, list);
					
				}
			}
			remainingMatches.put(team1, map);
		}
		
		
	}


	public void initializeCurrentSeason(List<String> currentSeasonDataFiles) {
		// TODO Auto-generated method stub
		LoadCSVData load = new LoadCSVData();
		for(String str: currentSeasonDataFiles)
			load.initializeCurrent(this, str);
		
	}
	
	public void initializePreviousSeason(List<String> previousSeasonDataFiles) {
		// TODO Auto-generated method stub
		LoadCSVData load = new LoadCSVData();
		for(String str: previousSeasonDataFiles)
			load.initializePrevious(this, str);
		
	}

	public Map<String, Team> getTeamDirectory() {
		return teamDirectory;
	}



	public Map<String, Map<String, List<Integer>>> getMatchesPlayed() {
		return matchesPlayed;
	}


	public Map<String, Map<String, List<Integer>>> getRemainingMatches() {
		return remainingMatches;
	}


	
	
	
	
	
	
	
}
