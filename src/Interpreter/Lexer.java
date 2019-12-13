package Interpreter;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class Lexer{
    public final char[] operators = {'=','>','<'};
    private boolean isNumber(char c ){
        return c >= '0' && c <= '9';
    }
    private boolean isLetter(char c){
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <='z';
    }
    private boolean isLetterOrNumebr(String s, int i){
        return this.isNumber(s.charAt(i+1)) && this.isNumber(s.charAt(i)) ||
                this.isLetter(s.charAt(i+1)) && this.isLetter(s.charAt(i));
    }

    private boolean isDoubleSign(String s, int i){
        return IntStream.range(0, operators.length).anyMatch(j -> s.charAt(i) == operators[j])  &&
        IntStream.range(0, operators.length).anyMatch(j -> s.charAt(i+1) == operators[j]);
    }

    public Stack<String> getTokens(String s){
        Stack <String>  parseData = new Stack<>();
        int j;
        s = '~'+s;
        StringBuilder temp = new StringBuilder();
        for(int i=s.length()-2; i>=0; i--){
            temp.append(s.charAt(i+1));
            if (isLetterOrNumebr(s, i) || isDoubleSign(s,i)){
                continue;
            }
            parseData.push(temp.reverse().toString());
            temp.setLength(0);
        }
        return parseData;
    }
}
