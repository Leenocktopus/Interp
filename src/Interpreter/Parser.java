package Interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;



public class Parser{

    public final String[] operators = {"cos", "sin","round", "tan", "abs", "log"};
    private Stack <String> stack;
    private Map<String, Object> variables = new HashMap<>();

    public void setStack(Stack<String> stack) {
        this.stack = stack;
    }
    public Stack getStack() {
        return stack;
    }

    public Object assignment() {
        if (stack.peek().matches("[A-za-z]*")){
            String temp = stack.peek();
            stack.pop();
            if (stack.peek().equals("=")){
            stack.pop();
            Object result = expression();
            variables.put(temp, result);
            return result;}
            stack.push(temp);
            return booleanExpression();
        } else {
        return booleanExpression(); }
    }
    private Object booleanExpression(){
        double result = expression();

        if(!stack.empty() && stack.peek().equals("==")){
            stack.pop();
            return result==expression();}
        if(!stack.empty() && stack.peek().equals(">")){
            stack.pop();
            return result>expression();}
        if(!stack.empty() && stack.peek().equals("<")){
            stack.pop();
            return result<expression();}
        if(!stack.empty() && stack.peek().equals("<=")){
            stack.pop();
            return result<=expression();}
        if(!stack.empty() && stack.peek().equals(">=")){
            stack.pop();
            return result>=expression();}
        return result;
    }
    private double expression(){

        double result = term();
        while(!stack.empty() && (stack.peek().equals("+") || stack.peek().equals("-"))){

        if(stack.peek().equals("+"))  {
            stack.pop();
            result += term();
        }else{
            stack.pop();
            result -= term();
        }}
        return result;
    }


    private double term(){
        double result = factor();
        while(!stack.empty() && (stack.peek().equals("*") || stack.peek().equals("/"))){
        if(stack.peek().equals("*")){
            stack.pop();
            result *= number();
        }else{
            stack.pop();
            result /= number();
        }}
        return result;
    }
    private double factor(){
        double result = number();
        while (!stack.empty() && stack.peek().equals("^")){
            stack.pop();
            result = Math.pow(result, number()); }

        return result;
    }
    private double number(){
        if (stack.peek().equals("(")){
            stack.pop();
            double result = expression();
            stack.pop();
            return result;
        } else if(stack.peek().matches("[A-za-z]*")){
            String temp = stack.peek();
            stack.pop();
            return (double) variables.get(temp);
        }
        int sign = (stack.peek().equals("-"))?-1:1;
        if (sign==-1){
            stack.pop();
        }
        double number = Double.parseDouble(stack.peek());
        stack.pop();
        return number*sign;
    }

}