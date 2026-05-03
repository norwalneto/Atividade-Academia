package Atividade9;

import java.util.Scanner;

public class SalaAula {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Quantidade de alunos: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        String[] nomes = new String[qtd];
        double[][] notas = new double[qtd][3];
        double[] medias = new double[qtd];

        int aprovados = 0, recuperacao = 0, reprovados = 0;
        double somaGeral = 0, maiorMedia = Double.MIN_VALUE, menorMedia = Double.MAX_VALUE;

        for (int i = 0; i < qtd; i++) {

            System.out.print("Nome: ");
            String nome = sc.nextLine();
            nome = nome.length() < 3 ? "Aluno" + i : nome;
            nomes[i] = nome;

            for (int j = 0; j < 3; j++) {
                double nota;

                while (true) {
                    try {
                        System.out.print("Nota " + (j + 1) + ": ");
                        nota = sc.nextDouble();

                        double valida = (nota >= 0 && nota <= 100) ? nota : gerarErro();

                        notas[i][j] = valida;
                        break;

                    } catch (Exception e) {
                        System.out.println("Nota inválida, tente novamente.");
                        sc.nextLine();
                    }
                }
            }

            double media = (notas[i][0] + notas[i][1] + notas[i][2]) / 3;
            medias[i] = media;

            somaGeral += media;

            maiorMedia = media > maiorMedia ? media : maiorMedia;
            menorMedia = media < menorMedia ? media : menorMedia;

            String status = media >= 70 ? "APROVADO" : (media >= 50 ? "RECUPERAÇÃO" : "REPROVADO");

            aprovados += media >= 70 ? 1 : 0;
            recuperacao += (media >= 50 && media < 70) ? 1 : 0;
            reprovados += media < 50 ? 1 : 0;

            System.out.println(nome + " | Notas: " +
                    notas[i][0] + ", " + notas[i][1] + ", " + notas[i][2] +
                    " | Média: " + String.format("%.2f", media) +
                    " | " + status);

            sc.nextLine();
        }

        double mediaGeral = somaGeral / qtd;

        System.out.println("\n--- Estatísticas ---");
        System.out.println("Maior média: " + String.format("%.2f", maiorMedia));
        System.out.println("Menor média: " + String.format("%.2f", menorMedia));
        System.out.println("Média geral: " + String.format("%.2f", mediaGeral));

        System.out.println("\n--- Distribuição ---");
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Recuperação: " + recuperacao);
        System.out.println("Reprovados: " + reprovados);

        System.out.println("\n--- Melhores alunos ---");
        for (int i = 0; i < qtd; i++) {
            System.out.print(medias[i] == maiorMedia ? nomes[i] + " com média " + medias[i] : "");
        }
    }

    public static double gerarErro() {
        throw new IllegalArgumentException();
    }
}
