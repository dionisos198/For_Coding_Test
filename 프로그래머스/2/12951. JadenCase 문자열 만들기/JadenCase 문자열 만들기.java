import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isFirstCharacter = true;
        
        for(int i=0;i<s.length();i++){
            
            if(s.charAt(i)==' '){
                isFirstCharacter = true;
                sb.append(" ");
            }
            
            else if(isFirstCharacter){
                isFirstCharacter = false;
                sb.append(Character.toUpperCase(s.charAt(i)));
            }

            else{
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        
        return sb.toString();
    }
    
//     public String makeJadenCase(String s){
//         StringBuilder sb = new StringBuilder();
        
//         if(s.charAt(0)>='a' && s.charAt(0)<='z'){
//             sb.append((char)(s.charAt(0)-'a'+'A'));
//             sb.append(s.substring(1,s.length()).toLowerCase());
//         }
//         else{
//             sb.append(s.charAt(0));
//             sb.append(s.substring(1,s.length()).toLowerCase());
//         }
        
        
//         return sb.toString();
        
//     }
}