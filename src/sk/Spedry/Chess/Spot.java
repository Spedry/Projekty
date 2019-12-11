package sk.Spedry.Chess;

public class Spot {
    private int x;
    private int y;
    private boolean safe = true;
    private Queen queen = null;

    Spot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public void setQueen(Queen queen) {
        this.queen = queen;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getSafe() {
        return safe;
    }

    public boolean getQueen() {
        if (queen != null) return true;
        else return false;
    }
}
