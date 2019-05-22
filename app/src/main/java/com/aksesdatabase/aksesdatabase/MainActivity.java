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
    String url = "http://192.168.5.25/input1/input.php";

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
                    JSONArray jsonArray = response.getJSONArray("anggota");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject mahasantri = jsonArray.getJSONObject(i);

                        String id = mahasantri.getString("id_anggota");
                        String nama = mahasantri.getString("nama_anggota");
                        String asalDaerah = mahasantri.getString("asal_daerah");
                        String kamar = mahasantri.getString("kelompok_kamar");
                        String saldo = mahasantri.getString("sisa_saldo");


                        textHasilJson.append("Id anggota : "+ id + "\nnama anggota : "+ nama + "\nAsal Daerah : "+ asalDaerah + "\nKamar : "+ kamar+"\nsisa Saldo : "+saldo+"\n\n");
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
                Toast.makeText(getApplicationContext(), "Error oyy", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
