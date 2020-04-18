package com.epl.prediction;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epl.model.League;

public class ScoreCalculator {
	

	public void calculateProbability(League league2019_20) {
		// TODO Auto-generated method stub
		Map<String, Map<String, List>> remainingMatches = league2019_20.getRemainingMatches();

		for (String homeTeam : remainingMatches.keySet()) {
			Map<String, List> rivals = remainingMatches.get(homeTeam);
			for (String awayTeam : rivals.keySet()) {
				int winFreq = 0, loseFreq = 0, drawFreq = 0;
				List<Integer> list = rivals.get(awayTeam);

				// calculating the frequency with the same team of winning, losing and drawing a
				// game
				for (Integer goalDifference : list) {
					if (goalDifference > 0) {
						++winFreq;
					} else if (goalDifference == 0) {
						++drawFreq;
					} else if (goalDifference < 0) {
						++loseFreq;
					}
				}

				int data_count = list.size();
				double probability = (double)1 / (double)data_count;
				double probability_win = winFreq * probability;
				double probability_lose = loseFreq * probability;
				double probability_draw = drawFreq * probability;
				
				DecimalFormat df = new DecimalFormat("###.######");

				// setting probabilities
				List<String> new_list = new ArrayList<String>();
				new_list.add(" Home Winning Prob: " + df.format(probability_win));
				new_list.add(" Home Losing Prob: " + df.format(probability_lose));
				new_list.add(" Draw Probability: " + df.format(probability_draw));

				try {
					// setting scores in Teams for later ranking
					if (probability_win > probability_lose && probability_win > probability_draw) {
						league2019_20.getTeamDirectory().get(homeTeam)
								.setScore(league2019_20.getTeamDirectory().get(homeTeam).getScore() + 3);
					} else if ((probability_draw == probability_win && probability_draw == probability_lose)
							|| probability_draw > probability_lose && probability_lose > probability_win) {
						league2019_20.getTeamDirectory().get(homeTeam)
								.setScore(league2019_20.getTeamDirectory().get(homeTeam).getScore() + 1);
						league2019_20.getTeamDirectory().get(awayTeam)
								.setScore(league2019_20.getTeamDirectory().get(awayTeam).getScore() + 1);
					} else if (probability_lose > probability_win && probability_lose > probability_draw) {
//						league2019_20.getTeamDirectory().get(awayTeam)
//								.setScore(league2019_20.getTeamDirectory().get(awayTeam).getScore() + 3);
					}
				}catch(Exception e) {
					//System.out.println(e);
				}

				rivals.replace(awayTeam, new_list);
			}

		}

	}

	public void updateTeamScores(League league2019_20) {
		// TODO Auto-generated method stub

		Map<String, Map<String, List>> remainingMatches = league2019_20.getMatchesPlayed();

		for (String homeTeam : remainingMatches.keySet()) {
			Map<String, List> rivals = remainingMatches.get(homeTeam);
			for (String awayTeam : rivals.keySet()) {
				List<Integer> list = rivals.get(awayTeam);
				for (Integer goalDifference : list) {
					if (goalDifference > 0) {
						league2019_20.getTeamDirectory().get(homeTeam)
								.setScore(league2019_20.getTeamDirectory().get(homeTeam).getScore() + 3);
					} else if (goalDifference == 0) {
						league2019_20.getTeamDirectory().get(homeTeam)
								.setScore(league2019_20.getTeamDirectory().get(homeTeam).getScore() + 1);
						league2019_20.getTeamDirectory().get(awayTeam)
								.setScore(league2019_20.getTeamDirectory().get(awayTeam).getScore() + 1);
					} else if (goalDifference < 0) {
//						league2019_20.getTeamDirectory().get(awayTeam)
//								.setScore(league2019_20.getTeamDirectory().get(awayTeam).getScore() + 3);
					}

				}

			}

		}

	}

}