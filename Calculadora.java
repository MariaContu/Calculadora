public class Calculadora {
    private String expressao;
    private LinkedStack pilha;
    private LinkedStack pilhaAux;

    private double primeiroValor, segundoValor;
    private char sinal;


    /*construtor que inicia pilha e pilha auxiliar, além de já imprimir a validade da
    * expressao e, caso seja valida, seu resultado e o tamanho maximo atingido pela pilha
    * sao retornados. Caso seja invalida, retornara um erro indicando o caracter erroneo
    */
    public Calculadora(String expressao) {
        this.expressao = expressao;
        pilha = new LinkedStack();
        pilhaAux = new LinkedStack();
        imprime();
    }

    //imprime a expressao, caso valida o resultado e tamanho maximo da pilha, do contrario o erro apresentado
    public void imprime() {
        System.out.println("--- Inicio expressao");
        System.out.println("Expressao: "+ expressao);
        try {
            //VERIFICA VALIDADE;
            isValida();

            //SE EXPRESSAO FOR VALIDA
            //transforma tudo em ()
            replaceAll();
            //apos alterar os caracteres faz os calculos a cada 2 valores.
            fazCalculo();
            //if implementado para evitar impressao em casos de expressao incompleta
            if (pilhaAux.size()<2) {
                System.out.println("Resultado: "+pilha);
                System.out.println("Tamanho maximo da pilha: "+pilha.getCountMax());
            }
        } catch (RuntimeException e) {
        }
        System.out.println("--- Fim expressao");
        System.out.println();
    }

//verifica se todos { [ ( abertos foram fechados corretamente
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

    //altera sinal de cada operacao realizada na expressao
    public void setSinal(char sinal) {
        this.sinal=sinal;
    }

    //altera o primeiro valor
    public void setPrimeiroValor(double valor1) {
        this.primeiroValor = valor1;
    }

    //altera o segundo valor
    public void setSegundoValor(double valor2) {
        this.segundoValor = valor2;
    }

    //retorna em String o resultado de cada suboperacao da expressao
    public String operacoes() {
        switch (sinal) {
            case '+':
                return String.valueOf(primeiroValor + segundoValor);
            case '-':
                return String.valueOf(primeiroValor - segundoValor);
            case '*':
                return String.valueOf(primeiroValor * segundoValor);
            case '/':
                return String.valueOf(primeiroValor / segundoValor);
            case '^':
                return String.valueOf(Math.pow(primeiroValor, segundoValor));
        }
        return null;
    }

    //caso a expressao seja dada como valida, substituira todos os '{', '}', '[' e ']' por '(' ou ')';
    public void replaceAll()    {
        this.expressao=expressao.replace('{','(').replace('[','(').replace('}',')').replace(']',')');
    }


//realiza o calculo completo da expressao
    public void fazCalculo() {
        //algoritmo inicial fornecido pela professora
        String[] termos = expressao.split(" ");
        for(String s : termos) {
            if(s.equals(")")) {
                try {
                    while(!pilha.top().equals("("))
                        pilhaAux.push(pilha.pop());
                    //elimina o parenteses
                    pilha.pop();

                    //excessao de valor faltando
                    if(pilhaAux.size()<=2) {
                        pilha.clear();
                        throw new MissingNumberException(pilhaAux.toString());
                    }

                    while(pilhaAux.size() > 2) {
                        setPrimeiroValor(Double.parseDouble(pilhaAux.pop()));
                        setSinal(pilhaAux.pop().charAt(0));
                        setSegundoValor(Double.parseDouble(pilhaAux.pop()));
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
