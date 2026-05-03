package Atividade10;

import java.util.ArrayList;
import java.util.Comparator;

public class Estoque {

    private ArrayList<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public Produto buscarProduto(String sku) {
        for (Produto p : produtos) {
            if (p.getSku().equals(sku)) return p;
        }
        return null;
    }

    public void listarProdutos() {
        produtos.forEach(System.out::println);
    }

    public void listarOrdenadoPorPreco() {
        produtos.stream()
                .sorted(Comparator.comparingDouble(Produto::getPreco))
                .forEach(System.out::println);
    }
}
