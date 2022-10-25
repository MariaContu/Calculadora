public class Calculadora {
    private String expressao;
    private LinkedStack pilha;
    private LinkedStack pilhaAux;

    private double op1, op2;
    private char sinal;


    public Calculadora(String expressao) {
        this.expressao = expressao;
        pilha = new LinkedStack();
        pilhaAux = new LinkedStack();
        imprime();

    }
    public void imprime() {
        System.out.println("--- Inicio expressao");
        System.out.println("Expressao: "+ expressao);
        try {
            //VERIFICA VALIDADE;
            isValida();

            //SE EXPRESSAO FOR VALIDA
            replaceAll();
            //transforma tudo em ()
            fazCalculo();
            System.out.println("Resultado: "+pilha);
            System.out.println("Tamanho maximo da pilha: "+pilha.getCountMax());

        } catch (RuntimeException e) {

        }
        System.out.println("--- Fim expressao");
        System.out.println();
    }

    public String getResult() {
        return pilha.toString();
    }

    public boolean isValida() {
        int cChaves = 0;
        int cColc = 0;
        int cParen = 0;

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            if (c == '{') {
                cChaves++;
            }
            if (c == '}') {
                cChaves--;
                if (cParen>0) throw new ExpressaoErradaException('}');
            }
            if (c == '(') {
                cParen++;
            }
            if (c == ')') {
                cParen--;
            }
            if (c == '[') {
                cColc++;
            }
            if (c == ']') {
                cColc--;
                if (cParen>0) throw new ExpressaoErradaException(']');
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

    public void setSinal(char sinal) {
        switch (sinal)   {
            case '+':
                this.sinal ='+';
                break;
            case '-':
                this.sinal ='-';
                break;
            case '*':
                this.sinal ='*';
                break;
            case '/':
                this.sinal ='/';
                break;
            case '^':
                this.sinal ='^';
                break;
        }

    }
    public void setOp1(double valor1) {
        this.op1 = valor1;
    }

    public void setOp2(double valor2) {
        this.op2 = valor2;
    }

    public String operacoes() {

        switch (sinal) {
            case '+':
                return String.valueOf(op1 + op2);
            case '-':
                return String.valueOf(op1 - op2);
            case '*':
                return String.valueOf(op1 * op2);
            case '/':
                return String.valueOf(op1 / op2);
            case '^':
                return String.valueOf(Math.pow(op1, op2));
        }
        return null;
    }

    public void replaceAll()    {
        this.expressao=expressao.replace('{','(').replace('[','(').replace('}',')').replace(']',')');
    }



    public void fazCalculo() {
        String[] termos = expressao.split(" ");
        for(String s : termos) {
            if(s.equals(")")) {
                try {
                    while(!pilha.top().equals("("))
                        pilhaAux.push(pilha.pop());

                    //elimina o "abre parenteses" excedente
                    pilha.pop();

                    if(pilhaAux.size()<=2) {
                        pilha.clear();
                        throw new MissingNumberException(pilhaAux.toString());

                    }
                    while(pilhaAux.size() > 2) {
                        setOp1(Double.parseDouble(pilhaAux.pop()));
                        setSinal(pilhaAux.pop().charAt(0));
                        setOp2(Double.parseDouble(pilhaAux.pop()));
                        pilhaAux.push(operacoes());
                    }

                    pilha.push(pilhaAux.pop());
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                    break;
                }

            }
            else
                pilha.push(s);
        }

    }
}
