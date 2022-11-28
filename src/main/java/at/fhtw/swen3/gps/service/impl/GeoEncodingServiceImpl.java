package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.json.JSONArray;
import org.json.JSONObject;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class GeoEncodingServiceImpl implements GeoEncodingService {
    private GeoCoordinateEntity geoCoordinateEntity=new GeoCoordinateEntity();

    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity adress) {
        URI url = URI.create(("https://nominatim.openstreetmap.org/search?addressdetails=1&q="+adress.getStreet()+" "+adress.getCountry()+" "+" "+adress.getCity()+" "+adress.getPostalCode()+"&format=json").replaceAll(" ", "%20"));
        System.out.println(url);
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        future
                .thenApply(HttpResponse::body)
                .thenAccept((response) -> {

                    JSONArray json = new JSONArray(response);
                    JSONObject obj = (JSONObject) json.get(0);
                    String lat = obj.getString("lat");
                    String lon = obj.getString("lon");
                    System.out.println("lat: "+ lat);
                    System.out.println("lon: "+ lon);
                    this.geoCoordinateEntity.setLon(Double.valueOf(lon));
                    this.geoCoordinateEntity.setLat(Double.valueOf(lat));

                })
                .join();


        return geoCoordinateEntity;
    }
}