package sk.Peter_Tokovics.Chess;

public class Queen {
    Queen(int x, int y, int N) {

        for (int i = 0; i < N; i++) {
           Board.getSpot(i, y).setSafe(false);
        }

        for (int i = 0; i < N; i++) {
            Board.getSpot(x, i).setSafe(false);
        }



    }
}

//[0 0][1 0][2 0][3 0][4 0]
//[0 1][1 1][2 1][3 1][4 1]
//[0 2][1 2][2 2][3 2][4 2]
//[0 3][1 3][2 3][3 3][4 3]
//[0 4][1 4][2 4][3 4][4 4]