import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int arr[] = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> consent = new HashSet<>();
        int count =0;

        for(int i=0;i<K;i++){
            if(consent.size()<N){
                consent.add(arr[i]);
            }
            else{

                if(consent.contains(arr[i])){
                    continue;
                }

                count++;

                HashSet<Integer> saved = new HashSet<>();
                int deleteNum = 0;
                for(int j=i+1;j<K;j++){

                    if(!saved.contains(arr[j]) && consent.contains(arr[j])){
                        saved.add(arr[j]);
                        deleteNum = arr[j];
                    }
                }

                if(saved.size()==consent.size()){
                    consent.remove(deleteNum);
                    consent.add(arr[i]);
                }
                else{
                    for(int element: consent){
                        if(!saved.contains(element)){
                            consent.remove(element);
                            consent.add(arr[i]);
                            break;
                        }
                    }
                }
            }
        }

        System.out.println(count);

    }

}
