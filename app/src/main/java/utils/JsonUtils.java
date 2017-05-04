package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import DTO.LectureDTO;
import DTO.MeetingDTO;

public class JsonUtils {

    public static Object getObject(String response, String object) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject.get(object);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<LectureDTO> getObjectLectureArray(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            List<LectureDTO> result = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                LectureDTO lectureDTO = new LectureDTO();
                lectureDTO.setId(jsonObject.getLong("id"));
                lectureDTO.setPictureAddress(jsonObject.getJSONObject("lecturer").getString("pictureAddress"));
                lectureDTO.setLecturer(jsonObject.getJSONObject("lecturer").getString("title"));
                lectureDTO.setDate(jsonObject.getJSONObject("meeting").getString("date"));
                lectureDTO.setSubject(jsonObject.getString("masterSubject"));
                result.add(lectureDTO);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LectureDTO getObjectLecture(String response) {
        try {
            final JSONObject jsonObject = new JSONObject(response);
            LectureDTO lectureDTO = new LectureDTO();
            lectureDTO.setId(jsonObject.getLong("id"));
            lectureDTO.setPictureAddress(jsonObject.getJSONObject("lecturer").getString("pictureAddress"));
            lectureDTO.setLecturer(jsonObject.getJSONObject("lecturer").getString("title"));
            lectureDTO.setDate(jsonObject.getJSONObject("meeting").getString("date"));
            lectureDTO.setDecorum(jsonObject.getJSONObject("meeting").getJSONObject("decorum").getString("title"));
            lectureDTO.setSubject(jsonObject.getString("masterSubject"));
            lectureDTO.setText(jsonObject.isNull("text") ? "" : jsonObject.getJSONObject("text").getString("text"));
            if (!jsonObject.isNull("mp3OfLectures")) {
                for (int i = 0; i < jsonObject.getJSONArray("mp3OfLectures").length(); i++) {
                    if (jsonObject.getJSONArray("mp3OfLectures").getJSONObject(i).getJSONObject("mp3").getJSONObject("quality").getLong("id") == 2)
                        lectureDTO.setMp3Url(jsonObject.getJSONArray("mp3OfLectures").getJSONObject(i).getJSONObject("mp3").getString("path"));
                }
            }
            return lectureDTO;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<MeetingDTO> getObjectMeetingArray(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            List<MeetingDTO> result = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MeetingDTO meetingDTO = new MeetingDTO();
                meetingDTO.setId(jsonObject.getLong("id"));
                meetingDTO.setDate(jsonObject.getString("date"));
                meetingDTO.setDecorum(jsonObject.getJSONObject("decorum").getString("title"));
                meetingDTO.setPictureCount(jsonObject.getInt("pictureCount"));
                result.add(meetingDTO);
            }
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static MeetingDTO addAddressesToMeetingDto(MeetingDTO meetingDTO, String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            List<String> result = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                result.add(jsonObject.getString("address"));
            }
            meetingDTO.setAddresses(result);
            return meetingDTO;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
