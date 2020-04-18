package com.epl.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epl.model.League;


public class LoadCSVData {
	
	public void initializeCurrent(League league, String path) {
		Path filepath = Paths.get(path);
		try(BufferedReader br = Files.newBufferedReader(filepath,Charset.forName("ISO-8859-1"))){
			String line = br.readLine(); 
			line = br.readLine();
			while(line!=null) {
				String[] line_content = line.split(",");
				//deal with empty lines
				if(line_content.length==0) {
					line = br.readLine();
					continue;
				}
				String home_team = line_content[2];
				String away_team = line_content[3];
				int home_goal = Integer.parseInt(line_content[4]);
				int away_goal = Integer.parseInt(line_content[5]);
				int goalDiff = home_goal-away_goal;
				
				// remove match from the remaining matches
				league.getRemainingMatches().get(home_team).remove(away_team);
				
				
				// add match to matchPlayed in the league instance
				Map<String, List> awayMap =  league.getMatchesPlayed().get(home_team);
				List<Integer> gd;
				if(awayMap == null) {
					Map<String, List> map = new HashMap<>();
					gd = new ArrayList<Integer>();
					gd.add(goalDiff);
					map.put(away_team, gd);
					league.getMatchesPlayed().put(home_team, map);
				}else {
					gd = awayMap.get(away_team);
					if(gd == null) {
						gd = new ArrayList<Integer>();
						gd.add(goalDiff);
						league.getMatchesPlayed().get(home_team).put(away_team, gd);
					}else {
						gd.add(goalDiff);
					}
				}
						
				
//				List<Integer> list; 
//				try {
//					list = league.getMatchesPlayed().get(home_team).get(away_team);
//				}catch(Exception e) {
//					list = new ArrayList<>();
//				}
				
							
//				Map<String, List> map = new HashMap<>();
//				map.put(away_team, list);
//				league.getMatchesPlayed().put(home_team, map);
				
								
				line = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void initializePrevious(League league, String path) {
		Path filepath = Paths.get(path);
		try(BufferedReader br = Files.newBufferedReader(filepath,Charset.forName("ISO-8859-1"))){
			String line = br.readLine(); 
			line = br.readLine();
			while(line!=null) {
				String[] line_content = line.split(",");
				//deal with empty lines
				if(line_content.length==0) {
					line = br.readLine();
					continue;
				}
				String home_team = line_content[2];
				String away_team = line_content[3];
				int home_goal = Integer.parseInt(line_content[4]);
				int away_goal = Integer.parseInt(line_content[5]);
				int goalDiff = home_goal-away_goal;
				
				try {
					league.getRemainingMatches().get(home_team).get(away_team).add(goalDiff);
				}catch(Exception e) {
					//System.out.println("inside catch");
				}
												
				line = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    
}
