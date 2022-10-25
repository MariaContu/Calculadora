public class MissingNumberException extends RuntimeException {
    public MissingNumberException(String s) {
        super("Numero faltando em " + s);
    }
}
