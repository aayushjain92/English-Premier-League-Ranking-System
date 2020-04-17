package com.epl.prediction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.epl.model.League;
import com.epl.services.LoadCSVData;
import java.io.File;



public class RankingSystem {
	private static List<String> currentSeasonDataFiles;
	private static List<String> previousSeasonDataFiles;
	

	public static void main(String[] args) {
		
		 RankingSystem.currentSeasonDataFiles = listFilesForFolder("datasets//current//", new File("datasets//current//"));
		 RankingSystem.previousSeasonDataFiles = listFilesForFolder("datasets//previous//", new File("datasets//previous//"));
		
		League league2019_20 = new League();
		league2019_20.initializeCurrentSeason(currentSeasonDataFiles);
		league2019_20.initializePreviousSeason(previousSeasonDataFiles);
		
		printProbabilityTable(league2019_20);
		
		System.out.println("test");
		
	}
	
	
	private static void printProbabilityTable(League league2019_20) {
		// TODO Auto-generated method stub
		
	}


	public static List<String> listFilesForFolder(String str, final File folder) {
		List<String> list = new LinkedList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            System.out.println("wrong structure");
	        } else {
	            if(fileEntry.getName().contains(".csv"))
	            	list.add(str + fileEntry.getName());
	        }
	    }
	    return list;
	}

	
		
	
}
