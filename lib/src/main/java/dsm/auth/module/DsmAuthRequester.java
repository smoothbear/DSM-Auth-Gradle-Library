package dsm.auth.module;

import com.google.gson.GsonBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class handles requests.
 */
public class DsmAuthRequester {
    /**
     * @param token - Dsm Auth Access-Token.
     * @return DsmUserInfo
     * @throws Exception - Throws Unauthorized Exception.
     */
    public DsmUserInfo getUserinfo(String token) throws Exception {
        HttpURLConnection connection = request(token);

        System.out.println(connection.getResponseCode());

        if (connection.getResponseCode() > 200) {
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

    /**
     * @param token - Dsm Auth Access-Token.
     * @return boolean type.
     * @throws Exception - Throws Unauthorized Exception.
     */
    public boolean userIsExist(String token) throws Exception {
        HttpURLConnection connection = request(token);

        return connection.getResponseCode() > 200;
    }

    private HttpURLConnection request(String token) throws Exception {
        URL url;
        HttpURLConnection connection;

        try {
            String DSM_AUTH_URL = "https://developer-api.dsmkr.com/v1/info/basic/";
            url = new URL(DSM_AUTH_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("access-token", "Bearer " + token);
            connection.setDoInput(true);
        } catch (Exception e) {
            throw new Exception("Could not connect to dsm auth!!");
        }

        return connection;
    }
}
