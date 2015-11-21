package com.example.test.homework_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.homework_1.beans.MyDataResult;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressLoading;
    private TextView Text;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.requestQueue = Volley.newRequestQueue(this);
        this.progressLoading = (ProgressBar) findViewById(R.id.progress_loading);
        this.Text = (TextView) findViewById(R.id.text);

        this.volley();
    }

    private void volley() {
        String requestURL = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=1316a13a-39cd-4db5-b663-930638f244c5&limit=10";

        final StringRequest getDataRequest = new StringRequest(
                Request.Method.GET,
                requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        Gson gson = new Gson();
                        MyDataResult myDataResult = gson.fromJson(response, MyDataResult.class);

                        String tmpResult = "";
                        for (MyDataResult.ResultItem resultItem : myDataResult.getResult().getResults()) {
                            tmpResult += resultItem.get_id() + "\n" + resultItem.S104NAME() + "\n" +resultItem.RESULT() + "\n\n";
                        }

                        Text.setText(tmpResult);

                        progressLoading.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressLoading.setVisibility(View.GONE);
                    }
                });
        new Thread(new Runnable() {
            @Override
            public void run() {
                progressLoading.setVisibility(View.VISIBLE);
            }
        }).start();
        requestQueue.add(getDataRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
