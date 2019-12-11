package sk.Spedry.Odosiealnie_HTTP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;


import java.net.HttpURLConnection;
import java.net.URL;

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
            ServerSocket server = null;
            URL obj = new URL("http://localhost:8000/test");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();

            /*final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +
                    "    \"id\": 101,\r\n" +
                    "    \"title\": \"Test Title\",\r\n" +
                    "    \"body\": \"Test Body\"" + "\n}";
            System.out.println(POST_PARAMS);*/
            //String response = "This is the response";
            OutputStream os = t.getResponseBody();
            //os.write(POST_PARAMS.getBytes());
            //t.sendResponseHeaders(200, response.length());
            //os.write(response.getBytes());
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            try {
                String message = (String) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            os.flush();
            os.close();

            /*if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                System.out.println(response.toString());

            }*/
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
            /*URL obj = new URL("http://localhost:8000/test");
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
            os.close();*/


        }

    }
}

