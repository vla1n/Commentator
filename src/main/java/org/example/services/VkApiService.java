package org.example.services;

import org.example.models.AddCommentRequest;
import org.example.models.GetWallsRequest;
import org.example.requesters.VkRequester;
import org.example.utils.JsonUtils;

import java.util.List;

public class VkApiService {

    private final VkRequester requester;

    public VkApiService(VkRequester requester) {
        this.requester = requester;
    }

    public List<Long> getWallsId(GetWallsRequest request) {
        String json = requester.sendGetRequest("wall.get", JsonUtils.requestToJSON(request));
        return JsonUtils.parseGetWallsRequest(json);
    }

    //TODO: ПОСМОТРЕТЬ РЕЗУЛЬТАТ
    public boolean addComment(AddCommentRequest request) {
        return !requester.sendPostRequest("wall.createComment", JsonUtils.requestToJSON(request)).equals("");
    }


}
