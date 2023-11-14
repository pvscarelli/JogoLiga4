import java.util.Scanner;

public class ligue4 {
    public static void main(String[] args) {
        ligue4 a = new ligue4();
    }

    public ligue4() {
        Scanner sc = new Scanner(System.in);
        char tabuleiro[][] = new char[6][7];
        char jogar = 'N';
        do {
            branco(tabuleiro);
            imprimir(tabuleiro);

            System.out.println("Quantas pessoas vão jogar: ");
            int jogadores = sc.nextInt();

            char VermOuAzul = escolha(sc);
            char corPC = 'A';
            if (VermOuAzul == 'A') {
                corPC = 'V';
            } else if (VermOuAzul == 'V') {
                corPC = 'A';
            }

            boolean empate = empate(tabuleiro);
            boolean teveVitoria = veSeVitoria(tabuleiro, VermOuAzul);

            while (!empate && !teveVitoria) {
                jogadaPessoa(tabuleiro, sc, VermOuAzul);
                System.out.println("JOGADA PESSOA: ");
                imprimir(tabuleiro);
                if (teveVitoria = veSeVitoria(tabuleiro, VermOuAzul)) {
                } else {
                    if (jogadores == 2) {
                        jogadaPessoa(tabuleiro, sc, corPC);
                        System.out.println("JOGADA PESSOA 2: ");
                        imprimir(tabuleiro);
                        teveVitoria = veSeVitoria(tabuleiro, corPC);
                    } else {
                        jogadaPC(tabuleiro, corPC);
                        System.out.println("JOGADA DO PC: ");
                        imprimir(tabuleiro);
                        teveVitoria = veSeVitoria(tabuleiro, corPC);
                    }
                }
                empate = empate(tabuleiro);
            }
            System.out.println("Deseja jogar novamente: (S PARA SIM OU QUALQUER OUTRA TECLA PARA ENCERRAR O PROGRAMA)");
            jogar = sc.next().toUpperCase().charAt(0);
        } while (jogar == 'S');
        sc.close();

    }

    public void branco(char[][] tabuleiro) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tabuleiro[i][j] = 'B';
            }
        }
    }

    public char escolha(Scanner sc) {

        System.out.println("Escolha sua cor entre V (vermelho) ou A (azul): ");
        char VermOuAzul = sc.next().toUpperCase().charAt(0);
        if (VermOuAzul != 'A' && VermOuAzul != 'V') {
            while (VermOuAzul != 'A' && VermOuAzul != 'V') {
                System.out.println("Cor inválida, escolha sua cor entre V (vermelho) ou A (azul): ");
                VermOuAzul = sc.next().toUpperCase().charAt(0);
            }
        }

        return VermOuAzul;
    }

    public void imprimir(char[][] tabuleiro) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tabuleiro[i][j] == 'B') {
                    System.out.print("( ) ");
                } else {
                    System.out.print("(" + tabuleiro[i][j] + ") ");
                }
            }
            System.out.println();
        }
    }

    public void jogadaPessoa(char[][] tabuleiro, Scanner sc, char VermOuAzul) {
        System.out.println("Escolha a coluna que deseja posicionar a peça: (Colunas vão de 1 a 7) ");
        int coluna = sc.nextInt();
        coluna += -1;
        boolean posicaoVal = false;

        while (!posicaoVal) {
            if (coluna < 0 || coluna > 6) {
                System.out
                        .println(
                                "Coluna inválida, digite uma coluna válida: ");
                coluna = sc.nextInt();
                coluna += -1;
                while (coluna < 0 || coluna > 6) {
                    System.out
                            .println(
                                    "Coluna inválida, digite uma coluna válida: ");
                    coluna = sc.nextInt();
                    coluna += -1;
                }
            }
            for (int i = 5; i >= 0; i--) {
                if (tabuleiro[i][coluna] == 'B') {
                    tabuleiro[i][coluna] = VermOuAzul;
                    posicaoVal = true;
                    break;
                }
            }
            if (!posicaoVal) {
                System.out.println("Coluna cheia, faça sua jogada novamente: ");
                coluna = sc.nextInt();
                coluna += -1;
            }
        }
    }

    public void jogadaPC(char[][] tabuleiro, char corPC) {
        boolean posicaoVal = false;

        int coluna = (int) (Math.random() * 7);

        while (!posicaoVal) {
            for (int i = 5; i >= 0; i--) {
                if (tabuleiro[i][coluna] == 'B') {
                    tabuleiro[i][coluna] = corPC;
                    posicaoVal = true;
                    break;
                }
            }
        }
    }

    public boolean veSeVitoria(char[][] tabuleiro, char VermOuAzul) {
       boolean vitoria = false;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (tabuleiro[i][j] == tabuleiro[i][j + 1] && tabuleiro[i][j + 1] == tabuleiro[i][j + 2]
                        && tabuleiro[i][j + 2] == tabuleiro[i][j + 3] && tabuleiro[i][j] == VermOuAzul) {
                    vitoria = true;
                }
            }
        }

        for (int j = 0; j < 7; j++) {
            for (int i = 0; i < 3; i++) {
                if (tabuleiro[i][j] == tabuleiro[i + 1][j] && tabuleiro[i + 1][j] == tabuleiro[i + 2][j]
                        && tabuleiro[i + 2][j] == tabuleiro[i + 3][j] && tabuleiro[i][j] == VermOuAzul) {
                    vitoria = true;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (tabuleiro[i][j] == tabuleiro[i + 1][j + 1] && tabuleiro[i + 1][j + 1] == tabuleiro[i + 2][j + 2]
                        && tabuleiro[i + 2][j + 2] == tabuleiro[i + 3][j + 3] && tabuleiro[i][j] == VermOuAzul) {
                    vitoria = true;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 6; j > 2; j--) {
                if (tabuleiro[i][j] == tabuleiro[i + 1][j - 1] && tabuleiro[i + 1][j - 1] == tabuleiro[i + 2][j - 2]
                        && tabuleiro[i + 2][j - 2] == tabuleiro[i + 3][j - 3] && tabuleiro[i][j] == VermOuAzul) {
                    vitoria = true;
                }
            }
        }  

        boolean teveVitoria = false;
      
        if (vitoria) {
            if (VermOuAzul == 'A') {
                System.out.println("AZUL GANHOU");
                teveVitoria = true;
            } else if (VermOuAzul == 'V') {
                System.out.println("VERMELHO GANHOU");
                teveVitoria = true;
            }
        }
        return teveVitoria;

    }

    public boolean empate(char[][] tabuleiro) {

        boolean empate = true;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tabuleiro[i][j] == 'B') {
                    empate = false;
                }
            }
        }
        if (empate) {
            System.out.println("EMPATE");
        }

        return empate;

    }
}
