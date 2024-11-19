import AOC_LeaderBoard.AOCMember;
import AOC_LeaderBoard.LeaderBoard;
import AOC_LeaderBoard.StarData;
import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AOCParser {
    Gson gson = new Gson();

    public Map<String, Integer> getStarsBeforeTime(int unixTime) throws FileNotFoundException {
        InputStreamReader ir = new InputStreamReader(this.getClass().getResourceAsStream("AOC-2023-leaderboard.json"));

        LeaderBoard leaderBoard = gson.fromJson(ir, LeaderBoard.class);

        Map<String, Integer> starsPerPerson = new HashMap<>();

        for(AOCMember member : leaderBoard.members().values()){
            int stars = 0;
            for(Map<Integer, StarData> day : member.completion_day_level().values()){
                for(StarData starData : day.values()){
                    if(starData.get_star_ts() <= unixTime){
                        stars++;
                    }
                }
            }
            starsPerPerson.put(member.name(), stars);
        }
        return starsPerPerson;
    }

    public static void main(String[] args) throws FileNotFoundException {
        AOCParser aocParser = new AOCParser();
        Map<String, Integer> starsBeforeEOY = aocParser.getStarsBeforeTime(1704067199);
        System.out.println(starsBeforeEOY.toString());
    }
}
