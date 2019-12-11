package sk.Spedry.Card;

public interface CardFace {
    public int zistiKredit(); //zobrazý aktuálny stav
    public void uhradKredit(int x); //zvíši kredyt o uhradenú sumu
    public void vykonajPlatbu(int x); //vypíše udalosť, a zníži kredit
}
