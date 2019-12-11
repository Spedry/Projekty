package sk.Spedry.Card;

public class GoldCard extends BlueCard{
    private static int limit = 5000;
    GoldCard(String name, String num, String CVV, int kredit) {
        super(name, num, CVV, kredit);
    }
    int getLimit() {
        return limit;
    }
}
