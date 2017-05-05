package com.ansaralhojat.ansaralhojat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import utils.JsonUtils;

public class MainAppCompatActivity extends BaseAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.pb_main);
        final LinearLayout addressLayout = (LinearLayout) findViewById(R.id.address_group);
        final LinearLayout timeLayout = (LinearLayout) findViewById(R.id.time_group);
        final LinearLayout familyLayout = (LinearLayout) findViewById(R.id.family_group);
        final LinearLayout parkingLayout = (LinearLayout) findViewById(R.id.parking_group);
        final TextView qoran = (TextView) findViewById(R.id.remember_qoran);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest requestAddress = new StringRequest(Request.Method.GET,
                "http://ansaralhojat.com/rest/address",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            final String versionName = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
                            if (!versionName.equals(String.valueOf(JsonUtils.getObject(response, "version")))) {
                                Intent intent = new Intent(getApplicationContext(), PleaseUpdateActivity.class);
                                startActivity(intent);
                            }
                        } catch (PackageManager.NameNotFoundException e) {

                        }
                        String addressInfo = (String) JsonUtils.getObject(response, "addressInfo");
                        String timeInfo = (String) JsonUtils.getObject(response, "timeInfo");
                        Boolean family = (Boolean) JsonUtils.getObject(response, "family");
                        Boolean parking = (Boolean) JsonUtils.getObject(response, "parking");
                        final TextView txtViewAddressInfo = (TextView) findViewById(R.id.txtView_addressInfo);
                        final TextView txtViewTimeInfo = (TextView) findViewById(R.id.txtView_timeInfo);
                        final TextView txtViewFamily = (TextView) findViewById(R.id.txtView_family);
                        final TextView textParking = (TextView) findViewById(R.id.txtView_parking);
                        txtViewAddressInfo.setText(Html.fromHtml(addressInfo));
                        txtViewTimeInfo.setText(Html.fromHtml(timeInfo));
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
                "http://ansaralhojat.com:8080/image/android/main/main.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        final ImageView imageView = (ImageView) findViewById(R.id.main_image);
                        imageView.setImageBitmap(response);
                        progressBar.setVisibility(View.GONE);
                        addressLayout.setVisibility(View.VISIBLE);
                        timeLayout.setVisibility(View.VISIBLE);
                        familyLayout.setVisibility(View.VISIBLE);
                        parkingLayout.setVisibility(View.VISIBLE);
                        qoran.setVisibility(View.VISIBLE);
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
        return R.layout.activity_main;
    }
}
