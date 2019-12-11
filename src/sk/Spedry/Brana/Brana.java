class MontyGate {
    boolean Win = false;

    void setWin() {
        this.Win = true;
    }

    boolean getWin() {
        return this.Win;
    }
}

public class Brana {

    public static void main(String[] args) {

        int Vyhra = 0;
        int Prehra = 0;

        for (int i = 0; i < 1000; i++ ) {
            MontyGate[] Gate = new MontyGate[3];
            for (int o = 0; o < 3; o++) {
                Gate[o] = new MontyGate();
            }
            int rand1 = (int)(Math.random()*3);
            //System.out.print(rand1);
            Gate[rand1].setWin();
            int rand2 = (int)(Math.random()*3);
            if (!Gate[rand2].getWin()) {
                Vyhra++;
            }
                else {
                Prehra++;
            }
        }
        System.out.println("Vyhra:" + Vyhra);
        System.out.println("Prehra:" + Prehra);
    }
}
