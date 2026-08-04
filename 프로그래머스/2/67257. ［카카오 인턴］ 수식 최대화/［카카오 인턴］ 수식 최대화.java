import java.util.*;

class Solution {
    long ans = 0;

    public long solution(String expression) {
        orderCalculate(expression);
        return ans;
    }

    public void orderCalculate(String expression){
        calculate(expression, '*', '+', '-');
        calculate(expression, '*', '-', '+');
        calculate(expression, '+', '*', '-');
        calculate(expression, '+', '-', '*');
        calculate(expression, '-', '*', '+');
        calculate(expression, '-', '+', '*');
    }

    public void calculate(String expression, Character c1, Character c2, Character c3){

        Character[] ops = {c1, c2, c3};

        for(Character op : ops){
            while(true){
                boolean stop = true;
                for(int i=0;i<expression.length();i++){
                    if(expression.charAt(i)==op){
                        expression = replaceByCalculate(op, i, expression);
                        stop = false;
                        break;
                    }
                }
                if(stop){
                    break;
                }
            }
        }

        ans = Math.max(Math.abs(Long.parseLong(expression.replace('~','-'))), ans);
    }

    public String replaceByCalculate(Character c, int i, String expression){

        // 왼쪽 피연산자
        int startIndex = i;
        StringBuilder sb1 = new StringBuilder();
        for(int j=i-1;j>=0;j--){
            char ch = expression.charAt(j);
            if(ch>='0' && ch<='9'){
                sb1.insert(0, ch);
                startIndex = j;
                continue;
            }
            if(ch=='~'){                 // 음수 마커면 부호로 붙이고 종료
                sb1.insert(0, '-');
                startIndex = j;
            }
            break;
        }

        // 오른쪽 피연산자
        int endIndex = i;
        StringBuilder sb2 = new StringBuilder();
        for(int j=i+1;j<expression.length();j++){
            char ch = expression.charAt(j);
            if(ch>='0' && ch<='9'){
                sb2.append(ch);
                endIndex = j;
                continue;
            }
            if(ch=='~' && j==i+1){       // 연산자 바로 뒤일 때만 부호
                sb2.append('-');
                endIndex = j;
                continue;
            }
            break;
        }

        long v1 = Long.parseLong(sb1.toString());
        long v2 = Long.parseLong(sb2.toString());

        long v;
        if(c=='*')      v = v1 * v2;
        else if(c=='+') v = v1 + v2;
        else            v = v1 - v2;

        String result = String.valueOf(v).replace("-", "~");

        return expression.substring(0, startIndex) + result +
               expression.substring(endIndex+1, expression.length());
    }
}