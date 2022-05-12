import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ACP {

    private static List<Estado> estados = new ArrayList<Estado>();
    private static char[] alfabeto = {'a','b'};
    private static Stack<Character> pilha = new Stack<>();
    private static final char inicioPilha = 'Z';
    private static Estado estadoAtual;

    public static Estado searchEstado(List<Estado> estados, char name){

        for(int i = 0; i < estados.size();i++){
            if(estados.get(i).getName() == name){
                return estados.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        char resposta;

        pilha.push(inicioPilha); // Adicionando Z à pilha

        System.out.print("Digite a quantidade de estados: ");
        int numEstados =  scanner.nextInt();

        System.out.print("Digite o caractere do estado INICIAL: ");
        Estado estadoAux = new Estado(scanner.next().charAt(0)); // Criando novo estado para ser adicionado em seguida
        estadoAux.setInicial(true); // Estado inicial
        System.out.print("O estado " + estadoAux.getName() + " é de aceitação? S/N : ");
        resposta = scanner.next().charAt(0);
        if(resposta == 'S'){
            estadoAux.setFinal(true); // Estado de aceitação?
        }else{
            estadoAux.setFinal(false);
        }
        estados.add(estadoAux); // Adicionando estado na lista de estados

        for(int i = 1; i < numEstados;i++){
            System.out.print("Digite o caractere do " + (i+1) + "º estado: ");
            Estado estadoAux2 = new Estado(scanner.next().charAt(0)); // Criando novo estado para ser adicionado em seguida
            estadoAux2.setInicial(false); // Estado inicial
            System.out.print("O estado " + estadoAux2.getName() + " é de aceitação? S/N : ");
            resposta = scanner.next().charAt(0);
            if(resposta == 'S'){
                estadoAux2.setFinal(true); // Estado de aceitação?
            }else{
                estadoAux2.setFinal(false);
            }
            estados.add(estadoAux2); // Adicionando estado na lista de estados
        }

        System.out.print("Estados: -> ");
        for(int i = 0; i< estados.size();i++){
            System.out.print(estados.get(i).getName());
            if(estados.get(i).isFinal()){
                System.out.print("* ");
            }else{
                System.out.print(" ");
            }
        }

        for(int i = 0; i< estados.size();i++){

            System.out.print("\nPara o estado " + estados.get(i).getName() + ", deseja adicionar alguma transição? S/N : ");
            resposta = scanner.next().charAt(0);
            while(resposta == 'S'){
                System.out.print("Estados: -> ");
                for(int j = 0; j< estados.size();j++){
                    System.out.print(estados.get(j).getName());
                    if(estados.get(j).isFinal()){
                        System.out.print("* ");
                    }else{
                        System.out.print(" ");
                    }
                }
                System.out.print("\nPara qual estado será essa transição? (Digite apenas o caractere): ");
                char nomeResposta = scanner.next().charAt(0);
                Transicao transicao = new Transicao(estados.get(i), searchEstado(estados, nomeResposta));

                System.out.print("\nQual elemento de leitura da ENTRADA para essa transição? (" + alfabeto[0]+", "+ alfabeto[1] + ") : ");
                transicao.setItemLido(scanner.next().charAt(0));
                System.out.print("\nQual elemento de leitura da PILHA para essa transição? ('Z' para a primeira transição)");
                transicao.setItemPilha(scanner.next().charAt(0));
                System.out.print("\nQual novo elemento a ser adicionado na pilha? ('e' para manter o atual) ('x' para eliminar o atual) : ");
                transicao.setItemPilhaNovo(scanner.next().charAt(0));

                estados.get(i).addTransicao(transicao);

                System.out.print("\nEstado : " + estados.get(i).getName());
                System.out.print("\nTransições:\n");
                for(int j = 0; j< estados.get(i).getTransicoes().size();j++){
                    System.out.print(estados.get(i).getTransicoes().get(j).getEstadoAtual().getName() + " -> " + estados.get(i).getTransicoes().get(j).getEstadoFim().getName() + " ");
                    System.out.print(estados.get(i).getTransicoes().get(j).getItemLido() + "," + estados.get(i).getTransicoes().get(j).getItemPilha() + "/" + estados.get(i).getTransicoes().get(j).getItemPilhaNovo() + estados.get(i).getTransicoes().get(j).getItemPilha() + "\n");
                }

                
                System.out.print("Deseja adicionar alguma outra transição? S/N : ");
                resposta = scanner.next().charAt(0);
            }
        }


        System.out.println("\n\nAutômato Finalizado!\n");

        // *---------- LENDO ENTRADA ----------*

        resposta = 'S';
        while(resposta == 'S'){
            estadoAtual = estados.get(0);
            System.out.print("Digite a cadeia a ser testada (" + alfabeto[0]+", "+ alfabeto[1] + ") : ");
            String cadeia = scanner.next();

            boolean hasTrans = false;
            for(int i = 0; i< cadeia.length(); i++){
                    hasTrans = false;
                    for(int j=0; j < estadoAtual.getTransicoes().size();j++){
                        if((cadeia.charAt(i) == estadoAtual.getTransicoes().get(j).getItemLido()) && (pilha.peek() == estadoAtual.getTransicoes().get(j).getItemPilha())){
                            hasTrans = true;
                            pilha.push(estadoAtual.getTransicoes().get(j).getItemPilhaNovo());
                            if(pilha.peek() == 'e'){
                                pilha.pop();
                            }
                            if(pilha.peek() == 'x'){
                                pilha.pop();
                                pilha.pop();
                            }
                                estadoAtual = estadoAtual.getTransicoes().get(j).getEstadoFim();
                                break;
                                
                        }
                    }
                    if(hasTrans == false){
                        System.out.println("\n*O autômato morreu no estado " + estadoAtual.getName() + " lendo " + cadeia.charAt(i) + " com " + pilha.peek() + " no topo da pilha." );
                        break;
                    }
            }
            
            // SE O AUTÔMATO NÃO MORRER...
            if(hasTrans == true){
                System.out.println("\nEstado alcançado: " + estadoAtual.getName());
            if(estadoAtual.isFinal()){
                System.out.println("Estado de aceitação alcançado!");
            }else{
                System.out.println("Estado de aceitação não alcançado!");
            }
            }
           
            System.out.print("\nDeseja testar alguma outra cadeia? S/N : ");
            resposta = scanner.next().charAt(0);
            pilha.clear();
            pilha.push(inicioPilha);
        } 
        scanner.close();
    }
}
