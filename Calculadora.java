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

    public boolean isValida() {
        int cChaves = 0;
        int cColc = 0;
        int cParen = 0;

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '{') {
                cChaves++;
            }if (c == '}') {
                cChaves--;
            }if (c == '(') {
                cParen++;
            }if (c == ')') {
                cParen--;
            }if (c == '[') {
                cColc++;
            }if (c == ']') {
                cColc--;
            }

        }
            if (cParen>0) throw new ExpressaoErradaException('(');
            if (cParen<0) throw new ExpressaoErradaException('(');
            if (cColc>0) throw new ExpressaoErradaException('[');
            if (cColc<0) throw new ExpressaoErradaException(']');
            if (cChaves>0) throw new ExpressaoErradaException('{');
            if (cChaves<0) throw new ExpressaoErradaException('}');
            return true;
    }

    // ANALIZAR ISVALIDA!!!!!!!!!!!!!!!!!!!!!! SE QUISER USAR PILHA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    public boolean isValida() {
//        for (int i = 0; i < expressao.length(); i++) {
//            if (expressao.charAt(i) == '{' || expressao.charAt(i) == '[' || expressao.charAt(i) == '(') {
//                valida.push(expressao.charAt(i));
//            }
//            if (expressao.charAt(i) == '}') {
//                if (valida.top() == '{') {
//                    valida.pop();
//                }
//            }
//            if (expressao.charAt(i) == ']') {
//                if (valida.top() == '[') {
//                    valida.pop();
//                }
//            }
//            if (expressao.charAt(i) == ')') {
//                if (valida.top() == '(') {
//                    valida.pop();
//                }
//            }
//        }
//            return valida.isEmpty();
//    }

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
