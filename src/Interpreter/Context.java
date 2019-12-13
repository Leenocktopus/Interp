package Interpreter;



public class Context {

    public static void main(String...args){
        Lexer lexer = new Lexer();
        String s;
        Parser parser = new Parser();
        while (!(s=Reader.readCommand()).equals("end")){
            parser.setStack(lexer.getTokens(s));
            Writer.writeResult(parser.assignment());
        }
    }
}
