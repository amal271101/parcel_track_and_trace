package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
@Service
public class GeoEncodingServiceImpl implements GeoEncodingService {
    private GeoCoordinateEntity geoCoordinateEntity=new GeoCoordinateEntity();
    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity adress) {

        System.out.println("before: " +adress.getPostalCode());
        System.out.println("after: "+formatPostalCode(adress.getPostalCode()));
        URI url = URI.create(("https://nominatim.openstreetmap.org/search?addressdetails=1&q="+ formatStreet(adress.getStreet())+" "+adress.getCountry()+" "+" "+adress.getCity()+" "+formatPostalCode(adress.getPostalCode())+"&format=json").replaceAll(" ", "%20"));
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


    public String formatPostalCode(String postalCode){
        if(postalCode.matches("[A]-[0-9]{4}")){
            return postalCode.replaceFirst("[A]-", "");
        }
        return postalCode;
    }

    public String formatStreet(String street){

        if (street.contains("-")) {
           return street.replaceFirst("-.*$", "");

        }
        return street;
    }

}