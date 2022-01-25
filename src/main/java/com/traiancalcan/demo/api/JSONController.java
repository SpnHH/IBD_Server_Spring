package com.traiancalcan.demo.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.traiancalcan.demo.URLs;
import com.traiancalcan.demo.service.LinkService;
import com.traiancalcan.demo.service.PersonService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpHeaders.USER_AGENT;


@RequestMapping("api/v1/doc")
@RestController
public class JSONController {
    private static JSONArray jsonArray;
    private static String searchName;
    private final PersonService personService;
    private final LinkService linkService;
    private final URLs urls;
    public JSONController(PersonService personService, LinkService linkService) throws IOException {
        this.personService = personService;
        this.linkService = linkService;
        jsonArray = new JSONArray();
        urls = new URLs();
    }

    @PostMapping
    public String writeJsonArray(@NonNull @RequestBody JSONObject json) throws Exception {
//        sendingPostRequest("http://0ae9-5-12-162-14.ngrok.io/",json);
//        sendingPostRequest("http://167a-188-25-3-123.ngrok.io/",json);
        return "Success";
    }

//    @PostMapping
//    public String writeJson(@NonNull@RequestBody JSONPObject json){
////        try {
////          //  sendingPostRequest("http://0ae9-5-12-162-14.ngrok.io/",json);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return "";
//    }

    @GetMapping(path = "{name}")
    public JSONObject writeName(@NonNull @PathVariable("name") String name)  {
        name = name.toLowerCase(Locale.ROOT);
        name = name.replaceAll("\\s","-");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", name);

        try {
//            sendingPostRequest("http://167a-188-25-3-123.ngrok.io",jsonObject);
            postReq(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public int sendGetRequest(String url) throws IOException {
        String USER_AGENT = "Mozilla/5.0";
        String GET_URL = url;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        return responseCode;

    }

    private void sendingPostRequest(String url, JSONObject jsonInputString) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        // For POST only - START
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
//            osw.write(jsonInputString.toString());
        }
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    private void sendingPostRequestWithArgs(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        // For POST only - START
        con.setDoOutput(true);
        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

//    public void postReq(JSONObject jsonObject) throws IOException {
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://7c03-188-25-3-123.ngrok.io");
//
//        StringEntity entity = new StringEntity(jsonObject.toString());
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-type", "application/json");
//
//
//        CloseableHttpResponse response = client.execute(httpPost);
//        if(response.getStatusLine().getStatusCode() == 200){ // do sth};
//            client.close();
//            HttpEntity ent = response.getEntity();
//            String str = EntityUtils.toString(ent);
//            var y = 0;
//    }
public void postReq(JSONObject jsonObject) throws Exception {
    HttpClient client =new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://7c03-188-25-3-123.ngrok.io");

    StringEntity entity = new StringEntity(jsonObject.toString());
    httpPost.setEntity(entity);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");


    HttpResponse response = client.execute(httpPost);
    if(response.getStatusLine().getStatusCode() == 200){ // do sth};
        HttpEntity ent = response.getEntity();
        String str = EntityUtils.toString(ent);
        JSONParser parser = new JSONParser();
        sendingPostRequest(urls.getMLURL(), (JSONObject) parser.parse(str));
    }else{
        //print err
        System.out.println("err");
    }
}
}


