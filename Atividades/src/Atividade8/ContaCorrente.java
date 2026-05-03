package Atividade8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContaCorrente {
    private int numero;
    private Cliente cliente;
    private double saldo;
    private LocalDateTime data;

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    public ContaCorrente(int numero, Cliente cliente, double saldoInicial) {
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldoInicial;
        this.data = LocalDateTime.now();
    }

    public void depositar(double valor) throws ExcecaoCustomizada {
        if (valor <= 0) {
            throw new ExcecaoCustomizada("Valor de depósito inválido!");
        }

        saldo += valor;

        System.out.println("=== DEPÓSITO ===");
        System.out.println("Cliente: " + cliente.getNomeCompleto());
        System.out.println("Conta: " + numero);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Saldo atual: R$ " + saldo);
        System.out.println("Data do Deposito: " + data.format(formato));
        System.out.println("================\n");
    }

    public void sacar(double valor) throws ExcecaoCustomizada {
        if (valor > saldo) {
            throw new ExcecaoCustomizada("Saldo insuficiente para saque!");
        }

        saldo -= valor;

        System.out.println("=== SAQUE ===");
        System.out.println("Cliente: " + cliente.getNomeCompleto());
        System.out.println("Conta: " + numero);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Saldo atual: R$ " + saldo);
        System.out.println("Data do Saque: " + data.format(formato));
        System.out.println("==============\n");
    }

    public void transferir(double valor, ContaCorrente destino) throws ExcecaoCustomizada {

        if (valor <= 0) {
            throw new ExcecaoCustomizada("Valor de transferência inválido!");
        }

        if (this.saldo < valor) {
            throw new ExcecaoCustomizada("Transferência cancelada: saldo insuficiente!");
        }

        // Dados antes da operação
        String origemNome = this.cliente.getNomeCompleto();
        String destinoNome = destino.getCliente().getNomeCompleto();

        this.sacar(valor);
        destino.depositar(valor);

        System.out.println("=== TRANSFERÊNCIA ===");
        System.out.println("De: " + origemNome + " (Conta " + this.numero + ")");
        System.out.println("Para: " + destinoNome + " (Conta " + destino.numero + ")");
        System.out.println("Valor: R$ " + valor);
        System.out.println("Data da Transferência: " + data.format(formato));
        System.out.println("====================\n");
    }

    public void exibirExtrato() {
        System.out.println("\n===== EXTRATO =====");
        System.out.println("Cliente: " + cliente.getNomeCompleto());
        System.out.println("Conta: " + numero);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Data: " + data.format(formato));
        System.out.println("===================\n");
    }

    public Cliente getCliente() {
        return cliente;
    }
}
