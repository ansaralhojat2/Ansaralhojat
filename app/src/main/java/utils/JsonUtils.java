package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import DTO.LectureDTO;

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
            return lectureDTO;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
