package sk.Peter_Tokovics.Odosiealnie_HTTP;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Odosielanie {


    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {

            /*URL obj = new URL("http://localhost:8000/test");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(t.getResponseCode());
            String res = "This is the response";
            os.write(res.getBytes());
            //os.flush();
            os.close();

            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in .readLine()) != null) {
                    response.append(inputLine);
                } in .close();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }*/
            URL obj = new URL("http://localhost:8000/test");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

            //String response = "This is the response";
            //t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            //OutputStream os = postConnection.getOutputStream();
            os.flush();
            os.close();

            int responseCode = t.getResponseCode();
            //String omg = t.getResponseMessage()
            if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in .readLine()) != null) {
                    response.append(inputLine);
                } in .close();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }
            os.close();
        }

    }

}

