package sk.Peter_Tokovics.Disc_Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Disc {


    public static void main(String[] args) {
        int bloky = 8;
        int velkost = 32;

        char[][] disc = new char[bloky][velkost];
        BufferedReader buffRead = null;
        String riadok;
        String hardDisc = "src\\sk\\Peter_Tokovics\\Disc_Simulation\\disc.txt";
        char comma = ',';
        try {
            buffRead = new BufferedReader(new FileReader(hardDisc));
            for (int i = 0; (riadok = buffRead.readLine()) != null; i++) {
                char[] ch = riadok.toCharArray();
                //for (int i = 0; i < bloky; i++) {
                    for (int j = 0, n = 0; j < ch.length; j += 2, n++) {
                        if (ch[j] != comma) disc[i][n] = ch[j];
                    }
                }
            //}
            for (int i = 0; i < bloky; i++) {
                for (int j = 0; j < velkost; j++) {
                    System.out.print(disc[i][j] + ".");
                }
                System.out.println();
            }
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
}
