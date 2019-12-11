package sk.Spedry.Card;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Terminal {

    private static void print(String meno, String cislo, int peniaze) {
        System.out.print(String.format("%-20s", meno));
        System.out.print(String.format("**** **** **** %-18s", cislo.substring(0, 4)));
        System.out.println(String.format("%-6s", peniaze));
    } //Print

    private static String returnName(String name) {
        return String.format("%-19s", name);
    }

    private static String cardType(int limit) {
        if (limit == 5000) return "G";
        else return "B";
    } //zisti či ide o Blue alebo Gold card

    public static void main(String[] args) throws IOException {

        String cardsCSV = "src\\sk\\Peter_Tokovics\\Card\\Data\\Cards.csv";
        BufferedReader BuffRead = null;
        String TabCell = "";
        List<BlueCard> cards = new ArrayList<>();
        String comma = ",";
        String pathName = "src\\sk\\Peter_Tokovics\\Card\\Data\\new.csv";
        try {
            BuffRead = new BufferedReader(new FileReader(cardsCSV)); //čitač dokumenta CSV

            while ((TabCell = BuffRead.readLine()) != null) { //rob dokým nedojdeš na koniec
                String[] read = TabCell.split(comma);
                if (read[4].equals("B")) {
                    BlueCard blueCard = new BlueCard(read[0], read[1], read[2], Integer.parseInt(read[3])); //vytvorwniw modrej karty
                    cards.add(blueCard); //zaradenie karty
                }
                else {
                    GoldCard goldCard = new GoldCard(read[0], read[1], read[2], Integer.parseInt(read[3])); //vytvorenie zlatej karty
                    cards.add(goldCard); //zaradenie karty
                } //ak B tak BlueCard inak GoldCard
            }
        }
        catch (IOException e) { //nastala chyba
            e.printStackTrace();
        } finally {
            if (BuffRead != null) {
                try {
                    BuffRead.close(); //ukončiť reader
                }
                catch (IOException e) { //nastala chyba
                    e.printStackTrace();
                }
            }
        }
        while(true) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Zadajte súbor: ");
            String input;
            String insertCSV;

            if ((input = sc.nextLine()).toLowerCase().equals("exit")) {
                System.out.println("Ukončujem program");
                break;
            } //ak je input exit v ľubovoľnej kombinácie malých a veľkých písmen tak quit program
            else {
                insertCSV = "src\\sk\\Peter_Tokovics\\Card\\Data\\" + input + ".csv";
                try
                {
                    File CSV = new File(insertCSV);
                    while(!CSV.exists()) {
                        System.out.print("Zadajte súbor: ");
                        insertCSV = "src\\sk\\Peter_Tokovics\\Card\\Data\\" + sc.nextLine() + ".csv";
                        CSV = new File(insertCSV);
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            } //inak je input nazov csv tranzakcií

            try {
                BuffRead = new BufferedReader(new FileReader(insertCSV)); //čitač dokumenta CSV

                while ((TabCell = BuffRead.readLine()) != null) { //rob dokým nedojdeš na koniec
                    String[] read = TabCell.split(comma);
                    for (BlueCard card : cards) { //cyklus skenovania arraylistu Cards
                        if (card.getNum().contains(read[0])) { //
                            if (read[1].equals(card.getCVV())) { //CVV kód bol zle zadaný
                                if (card.getLimit() >= Math.abs(Integer.parseInt(read[2]))) { //limit karty bol prečerpaný
                                    if ((Integer.parseInt(read[2])) > 0) { //zisti či ide o pribudnutie alebo ubudnutie peňazí
                                        card.uhradKredit(Integer.parseInt(read[2])); //pričitaj kredit
                                        print(card.getOwner(), card.getNum(), card.zistiKredit()); //funkcia print
                                    } else {
                                        if ((card.zistiKredit() - Math.abs((Integer.parseInt(read[2])))) > 0) { //zisti finalny zostatok, nesmie byť >0
                                            card.vykonajPlatbu(Integer.parseInt(read[2])); //odčitaj kredit
                                            print(card.getOwner(), card.getNum(), card.zistiKredit()); //funkcia print
                                        } else System.out.println(returnName(card.getOwner()) + " Na účte nemáte dosť peňazí");
                                    }
                                } else System.out.println(returnName(card.getOwner()) + " Prečerpali ste limit karty");
                            } else System.out.println(returnName(card.getOwner()) + " Zadali ste zlý CVV kód");
                        }
                    }
                }
            }
            catch (IOException e) { //nastala chyba
                e.printStackTrace();
            } finally {
                if (BuffRead != null) {
                    try {
                        BuffRead.close(); //ukončiť reader
                    }
                    catch (IOException e) { //nastala chyba
                        e.printStackTrace();
                    }
                }
                PrintWriter csvWritter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathName), StandardCharsets.UTF_8));
                for (BlueCard card : cards) { //cyklus skenovania arraylistu Cards
                    csvWritter.append(card.getOwner()).append(comma).append(card.getNum()).append(comma).append(card.getCVV()).append(comma).append(String.valueOf(card.zistiKredit())).append(comma).append(cardType(card.getLimit())).append("\n"); //zapíš
                }
                csvWritter.flush(); //vyčisit
                csvWritter.close(); //zavri
            }
        }
    }
}
