package org.example;

import org.example.client.CommentatorClient;
import org.example.requesters.VkRequester;
import org.example.services.VkApiService;

public class CommentatorApplication {
    public static void main(String[] args) {
        //https://oauth.vk.com/authorize?client_id=8078415&display=page&redirect_uri=http://vk.com/feed&scope=wall&response_type=token&v=5.131&state=123456
        String token = "";
        int ownerId = -156287735;

        VkRequester requester = new VkRequester(token);
        VkApiService vkApiService = new VkApiService(requester);
        CommentatorClient client = new CommentatorClient(vkApiService);

        // client.setTime(500);
        client.addComment(ownerId, "+2");
    }
}
