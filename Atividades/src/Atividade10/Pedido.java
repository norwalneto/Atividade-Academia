package Atividade10;

import java.util.ArrayList;

public class Pedido {

    private int id;
    private Cliente cliente;
    private ArrayList<ItemPedido> itens = new ArrayList<>();
    private StatusPedido status;
    private double desconto = 0; // 🔥 NOVO

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.status = StatusPedido.CRIADO;
    }

    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    // REGRA DE DESCONTO AUTOMÁTICO
    public void aplicarDescontoPorRegra() {
        double total = calcularTotalBruto();

        // 10% de desconto se passar de 500
        desconto = total > 500 ? total * 0.10 : 0;
    }

    // 🔥 TOTAL SEM DESCONTO
    public double calcularTotalBruto() {
        double total = 0;
        for (ItemPedido i : itens) {
            total += i.getTotal();
        }
        return total;
    }

    //TOTAL FINAL COM DESCONTO
    public double calcularTotal() {
        return calcularTotalBruto() - desconto;
    }

    public void reservar() {

        if (itens.isEmpty())
            throw new InvalidOrderException("Pedido sem itens!");

        aplicarDescontoPorRegra();

        this.status = StatusPedido.RESERVED;
    }

    public void pagar() {

        if (status != StatusPedido.RESERVED)
            throw new InvalidOrderException("Pedido precisa estar RESERVED!");

        boolean pago = Pagamento.processar(calcularTotal());

        this.status = pago ? StatusPedido.PAID : StatusPedido.FAILED;
    }

    public void cancelar() {

        if (status == StatusPedido.PAID)
            throw new InvalidOrderException("Pedido pago não pode ser cancelado!");

        for (ItemPedido item : itens) {
            item.getProduto().aumentarEstoque(item.getQuantidade());
        }

        status = StatusPedido.CANCELADO;
    }

    // ================= GETTERS =================

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}
