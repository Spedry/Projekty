package sk.Spedry.API_currency;

import org.json.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request;
import java.net.MalformedURLException;
import java.net.URL;

public class Exchange {
    private static String access_key = "131931da1dd2633f84634ca6c79fc7c7";
    private static String source = "USD";
    private static String currencies = "EUR,CZK,HUF,CHF";
    private static String format = "1";
    private static URL API;
    static {
        try {
            //API = new URL("http://www.apilayer.net/api/live?access_key=131931da1dd2633f84634ca6c79fc7c7&format=1");
            //API = new URL("http://apilayer.net/api/live?access_key=" + access_key  + "&currencies=" + currencies + "&source=" + source + "&format=" + format);
            API = new URL("http://apilayer.net/api/live?access_key=131931da1dd2633f84634ca6c79fc7c7&currencies=EUR,CZK,HUF,CHF&source=USD&format=1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static double exchangeTo(String toWhat) {
        try {
            System.out.println(toWhat);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(API)
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = null;
            jsonObject = new JSONObject(response.body().string());
            assert false;
            JSONObject quotes = jsonObject.getJSONObject("quotes");
            Object data = quotes.get("USD" + toWhat);
            return (double) data;
        } catch (Exception ex) {
            return -1;
        }
    }
}