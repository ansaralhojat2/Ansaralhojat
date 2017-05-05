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
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import DTO.LectureDTO;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class LectureCustomAdapter extends RecyclerView.Adapter<LectureCustomAdapter.ViewHolder> {
    public static Context context;
    public static LecturesActivityFragmentActivity lecturesActivityFragmentActivity;
    private static final String TAG = "LectureCustomAdapter";

    private static List<LectureDTO> lectureDTOs;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewLecturer;
        private final TextView textViewDate;
        private final TextView textViewSubject;

        public ViewHolder(View v) {
                super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    Intent intent = new Intent(context, LectureActivity.class);
                    intent.putExtra("khar", lectureDTOs.get(getAdapterPosition()).getId());
                    lecturesActivityFragmentActivity.startActivity(intent);
                }
            });
            textViewLecturer = (TextView) v.findViewById(R.id.txtView_lecturer);
            textViewDate = (TextView) v.findViewById(R.id.txtView_date);
            textViewSubject = (TextView) v.findViewById(R.id.txtView_subject);
        }

        public TextView getLecture() {
            return textViewLecturer;
        }

        public TextView getDate() {
            return textViewDate;
        }

        public TextView getSubject() {
            return textViewSubject;
        }
    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param lectureDTOs {@link LectureDTO}[] containing the data to populate views to be used by RecyclerView.
     */
    public LectureCustomAdapter(List<LectureDTO> lectureDTOs) {
        this.lectureDTOs = lectureDTOs;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lecture_row_item, viewGroup, false);

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
        viewHolder.getLecture().setText(lectureDTOs.get(position).getLecturer());
        viewHolder.getDate().setText(lectureDTOs.get(position).getDate());
        viewHolder.getSubject().setText(lectureDTOs.get(position).getSubject());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lectureDTOs.size();
    }
}
