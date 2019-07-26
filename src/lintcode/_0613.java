package lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 613. High Five
public class _0613 {
	class Record {
		public int id, score;
		public Record(int id, int score){
			this.id = id;
			this.score = score;
		}
	}
	
	/**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
	public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> answer = new HashMap<Integer, Double>();
        Map<Integer, PriorityQueue<Integer>> hash = new HashMap<Integer, PriorityQueue<Integer>>();


        for (Record r : results) {
            if (!hash.containsKey(r.id)){
                hash.put(r.id, new PriorityQueue<Integer>());
            }
            PriorityQueue<Integer> pq=hash.get(r.id);
            if (pq.size() < 5) {
                pq.add(r.score);
            } else {
                // check if current score is higher than the lowest score from the top 5
                if (pq.peek() < r.score){ // peak: retrieve the head of the Q but doesn't remove it
                    pq.poll(); // poll: retrieve the head and remove it
                    pq.add(r.score);
                }
            }
        }


        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : hash.entrySet()) {
            int id = entry.getKey();
            PriorityQueue<Integer> scores = entry.getValue();
            double average = 0;
            for (int i = 0; i < 5; ++i)
                average += scores.poll();
            average /= 5.0;
            answer.put(id, average);
        }
        return answer;
    }

}
