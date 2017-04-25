package com.ansaralhojat.ansaralhojat;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import DTO.LectureDTO;
import utils.JsonUtils;

public class LectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Long khar = (Long) getIntent().getExtras().get("khar");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,
                "http://ansaralhojat.com/rest/lectures/" + khar,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        final LectureDTO lectureDTO = JsonUtils.getObjectLecture(response);
                        final TextView txtViewAbstract = (TextView) findViewById(R.id.txtView_abstract_lecture);
                        txtViewAbstract.setText(Html.fromHtml(lectureDTO.getText()));

                        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                        toolbar.setTitle(lectureDTO.getSubject());
                        setSupportActionBar(toolbar);

                        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu_details));
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                StringBuilder details = new StringBuilder(lectureDTO.getDate()).append(", ").append(lectureDTO.getDecorum()).append(", ").append(lectureDTO.getLecturer());
                                Snackbar.make(view, details.toString(), Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        queue.add(request);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse("http://vprbbc.streamguys.net:80/vprbbc24.mp3"));
        mediaPlayer.start();
        setContentView(R.layout.activity_lecture);
    }
}
