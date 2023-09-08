package com.app.restapi;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);

        try {
            getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMovies() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://moviesminidatabase.p.rapidapi.com/movie/byYear/2015/")
                .get()
                .addHeader("X-RapidAPI-Key", "1a9b65ea23msh6d641a4c5e68500p1accc0jsne5b038a2448e")
                .addHeader("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response.body().string());


        JsonNode movies = actualObj.get("results");

        for (JsonNode movie : movies) {
            String title = movie.get("title").asText();
            String imdb_id = movie.get("imdb_id").asText();

            System.out.println("Title:- "+title + "\nID:-   " + imdb_id);

        }

    }

}
