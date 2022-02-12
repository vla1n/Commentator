package org.example.client;

import lombok.SneakyThrows;
import org.example.models.AddCommentRequest;
import org.example.models.GetWallsRequest;
import org.example.services.VkApiService;

public class CommentatorClient {

    private VkApiService vkApiService;

    /** Период между запросами, мс */
    private int time = 1000;

    public CommentatorClient(VkApiService vkApiService) {
        this.vkApiService = vkApiService;
    }

    @SneakyThrows
    public void addComment(int ownerId, String comment) {
        long currentEndWall = getEndIdWall(ownerId);

        while (true) {
            long idEndWall = getEndIdWall(ownerId); //id последний записи
            if (currentEndWall != idEndWall) {
                vkApiService.addComment(new AddCommentRequest(ownerId, idEndWall, comment));
                break;
                //currentEndWall = idEndWall;
            }
            Thread.sleep(time);
        }
    }

    //TODO: определиться с индексом
    private long getEndIdWall(int ownerId) {
        return vkApiService.getWallsId(new GetWallsRequest(ownerId, 0, 5)).get(0);
    }

    public void setTime(int time) {
        this.time = time;
    }
}
