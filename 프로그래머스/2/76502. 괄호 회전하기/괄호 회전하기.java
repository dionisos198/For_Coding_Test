import java.util.*;
class Solution {
    public int solution(String s) {
        
        int answer = 0;
        
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        
        for(int i = 0;i<s.length();i++){
            sb.append(sb.charAt(0));
            sb.deleteCharAt(0);
       //     System.out.println(sb.toString());
            if(checkAvailable(sb.toString())){
                answer++;
            }
        
        }
        
        return answer;

    }
    
    boolean checkAvailable(String s){
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length();i++){
            if(s.charAt(i)=='[' || s.charAt(i)=='(' || s.charAt(i)=='{'){
                stack.add(s.charAt(i));
            }
            else if(!stack.isEmpty() && stack.peek() =='[' && s.charAt(i)==']'){
                stack.pop();
            }
            else if(!stack.isEmpty() && stack.peek() =='(' && s.charAt(i)==')'){
                stack.pop();
            }
            else if(!stack.isEmpty() && stack.peek() =='{' && s.charAt(i)=='}'){
                stack.pop();
            }
            else{
                return false;
            }
            
        }
        
        if(stack.isEmpty()) return true;
        
        return false;
    }
}