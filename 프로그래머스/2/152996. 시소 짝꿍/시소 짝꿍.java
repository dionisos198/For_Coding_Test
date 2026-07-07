import java.util.*;
class Solution {
    class Duo {
        int one;
        int two;
        public Duo(int one, int two) {
            this.one = one;
            this.two = two;
        }
        public boolean equals(Object o) {
            Duo another = (Duo) o;
            return this.one == another.one && this.two == another.two;
        }
        public int hashCode() {
            return Objects.hash(one, two);
        }
    }

    public long solution(int[] weights) {
        long answer = 0;

        // 몸무게 값과 그것에 대한 사람 수 
        Map<Integer, Long> count = new HashMap<>();
        
        
        for (int weight : weights) {
            // 키가 없으면 1 넣고, 없으면 함수 실행
            count.merge(weight, 1L, Long::sum);
        }

        // 같은 몸무게: cC2
        for (long c : count.values()) {
            answer += c * (c - 1) / 2;
        }

        Set<Integer> set = count.keySet();
        Set<Integer> doubleSet = new HashSet<>();
        Set<Integer> processSet = new HashSet<>();
        Set<Duo> duoSet = new HashSet<>();

        for (int weight : set) {
            for (int k = 2; k <= 4; k++) {
                if (doubleSet.contains(weight * k)) {
                    for (int t = 2; t <= 4; t++) {
                        if (((weight * k) % t == 0) && processSet.contains((weight * k) / t)) {
                            int one = (weight * k) / t;
                            int two = weight;
                            duoSet.add(new Duo(Math.min(one, two), Math.max(one, two)));
                        }
                    }
                }
                doubleSet.add(weight * k);
            }
            processSet.add(weight);
        }


        for (Duo d : duoSet) {
            answer += count.get(d.one) * count.get(d.two);
        }

        return answer;
    }
}