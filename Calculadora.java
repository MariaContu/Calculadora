public class Calculadora {
    private String expressao;
    private LinkedStack pilha;
    private LinkedStack pilhaAux;

    private double op1, op2;
    private char operacao;


    public Calculadora(String expressao) {
        this.expressao = expressao;
        pilha = new LinkedStack();
        pilhaAux = new LinkedStack();

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

    public void setOperacao(char operacao) {
        switch (operacao)   {
            case '+':
                this.operacao='+';
                break;
            case '-':
                this.operacao='-';
                break;
            case '*':
                this.operacao='*';
                break;
            case '/':
                this.operacao='/';
                break;
            case '^':
                this.operacao='^';
                break;
        }

    }
    public void setOperador1(double valor1) {
        this.op1 = valor1;
    }

    public void setOperador2(double valor2) {
        this.op2 = valor2;
    }

    public double calcula() {

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



    public void fazTudo () {
        String[] termos = expressao.split(" ");
        for(String proximoTermo : termos) {
            if(proximoTermo.equals(")")) {
                try {
                    while(!pilha.top().equals("("))
                        pilhaAux.push(pilha.pop());

                    //elimina o "abre parenteses" excedente
                    pilha.pop();

                    if (pilhaAux.size()<=2) {
                        throw new MissingNumberException(pilhaAux.toString());
                    }
                    while(pilhaAux.size() > 2) {
                        setOperador1(Double.parseDouble(pilhaAux.pop()));
                        setOperacao(pilhaAux.pop().charAt(0));
                        setOperador2(Double.parseDouble(pilhaAux.pop()));
                        pilhaAux.push(String.format("%f", calcula()).replace(',','.'));
                    }

                    pilha.push(pilhaAux.pop());
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }

            }
            else
                pilha.push(proximoTermo);
        }
        System.out.println(pilha);

    }
}
