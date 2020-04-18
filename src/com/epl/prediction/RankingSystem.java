package com.epl.prediction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.epl.model.League;
import com.epl.model.Team;
import com.epl.services.LoadCSVData;
import java.util.Collections;

import java.io.File;

public class RankingSystem {
	private static List<String> currentSeasonDataFiles;
	private static List<String> previousSeasonDataFiles;

	public static void main(String[] args) {

		// providing the current data and previous data files to the ranking system
		RankingSystem.currentSeasonDataFiles = listFilesForFolder("datasets//current//",
				new File("datasets//current//"));
		RankingSystem.previousSeasonDataFiles = listFilesForFolder("datasets//previous//",
				new File("datasets//previous//"));

		// Reading the data files
		League league2019_20 = new League();
		league2019_20.initializeCurrentSeason(currentSeasonDataFiles);
		league2019_20.initializePreviousSeason(previousSeasonDataFiles);

		// calculating the scores and predicting the probabilities
		ScoreCalculator scoreCalculator = new ScoreCalculator();
		scoreCalculator.calculateProbability(league2019_20);
		scoreCalculator.updateTeamScores(league2019_20);

		// sorting on the basis of scores
		List<Team> teams = new ArrayList<>(league2019_20.getTeamDirectory().values());
		Collections.sort(teams);
		
		//printing the final predictions on console
		printProbabilityTable(league2019_20);
		printRankingPrediction(teams);

	}

//	private static void printRankingPrediction(League league2019_20) {
//		// TODO Auto-generated method stub
//		System.out.println("\n\n\n\n\n================== RANKING PREDICTION ==================\n");
//		System.out.format("%20s%10s\n", "CLUB", "SCORE");
//		System.out.println("--------------------------------------------");
//		for (String s : league2019_20.getTeamDirectory().keySet()) {
//			Team team = league2019_20.getTeamDirectory().get(s);
//			System.out.format("%20s%10d\n", team.getName(), team.getScore());
//		}
//	}
	
	private static void printRankingPrediction(List<Team> teamDirectory) {
		// TODO Auto-generated method stub
		System.out.println("\n\n\n\n\n================== RANKING PREDICTION ==================\n");
		System.out.format("%20s%10s\n", "CLUB", "SCORE");
		System.out.println("--------------------------------------------");
		for (Team team : teamDirectory) {			
			System.out.format("%20s%10d\n", team.getName(), team.getScore());
		}
	}
	
	
	

	private static void printProbabilityTable(League league2019_20) {
		// TODO Auto-generated method stub
		System.out.println("\n\n\n================== PREDICTION OF ABANDONED MATCHES ==================\n");
		System.out.format("%20s%20s%40s\n", "HOME TEAM", "AWAY TEAM", "PROBABILITY");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------");
		for (String homeTeam : league2019_20.getRemainingMatches().keySet()) {
			Map<String, List> rivals = league2019_20.getRemainingMatches().get(homeTeam);
			for (String awayTeam : rivals.keySet()) {
				List<Integer> list = rivals.get(awayTeam);
				System.out.format("%20s%20s%80s\n", homeTeam, awayTeam, list);
			}
		}

	}

	public static List<String> listFilesForFolder(String str, final File folder) {
		List<String> list = new LinkedList<String>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				System.out.println("wrong structure");
			} else {
				if (fileEntry.getName().contains(".csv"))
					list.add(str + fileEntry.getName());
			}
		}
		return list;
	}

}
