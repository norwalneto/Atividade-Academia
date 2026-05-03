# 🛒 Sistema de Pedidos (Java - Console)

Projeto desenvolvido em Java com foco em controle de:
- Produtos (estoque)
- Clientes
- Pedidos
- Pagamentos
- Relatórios com faturamento e desconto

---

## 🚀 Funcionalidades

✅ Cadastro de produtos  
✅ Listagem de produtos  
✅ Cadastro de clientes  
✅ Criação de pedidos  
✅ Controle de estoque automático  
✅ Pagamento de pedidos  
✅ Cancelamento de pedidos (com regras)  
✅ Relatórios com:
- Faturamento total
- Vendas por categoria
- Top produtos
- Clientes com mais pedidos  
  ✅ Aplicação de desconto automático

---

## 📦 Regras de Negócio

### 🧾 Pedido
- Deve ter **mínimo 1 item**
- Quantidade permitida: **1 a 100**
- Não pode adicionar item sem estoque
- Estoque é reduzido ao adicionar item

---

### 💳 Pagamento
- Só pode pagar pedido com status `RESERVED`
- Após pagamento:
    - `PAID` (sucesso)
    - `FAILED` (falha)

---

### ❌ Cancelamento
- Não pode cancelar pedido com status `PAID`
- Ao cancelar:
    - estoque é devolvido

---

# 🛒 Sistema de Pedidos (Java - Console)

Projeto desenvolvido em Java com foco em controle de:
- Produtos (estoque)
- Clientes
- Pedidos
- Pagamentos
- Relatórios com faturamento e desconto

---

## 🚀 Funcionalidades

- Cadastro de produtos
- Listagem de produtos
- Cadastro de clientes
- Criação de pedidos
- Controle de estoque automático
- Pagamento de pedidos
- Cancelamento de pedidos (com regras)
- Relatórios com:
    - Faturamento total
    - Vendas por categoria
    - Top produtos
    - Clientes com mais pedidos
- Aplicação de desconto automático

---

## 📦 Regras de Negócio

### 🧾 Pedido
- Deve ter **mínimo 1 item**
- Quantidade permitida: **1 a 100**
- Não pode adicionar item sem estoque
- Estoque é reduzido ao adicionar item

---

### 💳 Pagamento
- Só pode pagar pedido com status `RESERVED`
- Após pagamento:
    - `PAID` (sucesso)
    - `FAILED` (falha)

---

### ❌ Cancelamento
- Não pode cancelar pedido com status `PAID`
- Ao cancelar:
    - estoque é devolvido

---

### 🏷️ Desconto
Aplicado automaticamente ao reservar o pedido:

- Se total > 500 → **10% de desconto**

Exemplo:

    desconto = total > 500 ? total * 0.10 : 0;

---

## 📊 Relatórios

O relatório considera somente pedidos pagos (`PAID`)

Exibe:
- Faturamento total (com desconto)
- Vendas por categoria (proporcional ao desconto)
- Top 3 produtos
- Clientes com mais pedidos pagos

---

## 🧪 Dados Iniciais

O sistema já inicia com:

### Produtos:
- Mouse (R$50)
- Teclado (R$100)
- Monitor (R$800)
- Cadeira (R$500)
- Mesa (R$700)

### Clientes:
- João
- Maria
- Pedro

### Pedidos:
- 2 pedidos já criados (status: RESERVED)

⚠️ IMPORTANTE:  
Esses pedidos **não entram no faturamento até serem pagos.**

---

## 🧑‍💻 Como Executar

Compile:

    javac Main.java

Execute:

    java Main

---

## 🧪 Como Testar

### ✅ Fluxo completo

Criar pedido:

    4 - Criar Pedido
    Nome do Cliente: João
    SKU: GHI789
    Quantidade: 1

Pagar pedido:

    5 - Pagar Pedido
    ID do Pedido: 3

Ver relatório:

    7 - Relatórios

---

### 🧪 Testar desconto

Criar pedido com valor alto:

    Produto: Monitor (800)

Resultado esperado:

    Total: 720
    Desconto: 80

---

### 🧪 Testar erros

- Quantidade maior que estoque → bloqueia
- Pagar sem reservar → erro
- Cancelar pedido pago → erro
- Criar pedido vazio → erro

---

## 🐞 Debug Mode

Ativar:

    static boolean debugMode = true;

- Mostra erros técnicos (stacktrace)
- Usar apenas em desenvolvimento

---

## 📁 Estrutura

- Main.java → fluxo principal
- Pedido.java → regras de negócio (pedido, pagamento, desconto)
- Produto.java → dados do produto
- Cliente.java → dados do cliente
- ItemPedido.java → itens do pedido (produto + quantidade)
- Estoque.java → controle de produtos e busca por SKU

### ⚠️ Tratamento de Erros

- InvalidOrderException.java → exceção personalizada para regras de negócio inválidas

Exemplo:

    public class InvalidOrderException extends RuntimeException {
        public InvalidOrderException(String msg) {
            super(msg);
        }
    }

Usada para casos como:
- Cancelar pedido já pago
- Pagar pedido não reservado
- Pedido sem itens

---

### 🔄 Status do Pedido

- StatusPedido.java → enum que define os estados do pedido

Estados possíveis:

    public enum StatusPedido {
        CRIADO,
        RESERVED,
        PAID,
        FAILED,
        CANCELADO
    }

Descrição:
- CRIADO → pedido inicial
- RESERVED → pedido reservado (estoque reduzido)
- PAID → pagamento aprovado
- FAILED → pagamento falhou
- CANCELADO → pedido cancelado (estoque devolvido)

---

## ⚠️ Observações

- Relatório considera apenas pedidos `PAID`
- Desconto já está incluso no faturamento
- Estoque é atualizado automaticamente
- IDs são sequenciais

---

## 💡 Melhorias Futuras

- Banco de dados
- API com Spring Boot
- Interface gráfica
- Logs estruturados 
- 