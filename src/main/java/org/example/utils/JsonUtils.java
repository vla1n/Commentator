package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.models.VkApiRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtils {

    @SneakyThrows
    public static String requestToJSON(VkApiRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(request);
    }

    @SneakyThrows
    public static List<String> jsonToParamList(String json) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        return (ArrayList<String>) jsonObject.entrySet().stream().map(e -> e.toString()).collect(Collectors.toList());
    }

    @SneakyThrows
    public static List<Long> parseGetWallsRequest(String response) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject response1 = (JSONObject) jsonObject.get("response");
        JSONArray items = (JSONArray) response1.get("items");
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            JSONObject element = (JSONObject) items.get(i);
            ids.add((Long) element.get("id"));
        }
        return ids;
    }
}
