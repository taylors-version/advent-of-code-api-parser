package AOC_LeaderBoard;

import java.util.Map;

public record AOCMember(Map<Integer, Map<Integer, StarData>> completion_day_level, int global_score, int id,
                        long last_star_ts, int local_score, String name, int stars) {
}
