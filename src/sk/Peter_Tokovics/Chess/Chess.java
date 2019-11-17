package sk.Peter_Tokovics.Chess;

public class Chess {
    public static void main(String[] args) {
        int N = 6;

        Board board = new Board(N);

        board.placeQueen();

        board.printBoardd();


    }
}
