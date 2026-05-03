package Atividade8;

public class Principal {

    public static void main(String[] args) {

        try {
            // ==========================
            // Instanciando clientes
            // ==========================
            Cliente cliente1 = new Cliente("Norwal", "Gomes", "1238509500");
            Cliente cliente2 = new Cliente("Maria", "Silva", "45612347900");

            // ==========================
            // Instanciando conta1
            // ==========================
            ContaCorrente conta1 = new ContaCorrente(1, cliente1, 1000);

            // Exibir saldo e fazer depósito
            conta1.exibirExtrato();
            conta1.depositar(200);

            // ==========================
            // Instanciando conta2
            // ==========================
            ContaCorrente conta2 = new ContaCorrente(2, cliente2, 500);

            conta2.exibirExtrato();

            // ==========================
            // Executando saque
            // ==========================
            conta1.sacar(300);

            // ==========================
            // Exibir nome do cliente da conta1
            // ==========================
            System.out.println("Cliente da conta1: " + conta1.getCliente().getNomeCompleto());

            // ==========================
            // Transferência
            // ==========================
            conta1.transferir(400, conta2);

            // ==========================
            // Extratos finais
            // ==========================
            conta1.exibirExtrato();
            conta2.exibirExtrato();

            // ==========================
            // TESTE DE ERRO (opcional)
            // ==========================
            conta1.transferir(5000, conta2);

        } catch (ExcecaoCustomizada e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
