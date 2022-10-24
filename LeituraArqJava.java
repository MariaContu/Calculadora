import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

/**
 *
 * @author Isabel H. Manssour
 */
public class LeituraArqJava {

    public static void main(String[] args) {
        BufferedReader reader;
        Calculadora calc = new Calculadora();
        Path path1 = Paths.get("expressoes2.txt");
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while( (line = reader.readLine()) != null) {
                calc = new Calculadora(line);
                System.out.println("--- Inicio expressao");
                System.out.println("Expressao: "+ calc.getExpressao());
                //VERIFICA VALIDADE;
               // System.out.println(calc.isValida());

                //SE EXPRESSAO FOR VALIDA
                calc.replaceAll();

                String v[] = line.split(" "); // divide a string pelo espaco em branco
                for(String s : v) {
//APLICAR CALCULADORA

                }
                System.out.println("--- Fim expressao");
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
    }
//    public double calculadora(String s)   {
//            Stack<Integer> stack = new Stack<Integer>();
//            int result = 0;
//            int number = 0;
//            int sign = 1;
//            for(int i = 0; i < s.length(); i++){
//                char c = s.charAt(i);
//                if(Character.isDigit(c)){
//                    number = 10 * number + (int)(c - '0');
//                }else if(c == '+'){
//                    result += sign * number;
//                    number = 0;
//                    sign = 1;
//                }else if(c == '-'){
//                    result += sign * number;
//                    number = 0;
//                    sign = -1;
//                }else if(c == '('){
//                    //we push the result first, then sign;
//                    stack.push(result);
//                    stack.push(sign);
//                    //reset the sign and result for the value in the parenthesis
//                    sign = 1;
//                    result = 0;
//                }else if(c == ')'){
//                    result += sign * number;
//                    number = 0;
//                    result *= stack.pop();    //stack.pop() is the sign before the parenthesis
//                    result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
//
//                }
//            }
//            if(number != 0) result += sign * number;
//            return result;
//        }

    //FAZER BASEADO NO EXEMPLO ACIMA
}