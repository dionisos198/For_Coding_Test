import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static class Sentence implements Comparable<Sentence> {
        private String word;

        public Sentence(String word) {
            this.word = word;
        }

        @Override
        public int compareTo(Sentence other) {
            int i = 0, j = 0;
            int n1 = this.word.length();
            int n2 = other.word.length();

            while (i < n1 && j < n2) {
                char c1 = this.word.charAt(i);
                char c2 = other.word.charAt(j);
                
                if (isDigit(c1) && isDigit(c2)) {
                    int start1 = i;
                    while (i < n1 && isDigit(this.word.charAt(i))) i++;
                    int start2 = j;
                    while (j < n2 && isDigit(other.word.charAt(j))) j++;

                    String s1 = this.word.substring(start1, i);
                    String s2 = other.word.substring(start2, j);
                    
                    String v1 = removeLeadingZeros(s1);
                    String v2 = removeLeadingZeros(s2);

                    if (v1.length() != v2.length()) return v1.length() - v2.length();
                    if (!v1.equals(v2)) return v1.compareTo(v2);
                    
                    if (s1.length() != s2.length()) return s1.length() - s2.length();
                }
                else if (isDigit(c1)) {
                    return -1;
                } else if (isDigit(c2)) {
                    return 1;
                }
                else {
                    if (c1 != c2) {
                        int p1 = getPriority(c1);
                        int p2 = getPriority(c2);
                        return p1 - p2;
                    }
                    i++;
                    j++;
                }
            }
            
            return (n1 - i) - (n2 - j);
        }

        private boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        private String removeLeadingZeros(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '0') return s.substring(i);
            }
            return "0"; 
        }

        private int getPriority(char c) {
            if (c >= 'A' && c <= 'Z') return (c - 'A') * 2;
            if (c >= 'a' && c <= 'z') return (c - 'a') * 2 + 1;
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Sentence> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Sentence(br.readLine()));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (Sentence s : list) {
            sb.append(s.word).append("\n");
        }
        System.out.print(sb);
    }
}