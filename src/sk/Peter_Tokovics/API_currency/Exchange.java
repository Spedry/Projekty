package sk.Peter_Tokovics.API_currency;

import org.json.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request;
import java.net.URL;

public class Exchange {


    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            String access_key = "131931da1dd2633f84634ca6c79fc7c7";
            String source = "USD";
            String currencies = "EUR,CZK,HUF,CHF";
            String format = "1";

            URL API = new URL("http://apilayer.net/api/live?access_key=" + access_key  + "&currencies=" + currencies + "&source=" + source + "&format=" + format);
            //URL API = new URL("http://apilayer.net/api/live?access_key=131931da1dd2633f84634ca6c79fc7c7&currencies=EUR,CZK,HUF,CHF&source=USD&format=1");
            //URL API = new URL("http://www.apilayer.net/api/live?access_key=131931da1dd2633f84634ca6c79fc7c7&format=1");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API)
                    .get()
                    .addHeader("USDEUR", "apilayer.net")
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject jsonObject = null;

            try {
                jsonObject = new JSONObject(response.body().string());
            } catch (Exception e) {
                System.out.println("lol");
            }

            assert false;
            System.out.println(jsonObject);

            JSONObject quotes = jsonObject.getJSONObject("quotes");
            System.out.println(quotes);

            Object data = quotes.get("USDEUR");
            System.out.println(data);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}