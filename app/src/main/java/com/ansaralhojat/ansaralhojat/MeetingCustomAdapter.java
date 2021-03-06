/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.ansaralhojat.ansaralhojat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import DTO.LectureDTO;
import DTO.MeetingDTO;
import utils.JsonUtils;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class MeetingCustomAdapter extends RecyclerView.Adapter<MeetingCustomAdapter.ViewHolder> {
    public static Context context;
    public static MeetingsActivityFragmentActivity meetingsActivityFragmentActivity;
    private static final String TAG = "LectureCustomAdapter";

    private static List<MeetingDTO> meetingDTOs;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewMeetingDate;
        private final TextView textViewMeetingDecorum;
        private final TextView textViewMeetingPictureCount;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");

                    RequestQueue queue = Volley.newRequestQueue(context);
                    StringRequest requestAddress = new StringRequest(Request.Method.GET,
                            "http://ansaralhojat.com/rest/pictures?meetingId=" + meetingDTOs.get(getAdapterPosition()).getId(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(context, GalleryActivity.class);
                                    intent.putExtra("meeting", JsonUtils.addAddressesToMeetingDto(meetingDTOs.get(getAdapterPosition()), response));
                                    meetingsActivityFragmentActivity.startActivity(intent);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });
                    queue.add(requestAddress);
                }
            });
            textViewMeetingDate = (TextView) v.findViewById(R.id.txtView_meeting_date);
            textViewMeetingDecorum = (TextView) v.findViewById(R.id.txtView_meeting_decorum);
            textViewMeetingPictureCount = (TextView) v.findViewById(R.id.meeting_picture_count);
        }

        public TextView getDate() {
            return textViewMeetingDate;
        }

        public TextView getDecorum() {
            return textViewMeetingDecorum;
        }

        public TextView getPictureCount() {
            return textViewMeetingPictureCount;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param meetingDTOs {@link LectureDTO}[] containing the data to populate views to be used by RecyclerView.
     */
    public MeetingCustomAdapter(List<MeetingDTO> meetingDTOs) {
        this.meetingDTOs = meetingDTOs;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.meeting_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getDate().setText(meetingDTOs.get(position).getDate());
        viewHolder.getDecorum().setText(meetingDTOs.get(position).getDecorum());
        viewHolder.getPictureCount().setText(meetingDTOs.get(position).getPictureCount().toString());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return meetingDTOs.size();
    }
}
