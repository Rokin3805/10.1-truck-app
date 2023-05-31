package com.example.truckshare;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.truckshare.OrderModel.Order;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    Order broughtOrder;
    TextView fare;
    TextView duration;
    Button callButton;
    Button bookButton;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Retrieve the parceled order and user
        Intent intent = getIntent();
        broughtOrder = intent.getParcelableExtra("order");
        username = intent.getStringExtra("username");

        fare = findViewById(R.id.textPrice);
        duration = findViewById(R.id.textTime);
        callButton = findViewById(R.id.buttonCall);
        bookButton = findViewById(R.id.buttonBook);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));

        LatLng location = new LatLng(-34.01, 151.01);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker for arbitrary location"));

        double distance = calculateDistance(sydney, location);
        duration.setText("Distance (KM): " + distance);

        double price = 2.5 * distance;
        fare.setText("Price: $" + price);

        //destination is the brought order location via the getter
        String destination = broughtOrder.getLocation();

        getDirections("Sydney", destination);
    }
    //opens dial pad with relevant number
    public void callButton(View view) {
        String phoneNumber = "412345678";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
    //opens payment portal
    public void bookButton(View view) {
        String paymentPortalLink = "https://buy.stripe.com/aEU4iF1AZfgd97WcMM";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(paymentPortalLink));
        startActivity(intent);
    }
    //calculates the distance between two points
    public double calculateDistance(LatLng startLatLng, LatLng endLatLng) {
        Location startLocation = new Location("start");
        startLocation.setLatitude(startLatLng.latitude);
        startLocation.setLongitude(startLatLng.longitude);

        Location endLocation = new Location("destination");
        endLocation.setLatitude(endLatLng.latitude);
        endLocation.setLongitude(endLatLng.longitude);

        double distanceM = startLocation.distanceTo(endLocation);
        double distanceKm = distanceM / 1000;
        return distanceKm;
    }

    //gets json response with the route information
    public void getDirections(String origin, String destination) {
        String apiKey = "AIzaSyBQEb06p7C4Ypx7kCSW5CEE6etg9nEikhs";

        //create url string with correct variables
        String urlString = "https://maps.googleapis.com/maps/api/directions/json?" +
                "origin=" + origin +
                "&destination=" + destination +
                "&key=" + apiKey;

        new FetchDirectionsTask().execute(urlString);
    }

    //extens AsyncTask to perform in background
    private class FetchDirectionsTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls) {
            try {
                //create a URL object with the first URL from the input array
                URL url = new URL(urls[0]);
                //open connection to the URL
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                //set as get request
                connection.setRequestMethod("GET");

                //stringBuilder to store the response
                StringBuilder response = new StringBuilder();

                //bufferedReader to read the input from the connection
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                //read each line from the input and append to the response stringBuilder
                String line;
                while ((line = reader.readLine()) != null)
                {
                    response.append(line);
                }
                //close the reader
                reader.close();

                //string convert the response StringBuilder to a String and return it
                return response.toString();
            }
            //exception handling is mandatory
            catch (IOException e)
            {
                //print the stack trace on exception
                e.printStackTrace();
            }
            //exception occured, return null
            return null;
        }

        //check if returned response is null before parsing
        @Override
        protected void onPostExecute(String response) {
            if (response != null) {
                parseDirectionsResponse(response);
            }
        }
    }

    public void parseDirectionsResponse(String directionsResponse) {
        try {
            //create a JSONobject from the directionsResponse string
            JSONObject jsonObject = new JSONObject(directionsResponse);
            //assign routes as routes array from the JSONObject
            JSONArray routes = jsonObject.getJSONArray("routes");
            //get the first route from the routes array (should be optimal route)
            JSONObject route = routes.getJSONObject(0);
            //get the overview_polyline object from the route as polyline variable
            JSONObject polyline = route.getJSONObject("overview_polyline");
            // Get the "points" string from the polyline object
            String points = polyline.getString("points");
            //decode the polyline points to a list of LatLng objects
            List<LatLng> decodedPoints = decodePolyline(points);

            //create a PolylineOptions object and customise line appearance
            PolylineOptions polylineOptions = new PolylineOptions()
                    .addAll(decodedPoints)
                    .width(5f)
                    .color(Color.BLUE);

            //add the polyline to the map obkect
            mMap.addPolyline(polylineOptions);
            // Get the duration information from the JSON response
            JSONArray legs = route.getJSONArray("legs");
            JSONObject leg = legs.getJSONObject(0);
            JSONObject duration = leg.getJSONObject("duration");
            String durationText = duration.getString("text");

            //set the duration to the EditText duration
            this.duration.setText(durationText);
        }
        //exception handling mandatory
        catch (JSONException e) {

            //print the stack trace on error
            e.printStackTrace();
        }
    }

    //this method was adapted from https://github.com/scoutant/polyline-decoder/blob/master/src/main/java/org/scoutant/polyline/PolylineDecoder.java
    private List<LatLng> decodePolyline(String polyline)
    {
        List<LatLng> routePoints = new ArrayList<>();
        int index = 0, len = polyline.length();
        int lat = 0, lng = 0;

        while (index < len)
        {
            int b, shift = 0, result = 0;
            do {
                b = polyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = polyline.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);

            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            double latDouble = lat * 1e-5;
            double lngDouble = lng * 1e-5;
            LatLng point = new LatLng(latDouble, lngDouble);
            routePoints.add(point);
        }

        return routePoints;
    }
}
