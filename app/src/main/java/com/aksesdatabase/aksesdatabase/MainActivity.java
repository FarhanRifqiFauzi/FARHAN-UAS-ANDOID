package com.aksesdatabase.aksesdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView textHasilJson;
    private RequestQueue mQueue;
    String url = "http://localhost/UASANDROID.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mQueue = Volley.newRequestQueue(this);
        textHasilJson = findViewById(R.id.textJSON);

        Button tombolJson = (Button) findViewById(R.id.btnJSON);

        tombolJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uraiJSON();
            }
        });
    }

    private void uraiJSON  (){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("nama_anggota");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mahasantri = jsonArray.getJSONObject(i);

                        String id = mahasantri.getString("id");
                        String nama = mahasantri.getString("nama");
                        String asalDaerah = mahasantri.getString("asal_daerah");
                        String kamar = mahasantri.getString("kamar");



                        textHasilJson.append("Id : "+ id + "\nnama : "+ nama + "\nAsal Daerah : "+ asalDaerah + "\nKamar : "+ kamar+"\n\n");
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "anda gagal coba lagi", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
