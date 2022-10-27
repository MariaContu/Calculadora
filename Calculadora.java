public class Calculadora {
    private String expressao;
    private LinkedStack pilha;
    private LinkedStack pilhaContas;

    private double primeiroValor, segundoValor;
    private char sinal;


    /*construtor que inicia pilha e pilha auxiliar, além de já imprimir a validade da
    * expressao e, caso seja valida, seu resultado e o tamanho maximo atingido pela pilha
    * sao retornados. Caso seja invalida, retornara um erro indicando o caracter erroneo
    */
    public Calculadora(String expressao) {
        this.expressao = expressao;
        pilha = new LinkedStack();
        pilhaContas = new LinkedStack();

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
            if (pilhaContas.size()<2) {
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
    public void setValorEsquerda(double valor1) {
        this.primeiroValor = valor1;
    }

    //altera o segundo valor
    public void setValorDireita(double valorDireita) {
        this.segundoValor = valorDireita;
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
        String[] expressaoDividida = expressao.split(" ");
            for(String s : expressaoDividida) {
                if (!s.equals(")")) {
                    pilha.push(s);
                }
                else {

                    //enquanto a expressão não for fechada por parenteses
                    while(!pilha.top().equals("("))
                        pilhaContas.push(pilha.pop());

                    //elimina o parenteses que abre a conta
                    pilha.pop();

                    //excessao de valor faltando
                    if(pilhaContas.size()<=2) {
                        pilha.clear();
                        throw new MissingNumberException(pilhaContas.toString());
                    }

                    //enquanto a pilha de contas tiver ao menos 3 elementos, faz a conta
                    while(pilhaContas.size() > 2) {
                        setValorEsquerda(Double.parseDouble(pilhaContas.pop()));
                        setSinal(pilhaContas.pop().charAt(0));
                        setValorDireita(Double.parseDouble(pilhaContas.pop()));
                        pilhaContas.push(operacoes());
                    }

                    //insere resultado na pilha original
                    pilha.push(pilhaContas.pop());


            }

        }

    }
}
