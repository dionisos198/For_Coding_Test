import java.util.*;
class Solution {
    public String solution(String p) {
        
        return getAnswerByRecur(p);
    }
    
    public String getAnswerByRecur(String w){
        
        if(w.equals("")){
            return "";
        }
        
        if(isCorrect(w)){
            return w;
        }
        
        String []split = seperateToTwoBalanced(w);
        
        String u = split[0];
        String v = split[1];
        
        // System.out.println("u:"+u);
        // System.out.println("v:"+v);
        
        if(isCorrect(u)){
            return u+getAnswerByRecur(v);
        }
        
        return "("+getAnswerByRecur(v)+")"+makeWork(u);
        
        
    }
    
    public String[] seperateToTwoBalanced(String s){
        
        
        Stack<Character> stack = new Stack<>();
        int index = 0;
        
        for(int i=0;i<s.length();i++){
            if((!stack.isEmpty()) && ((stack.peek() =='(' && s.charAt(i)==')') || 
               (stack.peek() ==')' && s.charAt(i)=='('))){
                stack.pop();
            }
            else{
                stack.add(s.charAt(i));
            }
            
            if(stack.isEmpty()){
          //      System.out.println(i);
                index = i;
                break;
            }
        }
        
        return new String[]{s.substring(0,index+1), s.substring(index+1,s.length())};
    }
    
    public boolean isCorrect(String s){
        
        Stack<Character> stack = new Stack<>();
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.add(s.charAt(i));
            }
            
            else{
                if(!stack.isEmpty() && stack.peek()=='('&&s.charAt(i)==')'){
                    stack.pop();
                }
                else{
                 return false;   
                }
            }
        }
        
        if(stack.isEmpty()){
            return true;
        }
        
        return false;
    }
    
    public String makeWork(String s){
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=1;i<s.length()-1;i++){
            if(s.charAt(i)=='('){
                sb.append(")");
            }
            else{
                sb.append("(");
            }
        }
        
        return sb.toString();
    }
}