import java.util.Scanner;

public class JogoDaVelhaDimensaoTalita {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Escolha a dimensão do tabuleiro (mínimo 3):");
            int dimensao = 3;

            while (true) {
                try {
                    dimensao = Integer.parseInt(scanner.nextLine());
                    if (dimensao < 3) {
                        System.out.println("A dimensão deve ser pelo menos 3. Tente novamente:");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Digite um número inteiro:");
                }
            }

            System.out.println("Iniciando jogo com tabuleiro " + dimensao + "x" + dimensao);

            JogoDaVelhaTalita jogo = new JogoDaVelhaTalita();
            jogo.jogarContraSiMesma();
        }
    }
}