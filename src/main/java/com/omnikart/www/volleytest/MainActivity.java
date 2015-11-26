package com.omnikart.www.volleytest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    TextView view_data;
    Button button_login;
    RequestQueue requestQueue;
    JSONObject params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_data= (TextView) findViewById(R.id.textView_data);
        button_login = (Button)findViewById(R.id.button_login);
        requestQueue = Volley.newRequestQueue(this);
        params = new JSONObject();
        try {
            params.put("username", "yashu1996@gmail.com");
            params.put("password", "yashgupta");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://testing.omnikart.com/index.php?route=api/login/customerlogin",
                        params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String success = null;
                                    String cookie = null;
                                    String error = null;
                                    if (response.has("success")) {
                                        success = response.getString("success");
                                    }
                                    if (response.has("cookie")) {
                                        cookie = response.getString("cookie");
                                    }
                                    if (response.has("error")) {
                                        error = response.getString("error");
                                    }


                                    view_data.append(success + "   1st   \n" + cookie + "    2nd     \n" + error + "    3rd     \n");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }


                );
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

}
