public class Calculadora {
    private String expressao;
    private LinkedStack pilha = new LinkedStack();
    private LinkedStack valida = new LinkedStack();
    private int op1, op2;
    private char operacao;

    public Calculadora(){}
    public Calculadora(String expressao) {
        this.expressao = expressao;
    }
    public String getExpressao() {
        return expressao;
    }

    // ANALIZAR ISVALIDA!!!!!!!!!!!!!!!!!!!!!!1
    public boolean isValida() {
        for (int i = 0; i < expressao.length(); i++) {
            if (expressao.charAt(i) == '{' || expressao.charAt(i) == '[' || expressao.charAt(i) == '(') {
                valida.push(expressao.charAt(i));
            }
            if (expressao.charAt(i) == '}') {
                if (valida.top() == '{') {
                    valida.pop();
                }
            }
            if (expressao.charAt(i) == ']') {
                if (valida.top() == '[') {
                    valida.pop();
                }
            }
            if (expressao.charAt(i) == ')') {
                if (valida.top() == '(') {
                    valida.pop();
                }
            }
        }
            return valida.isEmpty();
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }

    public void setOperacao(char operacao) {
        switch (operacao)   {
            case '+':
                this.operacao='+';
            case '-':
                this.operacao='-';
            case '*':
                this.operacao='*';
            case '/':
                this.operacao='/';
            case '^':
                this.operacao='^';
        }

    }

    public double calcula(String expressao) {
        switch (operacao) {
            case '+':
                return op1 + op2;
            case '-':
                return op1 - op2;
            case '*':
                return op1 * op2;
            case '/':
                return op1 / op2;
            case '^':
                return Math.pow(op1, op2);
        }
        return 0;
    }

    public void replaceAll()    {
        this.expressao=expressao.replace('{','(').replace('[','(').replace('}',')').replace(']',')');
    }

}
