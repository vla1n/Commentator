package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Модель запроса для получения записей */
@Getter
@Setter
@AllArgsConstructor
public class GetWallsRequest implements VkApiRequest {

    /** Идентификатор сообщества, на чьей стене находится запись, к которой необходимо добавить комментарий */
    @JsonProperty("owner_id")
    private int ownerId;

    /** Смещение, необходимое для выборки определённого подмножества записей */
    private int offset;

    /** Количество записей, которое необходимо получить. Максимальное значение: 100 */
    private int count;

    public GetWallsRequest(int ownerId) {
        this.ownerId = ownerId;
    }

}
