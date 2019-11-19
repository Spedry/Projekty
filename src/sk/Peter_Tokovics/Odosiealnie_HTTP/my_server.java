package sk.Peter_Tokovics.Odosiealnie_HTTP;

import com.sun.prism.shader.Solid_ImagePattern_Loader;
import sun.awt.geom.AreaOp;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class my_server implements Runnable{
    // cesta k súboru kde je html
    static final File PATH = new File("src\\sk\\Peter_Tokovics\\Odosiealnie_HTTP\\Html_Chat");
    // hlavné telo html stránky
    static final String INDEX = "index.html";
    // v pripade keby nastala chyba napr. index.html by nebol najdený/načítaný
    static final String ERROR = "404.html";
    // prichýdzajúca methoda nebola nerozoznaná
    static final String METHOD_NOT_FOUND = "method_error.hmtl";
    // proste port
    static final int PORT = 8000;

    static final boolean napojenie = true;

    private Socket connect;

    public my_server(Socket c) {
        connect = c;
    }
    // telo main
    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            System.out.println("Server nabieha.\nČaká na input na porte: " + PORT + "...");

            while(true) {
                Server myServer = new Server(serverConnect.accept());

                if(napojenie) {
                    System.out.println("Pripojenie nadviazané. (" + new Date() + ")");
                }

                Thread thread = new Thread(myServer);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Chyba pri pokuse nadviadzať spojenie: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        BufferedReader vstup = null;
        PrintWriter vystup = null;
        BufferedOutputStream dataVystup = null;
        String pozadovanySubor = null;

        try {
            // načítanie char input od klienta pomocou input stream na socket
            vstup = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            // zaslanie char output klientovi (for headers)
            vystup = new PrintWriter(connect.getOutputStream());
            // zaslanie binary output klientovy (pre požadované dáta)
            dataVystup = new BufferedOutputStream(connect.getOutputStream());

            // ziskať prvý riadok inputu/žiadosti od klienta
            String input = vstup.readLine();
            // rozdelenie inputu/žiadosti pomocou string tokenizer
            StringTokenizer rozdel = new StringTokenizer(input);
            String method = rozdel.nextToken().toUpperCase(); // zistíme o akú metódu sme prijali
            // vráti html súbor teda stránku
            pozadovanySubor = rozdel.nextToken().toLowerCase();
        }
    }
}
