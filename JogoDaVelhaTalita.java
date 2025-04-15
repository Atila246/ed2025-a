import java.util.Scanner;

public final class JogoDaVelhaTalita {
    protected static final int X = 1, O = -1;
    protected static final int VAZIO = 0;
    protected int[][] tabuleiro;
    protected int jogador;
    protected int dimensao;

    public JogoDaVelhaTalita() {
        setDimensao(3);
    }

    public JogoDaVelhaTalita(int dimensao) {
        setDimensao(dimensao);
    }

    public void setDimensao(int dimensao) {
        if (dimensao < 3) {
            throw new IllegalArgumentException("A dimensão deve ser pelo menos 3");
        }
        this.dimensao = dimensao;
        this.tabuleiro = new int[dimensao][dimensao];
        limpaTabuleiro();
    }

    public void limpaTabuleiro() {
        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogador = X;
    }

    public void poePeca(int i, int j) {
        if (i < 0 || i >= dimensao || j < 0 || j >= dimensao) {
            throw new IllegalArgumentException("Posição Inválida");
        }
        if (tabuleiro[i][j] != VAZIO) throw new IllegalArgumentException("Posição Ocupada");
        tabuleiro[i][j] = jogador;
        jogador = -jogador;
    }

    public boolean eVencedor(int marca) {
        for (int i = 0; i < dimensao; i++) {
boolean venceuLinha = true;
            for (int j = 0; j < dimensao; j++) {
            if (tabuleiro[i][j] != marca) {
venceuLinha = false;
                    break;
                }
            }
            if (venceuLinha) return true;
                    }

        for (int j = 0; j < dimensao; j++) {
            boolean venceuColuna = true;
            for (int i = 0; i < dimensao; i++) {
                if (tabuleiro[i][j] != marca) {
                    venceuColuna = false;
                    break;
                }
            }
            if (venceuColuna) return true;
        }

        boolean venceuDiagonaldireita = true;
        for (int i = 0; i < dimensao; i++) {
            if (tabuleiro[i][i] != marca) {
                venceuDiagonaldireita = false;
                break;
            }
        }
        if (venceuDiagonaldireita) return true;

        boolean venceuDiagonalesquerda = true;
        for (int i = 0; i < dimensao; i++) {
            if (tabuleiro[i][dimensao - 1 - i] != marca) {
                venceuDiagonalesquerda = false;
                break;
        }
}
        return venceuDiagonalesquerda;
    }

    public int vencedor() {
        if (eVencedor(X)) {
            return X;
        } else if (eVencedor(O)) {
            return O;
        } else {
            for (int i = 0; i < dimensao; i++) {
                for (int j = 0; j < dimensao; j++) {
                    if (tabuleiro[i][j] == VAZIO) {
                        return 0;
                    }
                }
            }
            return 2;
        }
    }

    public void jogarContraSiMesma() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            
            while (continuar) {
                limpaTabuleiro();
                while (vencedor() == 0) {
                    boolean jogadaValida = false;
                    while (!jogadaValida) {
                        int i = (int) (Math.random() * dimensao);
                        int j = (int) (Math.random() * dimensao);
                        if (tabuleiro[i][j] == VAZIO) {
                            poePeca(i, j);
                            jogadaValida = true;
                        }
                    }
                    System.out.println(toString());
                    if (vencedor() == 0) {
                        System.out.println("O jogo ainda não acabou");
                    }
                }
                
                int resultado = vencedor();
                switch (resultado) {
                    case X -> System.out.println("X venceu!");
                    case O -> System.out.println("O venceu!");
                    case 2 -> System.out.println("Empate!");
                    default -> {
                    }
                }
                
                    continuar = false;
                
            }
        }
    }

    @Override
    public String toString() {
        String retorno = "";
        for (int i = 0; i < 3; i++) {
            retorno += "| ";
            for (int j = 0; j < 3; j++) {
                switch (tabuleiro[i][j]) {
                    case X -> retorno += "X | ";
                    case O -> retorno += "O | ";
                    default -> retorno += "  | ";
                }
            }
            retorno += "\n";
        }
        return retorno;
    }

    public static void main(String[] args) {
        System.out.println("Escolha a dimensão do tabuleiro:");
        int dimensao = 3;
        
        System.out.println("Iniciando jogo com tabuleiro " + dimensao + "x" + dimensao);
        
        JogoDaVelhaTalita jogo = new JogoDaVelhaTalita(dimensao);
        jogo.jogarContraSiMesma();
    }
}