public class Transicao {
    private  Estado estadoAtual;
    private  Estado estadoFim;
    private  char itemLido;
    private  char itemPilha;
    private  char itemPilhaNovo;

    public Transicao(Estado estadoAtual, Estado estadoFim){
        this.estadoAtual = estadoAtual;
        this.estadoFim = estadoFim;
    }

    public char getItemPilhaNovo() {
        return itemPilhaNovo;
    }

    public void setItemPilhaNovo(char itemPilhaNovo) {
        this.itemPilhaNovo = itemPilhaNovo;
    }

    public Estado getEstadoAtual() {
        return estadoAtual;
    }

    public Estado getEstadoFim() {
        return estadoFim;
    }

    public char getItemLido() {
        return itemLido;
    }
    public void setItemLido(char itemLido) {
        this.itemLido = itemLido;
    }
    public char getItemPilha() {
        return itemPilha;
    }
    public void setItemPilha(char itemPilha) {
        this.itemPilha = itemPilha;
    }
}
