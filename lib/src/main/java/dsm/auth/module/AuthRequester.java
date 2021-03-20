package dsm.auth.module;

import com.google.gson.GsonBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthRequester {
    public DsmUserInfo getUserinfo(String accessToken) throws Exception {
        URL url;
        HttpURLConnection connection;

        try {
            url = new URL("https://developer-api.dsmkr.com/v1/info/basic/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setDoInput(true);
        } catch (Exception e) {
            throw new Exception("Could not connect to dsm auth!!");
        }

        int statusCode = connection.getResponseCode();

        if (statusCode > 200) {
            throw new Exception("DSM Auth: Unauthorized.");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        String json = response.toString();

        return new GsonBuilder().create().fromJson(json, DsmUserInfo.class);
    }
}
