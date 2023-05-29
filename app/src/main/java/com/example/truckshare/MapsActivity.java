package com.example.truckshare;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
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
import com.example.truckshare.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Order broughtOrder;

    TextView fare;
    TextView duration;
    Button callButton;
    Button bookButton;

    String username;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //retrieve the parceled order and user (not necessary but for more detailed implementation it could be )
        Intent intent = getIntent();
        broughtOrder = (Order) intent.getParcelableExtra("other");
        username = intent.getStringExtra("username");


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //assign relevant xml widgets
        fare = findViewById(R.id.textPrice);
        duration = findViewById(R.id.textTime);
        callButton = findViewById(R.id.buttonCall);
        bookButton = findViewById(R.id.buttonBook);


        //Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
        LatLng location = new LatLng(-34.01, 151.01);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker for arbitrary location"));
        drawPath(sydney, location);
        //get the distance between the two points
        double distance = calculateDistance(sydney, location);
        //display the distane in the text field
        duration.setText("Distance (KM): " + distance);
        //calculate arbitrary price based on distance
        double price = 2.5 * distance;

        fare.setText("Price: $" + price);
    }


    public void callButton(View view) {
        //arbitrary number since number is entered by user but not associated with an order/driver
        String phoneNumber = "412345678";
        //intent to open dial pad with the number (used ACTION_DIAL rather than ACTION_CALL, as there is no need to actually call and the code is the same)
        Intent intent = new Intent(Intent.ACTION_DIAL);
        //uniform resource identifier used for phone number
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    public void bookButton(View view)
    {
        //static payment rather than dynamic for example (only way to implement mandatory min value payment as far as i could determine)
        String paymentPortalLink = "https://buy.stripe.com/aEU4iF1AZfgd97WcMM";
        //intent to open the payment link in a web browser
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(paymentPortalLink));
        startActivity(intent);
    }
    private void drawPath(LatLng startLatLng, LatLng endLatLng) {
        //new PolylineOptions object
        PolylineOptions options = new PolylineOptions();
        //setting colour, width, and visibility of line
        options.color(Color.parseColor("#CC0000FF"));
        options.width(5);
        options.visible(true);
        //adding the LatLng objects
        options.add(startLatLng);
        options.add(endLatLng);
        //adding line to map
        mMap.addPolyline(options);
    }

    private double calculateDistance(LatLng startLatLng, LatLng endLatLng) {
        //start location
        Location startLocation = new Location("start");
        startLocation.setLatitude(startLatLng.latitude);
        startLocation.setLongitude(startLatLng.longitude);

        //end location
        Location endLocation = new Location("destination");
        endLocation.setLatitude(endLatLng.latitude);
        endLocation.setLongitude(endLatLng.longitude);
        //distance in meters
        double distanceM = startLocation.distanceTo(endLocation);
        double distanceKm = distanceM / 1000;
        return distanceKm;
    }
}