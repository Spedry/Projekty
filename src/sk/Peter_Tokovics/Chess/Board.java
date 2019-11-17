package sk.Peter_Tokovics.Chess;

public class Board {
    private static Spot[][] spots;
    private static int N;

    Board(int N) {
        this.N = N;
        spots = new Spot[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                spots[j][i] = new Spot(i, j);
            }
        }
    }

    static Spot getSpot(int x, int y) {
        return spots[x][y];
    }

    /*void placeQueen() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if(spots[j][i].getSafe()) {
                    spots[j][i].setQueen(new Queen(j, i, N));
                }
            }
            printBoard();
        }
    }*/
    void placeQueen() {
        spots[3][2].setQueen(new Queen(3, 2, N));
        printBoard();
    }

    static void printBoardd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print("[" + spots[i][j].getX() + " " + spots[i][j].getY() + "]");
            }
            System.out.println();
        }
    }

    static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (spots[j][i].getQueen()) {
                    System.out.print(" ♕ ");
                }
                else if(!spots[j][i].getSafe()){
                    System.out.print(" \uD83D\uDD32 ");
                }
                else {
                    System.out.print(" \uD83D\uDD33 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
