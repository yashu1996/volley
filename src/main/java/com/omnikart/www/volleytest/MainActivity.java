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


public class MainActivity extends ActionBarActivity {

    TextView view_data;
    Button button_login;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view_data= (TextView) findViewById(R.id.textView_data);
        button_login = (Button)findViewById(R.id.button_login);
        requestQueue = Volley.newRequestQueue(this);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST,"http://testing.omnikart.com/app/index.php",
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                try{
                                    JSONObject obj = response.getJSONObject(0);
                                    String success = null;
                                    String id = null;
                                    String error = null;
                                    if(obj.has("success")){
                                    success = obj.getString("success");}
                                    if(obj.has("id")){
                                    id = obj.getString("id");}
                                    if(obj.has("error")){
                                    error = obj.getString("error");}


                                view_data.append(success+"   1st   \n"+id+"    2nd     \n"+error+"    3rd     \n");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                }


                        );
                requestQueue.add(jsonArrayRequest);
            }
        });
    }

}
