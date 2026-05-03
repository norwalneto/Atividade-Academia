package Atividade10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Estoque estoque = new Estoque();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();

    static Cliente buscarClientePorNome(String nome) {
        return clientes.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    static int idCliente = 1;
    static int idPedido = 1;

    static boolean debugMode = false;

    public static void main(String[] args) {

        carregarDadosIniciais();

        int op;

        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Add Produto");
            System.out.println("2 - Listar Produto");
            System.out.println("3 - Add Cliente");
            System.out.println("4 - Criar Pedido");
            System.out.println("5 - Pagar Pedido");
            System.out.println("6 - Cancelar Pedido");
            System.out.println("7 - Relatórios");
            System.out.println("0 - Sair");

            try {
                op = sc.nextInt();

                switch (op) {
                    case 1 -> addProduto();
                    case 2 -> estoque.listarProdutos();
                    case 3 -> addCliente();
                    case 4 -> criarPedido();
                    case 5 -> pagarPedido();
                    case 6 -> cancelarPedido();
                    case 7 -> relatorios();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Opção inválida");
                }

            } catch (Exception e) {
                System.out.println("Erro: entrada inválida.");
                if (debugMode) e.printStackTrace(); // só aparece em debug
                sc.nextLine();
                op = -1;
            }

        } while (op != 0);
    }

    // ========================= DADOS INICIAIS =========================

    static void carregarDadosIniciais() {

        estoque.adicionarProduto(new Produto("ABC123", "Mouse", "TI", 50, 10));
        estoque.adicionarProduto(new Produto("DEF456", "Teclado", "TI", 100, 10));
        estoque.adicionarProduto(new Produto("GHI789", "Monitor", "TI", 800, 10));
        estoque.adicionarProduto(new Produto("JKL111", "Cadeira", "Moveis", 500, 10));
        estoque.adicionarProduto(new Produto("MNO222", "Mesa", "Moveis", 700, 10));

        clientes.add(new Cliente(idCliente++, "João", "joao@email.com"));
        clientes.add(new Cliente(idCliente++, "Maria", "maria@email.com"));
        clientes.add(new Cliente(idCliente++, "Pedro", "pedro@email.com"));


        Pedido p1 = new Pedido(idPedido++, clientes.get(0));
        Produto prod1 = estoque.buscarProduto("ABC123");
        prod1.reduzirEstoque(1);
        p1.adicionarItem(new ItemPedido(prod1, 1));
        p1.reservar();
        pedidos.add(p1);

        Pedido p2 = new Pedido(idPedido++, clientes.get(1));
        Produto prod2 = estoque.buscarProduto("DEF456");
        prod2.reduzirEstoque(1);
        p2.adicionarItem(new ItemPedido(prod2, 1));
        p2.reservar();
        pedidos.add(p2);
    }

    // ========================= PRODUTO =========================

    static void addProduto() {
        sc.nextLine();

        System.out.print("SKU: ");
        String sku = sc.nextLine();

        if (estoque.buscarProduto(sku) != null) {
            System.out.println("Erro: SKU já existe!");
            return;
        }

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String cat = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque: ");
        int est = sc.nextInt();

        try {
            estoque.adicionarProduto(new Produto(sku, nome, cat, preco, est));
            System.out.println("Produto cadastrado!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto!");
            if (debugMode) e.printStackTrace();
        }
    }

    // ========================= CLIENTE =========================

    static void addCliente() {
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (!email.contains("@")) {
            System.out.println("Erro: email deve conter '@'");
            return;
        }

        clientes.add(new Cliente(idCliente++, nome, email));
        System.out.println("Cliente cadastrado!");
    }

    static Cliente buscarCliente(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ========================= PEDIDO =========================

    static void criarPedido() {

        sc.nextLine();
        System.out.print("Nome do Cliente: ");
        String nome = sc.nextLine();

        Cliente c = buscarClientePorNome(nome);

        if (c == null) {
            System.out.println("Erro: cliente não encontrado!");
            return;
        }

        Pedido pedido = new Pedido(idPedido++, c);

        int opc = 1;

        do {
            System.out.print("SKU: ");
            String sku = sc.nextLine();

            Produto p = estoque.buscarProduto(sku);

            if (p == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.print("Quantidade: ");
            int qtd = sc.nextInt();

            if (qtd < 1 || qtd > 100) {
                System.out.println("Quantidade deve ser entre 1 e 100.");
                sc.nextLine();
                continue;
            }

            if (p.getEstoque() < qtd) {
                System.out.println("Estoque insuficiente. Disponível: " + p.getEstoque());
                sc.nextLine();
                continue;
            }

            p.reduzirEstoque(qtd);
            pedido.adicionarItem(new ItemPedido(p, qtd));

            System.out.println("Adicionar mais? 1-Sim 0-Não");
            opc = sc.nextInt();
            sc.nextLine();

        } while (opc == 1);

        if (pedido.getItens().isEmpty()) {
            System.out.println("Erro: pedido deve conter ao menos 1 item.");
            return;
        }

        try {
            pedido.reservar();
            pedidos.add(pedido);
            System.out.println("Pedido reservado com sucesso!");
            System.out.println("Total: R$ " + pedido.calcularTotal());
            System.out.println("Desconto aplicado: R$ " + pedido.getDesconto());
        } catch (Exception e) {
            System.out.println("Erro ao reservar pedido.");
            if (debugMode) e.printStackTrace();
        }
    }

    // ========================= PAGAMENTO =========================

    static void pagarPedido() {
        System.out.print("ID do Pedido: ");
        int id = sc.nextInt();

        for (Pedido p : pedidos) {
            if (p.getId() == id) {

                if (p.getStatus() != StatusPedido.RESERVED) {
                    System.out.println("Erro: pedido precisa estar RESERVED.");
                    return;
                }

                try {
                    p.pagar();
                    System.out.println("Pedido do cliente " + p.getCliente().getNome() +
                            " pago com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao processar pagamento.");
                    if (debugMode) e.printStackTrace();
                }
                return;
            }
        }

        System.out.println("Pedido não encontrado.");
    }

    // ========================= CANCELAMENTO =========================

    static void cancelarPedido() {
        System.out.print("ID do Pedido: ");
        int id = sc.nextInt();

        for (Pedido p : pedidos) {
            if (p.getId() == id) {

                try {
                    p.cancelar();
                    System.out.println("Pedido do cliente " +
                            p.getCliente().getNome() + " cancelado!");
                } catch (InvalidOrderException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro ao cancelar pedido.");
                    if (debugMode) e.printStackTrace();
                }

                return;
            }
        }

        System.out.println("Pedido não encontrado.");
    }

    // ========================= RELATÓRIOS =========================

    static void relatorios() {

        double total = 0;

        Map<String, Double> categoria = new HashMap<>();
        Map<String, Integer> produtos = new HashMap<>();
        Map<String, Integer> clientesMap = new HashMap<>();

        for (Pedido p : pedidos) {

            if (p.getStatus() == StatusPedido.PAID) {

                double totalPedido = p.calcularTotal(); // 🔥 COM DESCONTO
                total += totalPedido;

                String nome = p.getCliente().getNome();

                clientesMap.put(nome,
                        clientesMap.getOrDefault(nome, 0) + 1);

                // 🔥 DISTRIBUIR O TOTAL PROPORCIONALMENTE
                double totalBruto = p.calcularTotalBruto();

                for (ItemPedido i : p.getItens()) {

                    double proporcao = i.getTotal() / totalBruto;
                    double valorComDesconto = totalPedido * proporcao;

                    categoria.put(i.getProduto().getCategoria(),
                            categoria.getOrDefault(i.getProduto().getCategoria(), 0.0) + valorComDesconto);

                    produtos.put(i.getProduto().getSku(),
                            produtos.getOrDefault(i.getProduto().getSku(), 0) + i.getQuantidade());
                }
            }
        }

        System.out.println("\n==== RELATÓRIO ====");

        if (total == 0) {
            System.out.println("Nenhuma venda realizada.");
            return;
        }

        System.out.println("\nFaturamento total: R$ " + total);

        System.out.println("\nPor categoria:");
        categoria.forEach((k,v) -> System.out.println(k + " -> " + v));

        System.out.println("\nTop produtos:");
        produtos.entrySet().stream()
                .sorted((a,b) -> b.getValue() - a.getValue())
                .limit(3)
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));

        System.out.println("\nClientes com mais pedidos:");
        clientesMap.forEach((k,v) ->
                System.out.println(k + " -> " + v + " pedidos"));
    }
}
