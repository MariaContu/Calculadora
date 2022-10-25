import java.util.InputMismatchException;

public class ExpressaoErradaException extends InputMismatchException {

    public ExpressaoErradaException(char erro) {
        System.out.println();
        System.out.printf("Erro de sintaxe: %s no lugar errado ", erro);
        System.out.println();
    }
}
