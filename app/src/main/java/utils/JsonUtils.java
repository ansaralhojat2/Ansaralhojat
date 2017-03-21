package utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Object getObject(String response, String object) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            return jsonObject.get(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
