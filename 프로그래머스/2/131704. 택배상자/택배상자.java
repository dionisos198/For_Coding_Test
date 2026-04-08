import java.util.*;
class Solution {

    Queue<Integer> queue = new LinkedList<>();
    Stack<Integer> stack = new Stack<>();
    
    public int solution(int[] order) {
        
        int count = 0;
        for(int i=0;i<order.length;i++){
            queue.add(i+1);
        }
        
        for(int i=0;i<order.length;i++){
            int current = order[i];
            
            while(!queue.isEmpty() && queue.peek()<current){
                stack.add(queue.poll());
            }
            if(!queue.isEmpty() && queue.peek()==current){
                queue.poll();
                count++;
                continue;
            }
            else{
                if(!stack.isEmpty()&& stack.peek()==current){
                    stack.pop();
                    count++;
                    continue;
                }
                break;
            }
            
            
        }
        
        return count;
    }
}