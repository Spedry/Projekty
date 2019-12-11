package sk.Spedry.Card;

public class BlueCard implements CardFace {
    private String owner;
    private String num;
    private String CVV;
    private int kredit;
    private static int limit = 1000;

    BlueCard(String name, String num, String CVV, int kredit) {
        this.owner = name;
        this.num = num;
        this.CVV = CVV;
        this.kredit = kredit;
    }

    String getOwner() {
        return owner;
    }

    String getNum() {
        return num;
    }

    String getCVV() {
        return CVV;
    }

    int getLimit() {
        return limit;
    }

    @Override
    public int zistiKredit() {
        return this.kredit;
    }
    @Override
    public void vykonajPlatbu(int hodnota) {
        this.kredit+=hodnota;
    }
    @Override
    public void uhradKredit(int hodnota) {
        this.kredit+=hodnota;
    }
}