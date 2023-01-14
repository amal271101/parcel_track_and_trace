package at.fhtw.swen3.gps.service.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.RecipientEntity;
import at.fhtw.swen3.services.BLValidationException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
@Service
@Slf4j
public class GeoEncodingServiceImpl implements GeoEncodingService {
    private GeoCoordinateEntity geoCoordinateEntity=new GeoCoordinateEntity();
    @Override
    public GeoCoordinateEntity encodeAddress(RecipientEntity adress) throws BLValidationException {
        try {
            URI url = URI.create(("https://nominatim.openstreetmap.org/search?addressdetails=1&q="+ formatStreet(adress.getStreet())+" "+adress.getCountry()+" "+" "+adress.getCity()+" "+formatPostalCode(adress.getPostalCode())+"&format=json").replaceAll(" ", "%20"));
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
                        this.geoCoordinateEntity.setLon(Double.valueOf(lon));
                        this.geoCoordinateEntity.setLat(Double.valueOf(lat));

                    })
                    .join();
            return geoCoordinateEntity;
        }catch (Exception e){
            log.error("The address of sender or receiver was not found.");
            throw new BLValidationException( e,"The address of sender or receiver was not found." );
        }

    }


    public String formatPostalCode(String postalCode){
        if(postalCode.matches("[A]-[0-9]{4}")){
            return postalCode.replaceFirst("[A]-", "");
        }
        return postalCode;
    }

    public String formatStreet(String street){

        if (street.contains("-")) {return street.replaceFirst("-.*$", "");
        }
        return street;
    }

}