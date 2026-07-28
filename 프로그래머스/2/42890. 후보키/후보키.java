import java.util.*; 
class Solution {
    
    List<Integer> availableIndexList = new ArrayList<>();
    List<List<Integer>> huboKeyList = new ArrayList<>();
    int ans = 0;
    
    public int solution(String[][] relation) {
        
        for(int i =0;i<relation[0].length;i++){
            availableIndexList.add(i);
        }
        
        for(int i=1;i<=relation[0].length;i++){
            decideUniqueBT(i,new ArrayList<>(), 0,relation);
        }
        return ans;
    }
    
    public boolean deDepHuboKeyList(List<Integer> list){
        
        for(int i=0;i<huboKeyList.size(); i++){
            
            List<Integer> huboKey = huboKeyList.get(i);
            boolean allContain = true;
            for(int j=0;j<huboKey.size();j++){
                if(!list.contains(huboKey.get(j))){
                    allContain = false;
                    break;
                }
            }
            
            if(allContain){
                return false;
            }
        }
        
        
        return true;
    }
    
    public boolean isUnique(String[][]relation, List<Integer> list){
        
        Set<String> set = new HashSet<>();
        for(int i=0;i<relation.length; i++){
            String tmp = "";
            for(int j=0;j<list.size();j++){
                tmp+=relation[i][list.get(j)];
            }
            set.add(tmp);
        }
        
        if(set.size()==relation.length){
            return true;
        }
        
        return false;
    }
    
    
    public void decideUniqueBT(int count,List<Integer> list,int idx,String[][] relation){
        
        if(list.size() == count){
            
            if(deDepHuboKeyList(list) && isUnique(relation,list)){
                ans++;
                List<Integer> tmpList = new ArrayList<>();
                for(int i: list){
                    tmpList.add(i);
                }
                
              //  System.out.println(tmpList);
                
                huboKeyList.add(tmpList);
            }
            return;
        }
        
        for(int i = idx;i<availableIndexList.size(); i++){
            
            list.add(availableIndexList.get(i));
            decideUniqueBT(count, list, i+1,relation);
            list.remove(list.size()-1);
        }
        
    }
}