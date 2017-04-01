package com.ansaralhojat.ansaralhojat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import utils.JsonUtils;

public class Main2AppCompatActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest requestAddress = new StringRequest(Request.Method.GET,
                "http://ansaralhojat.com/rest/address",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String addressInfo = (String) JsonUtils.getObject(response, "addressInfo");
                        Boolean family = (Boolean) JsonUtils.getObject(response, "family");
                        Boolean parking = (Boolean) JsonUtils.getObject(response, "parking");
                        final TextView txtViewAddressInfo = (TextView) findViewById(R.id.txtView_addressInfo);
                        final TextView txtViewFamily = (TextView) findViewById(R.id.txtView_family);
                        final TextView textParking = (TextView) findViewById(R.id.txtView_parking);
                        txtViewAddressInfo.setText(Html.fromHtml(addressInfo));
                        txtViewFamily.setText(family ? "منعقد می باشد" : "منعقد نمی باشد");
                        textParking.setText(parking ? "دارد" : "ندارد");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        ImageRequest requestImage = new ImageRequest(
                "http://s4.picofile.com/file/8162176526/salavat_12.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        final ImageView imageView = (ImageView) findViewById(R.id.image);
                        imageView.setImageBitmap(response);
                    }
                },
                1028,
                1028,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(requestAddress);
        queue.add(requestImage);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main2;
    }
}
