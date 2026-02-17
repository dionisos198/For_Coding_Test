import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String,List<String>> map = new HashMap<>();
    static HashMap<String,Integer> degreeMap = new HashMap<>();

    static HashMap<String,List<String>> realChild = new HashMap<>();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            String name = st.nextToken();
            map.put(name, new ArrayList<>());
            degreeMap.put(name, 0);
            realChild.put(name, new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            String child = st.nextToken();
            String parent = st.nextToken();

            map.get(parent).add(child);
            degreeMap.put(child,degreeMap.get(child)+1);
        }

        List<String> root = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();

        for(String key: degreeMap.keySet()){
            if(degreeMap.get(key)==0){
                root.add(key);
                queue.add(key);
            }
        }




        while(!queue.isEmpty()){

            String cur = queue.poll();

            for(int i=0;i<map.get(cur).size();i++){
                String child = map.get(cur).get(i);

                degreeMap.put(child, degreeMap.get(child)-1);

                if(degreeMap.get(child)==0){
                    realChild.get(cur).add(child);
                    queue.add(child);
                }

            }
        }

        System.out.println(root.size());
        Collections.sort(root);
        for(int i=0;i<root.size();i++){
            System.out.print(root.get(i)+" ");
        }

        System.out.println();
        List<String> result = new ArrayList<>(realChild.keySet());

        Collections.sort(result);

        for(int i=0;i<result.size();i++){
            String name = result.get(i);
            System.out.print(name+" "+realChild.get(name).size()+" ");
            Collections.sort(realChild.get(name));
            for(int j=0;j< realChild.get(name).size();j++){
                System.out.print(realChild.get(name).get(j)+" ");
            }
            System.out.println();
        }






    }

}
