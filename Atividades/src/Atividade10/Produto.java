package Atividade10;

public class Produto {
    private String sku;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String sku, String nome, String categoria, double preco, int estoque) {

        if (!sku.matches("[A-Z0-9]{6,12}"))
            throw new IllegalArgumentException("SKU inválido!");

        if (preco <= 0 || estoque < 0)
            throw new IllegalArgumentException("Preço ou estoque inválido!");

        this.sku = sku;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void reduzirEstoque(int qtd) {
        estoque -= qtd;
    }

    public void aumentarEstoque(int qtd) {
        estoque += qtd;
    }

    @Override
    public String toString() {
        return sku + " - " + nome + " | R$" + preco + " | Estoque: " + estoque;
    }
}
