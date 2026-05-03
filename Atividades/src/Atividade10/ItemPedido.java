package Atividade10;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {

        if (quantidade < 1 || quantidade > 100)
            throw new IllegalArgumentException("Quantidade inválida!");

        this.produto = produto;
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return produto.getPreco() * quantidade;
    }

    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
