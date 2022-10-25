import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Stack;

/**
 *
 * @author Isabel H. Manssour
 */
public class LeituraArqJava {

    public static void main(String[] args) {
        BufferedReader reader;
        Calculadora calc;

        Path path1 = Paths.get("expressoes2.txt");
        try {
            reader = Files.newBufferedReader(path1, Charset.defaultCharset());
            String line = null;
            while( (line = reader.readLine()) != null) {
                calc = new Calculadora(line);
                System.out.println("--- Inicio expressao");
                System.out.println("Expressao: "+ calc.getExpressao());
                try {
                    //VERIFICA VALIDADE;
                    calc.isValida();

                    //SE EXPRESSAO FOR VALIDA
                    calc.replaceAll();
                    //transforma tudo em ()
                    calc.fazTudo();


                    //APLICAR CALCULADORA

                } catch (RuntimeException e) {

                }
                System.out.println("--- Fim expressao");
            }
            reader.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
    }
}