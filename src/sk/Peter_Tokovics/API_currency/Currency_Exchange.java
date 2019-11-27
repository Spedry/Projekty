package sk.Peter_Tokovics.API_currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
//import org.json.simple.JSONObject;
//import org.json.JSONString;
import org.json.*;
public class Currency_Exchange {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            URL url = new URL("http://www.apilayer.net/api/live?access_key=131931da1dd2633f84634ca6c79fc7c7&format=1");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String str = "";
            while (null != (str = br.readLine())) {
                System.out.println(str);

                //JSONObject json = new JSONObject(br);
                //String USD = json.getString("USDAED");
            //System.out.println(USD);
            }
            //JsonReader jsonReader = Json.createReader(...);
            //JsonObject object = jsonReader.readObject();
            //jsonReader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}