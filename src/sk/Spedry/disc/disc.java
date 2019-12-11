package sk.Spedry.disc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class disc {
    private static int bloky = 8;
    private static int velkost = 32;

    private static char[][] disc = new char[bloky][velkost];
    private static char[][] kos = new char[3][velkost];

    public static void main(String[] args) {

        BufferedReader buffRead = null;
        String riadok;
        String hardDisc = "src\\sk\\Peter_Tokovics\\disc\\disc.txt";
        char comma = ',';
        try {
            buffRead = new BufferedReader(new FileReader(hardDisc));
            for (int i = 0; (riadok = buffRead.readLine()) != null; i++) {
                char[] ch = riadok.toCharArray();
                for (int j = 0, n = 0; j < ch.length; j += 2, n++) {
                    if (ch[j] != comma) disc[i][n] = ch[j];
                }
            }
            terminal();
        }
        catch (IOException e) { //nastala chyba
            e.printStackTrace();
        } finally {
            if (buffRead != null) {
                try {
                    buffRead.close(); //ukončiť reader
                }
                catch (IOException e) { //nastala chyba
                    e.printStackTrace();
                }
            }
        }
    }

    private static void terminal() {
        char option;
        Scanner sc = new Scanner(System.in);
        manual();
        while (true) {
            option = sc.next().charAt(0);
            switch (option) {
                case 'q': {
                    makeNewFile();
                    break;
                }
                case 'w': {
                    printFile();
                    break;
                }
                case 'e': {
                    format();
                    break;
                }
                case 'r': {
                    dump();
                    break;
                }
                case 't': {
                    printBlock();
                    break;
                }
                case 'z': {
                    velkostDisku();
                    break;
                }
                case 'u': {
                    velkostSuboru();
                    break;
                }
                case 'i': {
                    pocetZnakov();
                    break;
                }
                case 'o': {
                    zmazSubor();
                    break;
                }
                case 'p': {
                    kos();
                    break;
                }
                case 'x': {
                    System.exit(0);
                    break;
                }
            }
            clearScreen();
            manual();
        }
    }

    private static void manual() {
        System.out.println("List príkazov");
        System.out.println("q - vytvor nový subor");
        System.out.println("w - vypíš subor");
        System.out.println("e - formatuj disk");
        System.out.println("r - vypíš obsah disku");
        System.out.println("t - vypíš blok");
        System.out.println("BONUSY");
        System.out.println("z - vypíš velkosť disku");
        System.out.println("u - vypíše velkosť súboru");
        System.out.println("i - vypíš zvyšní počet znakov");
        System.out.println("o - zmaz subor");
        System.out.println("p - vypíš čo je v koši");
        System.out.println("x - EXIT");

    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void makeNewFile() {
        Scanner sc = new Scanner(System.in);

        int[] choose = new int[3];
        String nazov, data1, data2;
        char[][] threeFreeBlocks = new char[3][velkost];
        threeFreeBlocks[0][0] = 'i';
        threeFreeBlocks[1][0] = 'd';
        threeFreeBlocks[2][0] = 'd';
        System.out.print("Zadajte ako sa bude volať súbor: ");
        nazov = sc.nextLine();
        System.out.println("Zadajte data ktoré chcete ulož iť: ");
        System.out.print("1: "); data1 = sc.nextLine();
        System.out.print("2: "); data2 = sc.nextLine();
        System.out.println("\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < bloky; j++) {
                choose[i] = -1;
                if (disc[j][0] == 'f') {
                    disc[j][0] = 'c';
                    choose[i] = j;
                    break;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (choose[i] == -1) System.out.println("Nenašiel sa volný block");
            else if (i == 0) {
                int j = 1;
                disc[choose[i]][0] = 'i';
                for (int n = 0; n < nazov.length(); j++, n++) {
                    disc[choose[i]][j] = nazov.charAt(n);
                }
                for (int n = 1;n < 3;j++, n++) {
                    disc[choose[i]][++j] = (char) (choose[n] + 48);
                }
            }
            else if (i == 1) {
                disc[choose[i]][0] = 'd';
                for (int j = 1, n = 0; n < data1.length(); j++, n++) {
                    disc[choose[i]][j] = data1.charAt(n);
                }
            }
            else {
                disc[choose[i]][0] = 'd';
                for (int j = 1, n = 0; n < data2.length(); j++, n++) {
                    disc[choose[i]][j] = data2.charAt(n);
                }
            }
        }
        for (int i = 0; i < bloky; i++) {
            for (int j = 0; j < velkost; j++) {
                System.out.print(disc[i][j] + ",");
            }
            System.out.println();
        }
    }

    private static void printFile() {
        Scanner sc = new Scanner(System.in);
        String subor;

        System.out.print("Aký subor hladáte?\nZadajte názov súboru:");
        subor = sc.nextLine();
        for (int i = 0; i < bloky; i++) {
            if (found(subor, i)) {
                System.out.println(disc[i]);
                lookForD(i);
            }
        }
    }
    private static boolean found(String subor, int i) {
        if (disc[i][0] == 'i') {
            for (int j = 1, n = 0; n < subor.length(); j++, n++) {
                if (disc[i][j] != subor.charAt(n)) return false;
            }
            return true;
        }
        return false;
    }
    private static void lookForD(int block) {
        int[] get = new int[2];
        int n = 0;
        for (int j = 0; j < 2; j++) {
            for (; n < velkost; n++) {
                if (disc[block][n] == '-') {
                    n++;
                    get[j] = Character.getNumericValue(disc[block][n]);
                    break;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < velkost; j++) {
                if (get[i] != -1) System.out.print(disc[get[i]][j] + ",");
                else break;
            }
            System.out.println();
        }
    }

    private static void format() {
        for (int i = 0; i < bloky;i++) {
            disc[i][0] = 'f';
            for (int j = 2; j < velkost; j++) {
                disc[i][j] = '-';
            }
        }
    }

    private static void dump() {
        for (int i = 0; i < bloky;i++) {
            System.out.print(i + ": ");
            for (int j = 1; j < velkost; j++) {
                System.out.print(disc[i][j] + ",");
            }
            System.out.println();
        }
    }

    private static void printBlock() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ktorý blok chcete vypísať?");
        int input = sc.nextInt();
        for (int i = 0; i < velkost; i++) {
            if (disc[input][i] == '-') break;
            else System.out.print(disc[input][i]);
        }
    }


    //////////////////
    //BONUSOVÉ PRIKAZY
    //////////////////

    private static void pocetZnakov() {

        for (int i = 0; i < bloky;i++) {
            int zvysok = 0;
            System.out.print(i + ": ");
            for (int j = 1; j < velkost; j++) {
                if (disc[i][j] == '-') zvysok++;
            }
            System.out.println("Ostavajuci pocet znakov je: " + zvysok);
        }
    }

    private static void velkostDisku() {
        int celkovaVelkost = 0;
        for (int i = 0; i < bloky;i++) {
            int velkostSub = 0;
            System.out.print(i + ": ");
            for (int j = 1; j < velkost; j++) {
                if (disc[i][j] != '-') velkostSub++;
            }
            celkovaVelkost+=velkostSub;
            System.out.println("velkosť súboru: " + velkostSub);
        }
        System.out.println("Celková velkosť súbru je: " + celkovaVelkost);
    }

    private static void velkostSuboru() {
        Scanner sc = new Scanner(System.in);
        String subor;

        System.out.print("Aký subor chcete najsť?\nZadajte názov súboru:");
        subor = sc.nextLine();
        for (int i = 0; i < bloky; i++) {
            if (found(subor, i)) {
                int velkostSub = 0;
                for (int j = 1; j < velkost; j++) {
                    if (disc[i][j] != '-') velkostSub++;
                }
                System.out.println(i + ": " + velkostSub);
                velkostD(i);
            }
        }
    }
    private static void velkostD(int block) {
        int[] get = new int[2];
        int n = 0;
        for (int j = 0; j < 2; j++) {
            for (; n < velkost; n++) {
                if (disc[block][n] == '-') {
                    n++;
                    get[j] = Character.getNumericValue(disc[block][n]);
                    break;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            int velkostD = 0;
            for (int j = 0; j < velkost; j++) {
                if (disc[get[i]][j] != '-') velkostD++;
            }
            System.out.println(get[i] + ": " + velkostD);
        }
    }

    private static void zmazSubor() {
        Scanner sc = new Scanner(System.in);
        String subor;

        System.out.print("Aký subor chcete zmazať?\nZadajte názov súboru:");
        subor = sc.nextLine();
        for (int i = 0; i < bloky; i++) {
            if (found(subor, i)) {
                deleteD(i);
                deleteI(i);
            }
        }
    }
    private static void deleteI(int block) {
        System.arraycopy(disc[block], 0, kos[0], 0, velkost);
        disc[block][0] = 'f';
        for (int j = 1; j < velkost; j++) {
            disc[block][j] = '-';
        }
    }
    private static void deleteD(int block) {
        int[] get = new int[2];
        int n = 0;
        for (int j = 0; j < 2; j++) {
            for (; n < velkost; n++) {
                if (disc[block][n] == '-') {
                    n++;
                    get[j] = Character.getNumericValue(disc[block][n]);
                    break;
                }
            }
        }
        for (int i = 1, j = 0; i < 3; i++, j++) {
            System.arraycopy(disc[get[j]], 0, kos[i], 0, velkost);
        }
        for (int i = 0; i < 2; i++) {
            disc[get[i]][0] = 'f';
            for (int j = 1; j < velkost; j++) {
                disc[get[i]][j] = '-';
            }
        }
    }

    private static void kos() {
        for (char[] k : kos) System.out.println(k);
    }
}