import java.util.ArrayList;
import java.util.List;

public class Estado {
   private boolean isFinal;
   private boolean isInicial;
   private char name;
   private List<Transicao> transicoes = new ArrayList<Transicao>();

    public List<Transicao> getTransicoes() {
    return transicoes;
}

public void setTransicoes(List<Transicao> transicoes) {
    this.transicoes = transicoes;
}

    public Estado(char name){
        this.isFinal = false;
        this.isInicial = false;
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isInicial() {
        return isInicial;
    }

    public void setInicial(boolean isInicial) {
        this.isInicial = isInicial;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void addTransicao(Transicao transicao){
        this.transicoes.add(transicao);
    }
}
