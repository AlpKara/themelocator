package edu.illinois.cs.cs125.spring2019.lab12;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Main class for our UI design lab.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Lab12:Main";

    /** Request queue for our API requests. */
    private static RequestQueue requestQueue;
    public String ip;
    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the queue for our API requests
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.location);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startAPICall1();
                startAPICall2(ip);
            }
        });

//        findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "You are currently in Illinois.", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    void startAPICall1() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://api.ipify.org/?format=json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone1(response);
                        }
                    },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void startAPICall2(final String ipAddress) {
        try {
            //isanbul "88.247.102.214"
            //seol "211.209.61.204"
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://ipinfo.io/" + ipAddress + "/json",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone2(response);
                        }
                    },new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone1(final JSONObject response) {
        try {

            Log.d(TAG, response.toString(2));

            ip = response.get("ip").toString();

        } catch (JSONException ignored) { }
    }

    /**
     *
     * @param response asd.
     */
    void apiCallDone2(final JSONObject response) {
        try {
            final MediaPlayer sound1 = MediaPlayer.create(this, R.raw.il_sound);
            final MediaPlayer sound2 = MediaPlayer.create(this, R.raw.ny_sound);
            final MediaPlayer sound3 = MediaPlayer.create(this, R.raw.tx_sound);
            final MediaPlayer sound4 = MediaPlayer.create(this, R.raw.az_sound);
            final MediaPlayer sound5 = MediaPlayer.create(this, R.raw.ca_sound);
            final MediaPlayer sound6 = MediaPlayer.create(this, R.raw.md_sound);
            final MediaPlayer sound7 = MediaPlayer.create(this, R.raw.al_sound);
            final MediaPlayer sound8 = MediaPlayer.create(this, R.raw.fl_sound);

            final MediaPlayer soundIst = MediaPlayer.create(this, R.raw.ist_sound);
            final MediaPlayer soundSel = MediaPlayer.create(this, R.raw.sel_sound);
            final MediaPlayer soundBer = MediaPlayer.create(this, R.raw.ber_sound);
            final MediaPlayer soundLon = MediaPlayer.create(this, R.raw.lon_sound);
            final MediaPlayer soundMos = MediaPlayer.create(this, R.raw.mos_sound);
            final MediaPlayer soundPar = MediaPlayer.create(this, R.raw.par_sound);
            final MediaPlayer soundRom = MediaPlayer.create(this, R.raw.rom_sound);
            TextView txt = (TextView) findViewById(R.id.t);

            Log.d(TAG, response.toString(2));
            String state;
            if (response.get("country").toString().equals("US")) {
                state = response.get("region").toString();
            } else {
                state = response.get("city").toString();
            }


            txt.setText("You are currently in " + state + "!");
            switch (state) {
                case "Illinois":
                    sound1.start();
                    break;
                case "New York":
                    sound2.start();
                    break;
                case "Texas":
                    sound3.start();
                    break;
                case "Arizona":
                    sound4.start();
                    break;
                case "California":
                    sound5.start();
                    break;
                case "Maryland":
                    sound6.start();
                    break;
                case "Alabama":
                    sound7.start();
                    break;
                case "Florida":
                    sound8.start();
                    System.out.println("yazma");
                    break;
                case "Istanbul":
                    soundIst.start();
                    System.out.println("yaz");
                    break;
                case "Seoul":
                    soundSel.start();
                    break;
                case "Berlin":
                    soundBer.start();
                    break;
                case "London":
                    soundLon.start();
                    break;
                case "Paris":
                    soundPar.start();
                    break;
                case "Moscow":
                    soundMos.start();
                    break;
                case "Rome":
                    soundRom.start();
                    break;
                default:
                    break;
            }

        } catch (JSONException ignored) { }
    }

//    /**
//     * Checks the current location.
//     * @param json the JSON returned by the Microsoft Cognitive Services API
//     * @return the current location
//     */
//    public static String currentLocation(final java.lang.String json) {
//        if (json == null) {
//            return null;
//        }
//        JsonParser parser = new JsonParser();
//        JSONObject json = (JSONObject) parser.parse("");
//    }





}

