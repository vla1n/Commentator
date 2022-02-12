package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Модель запроса для создания комментария */
@Getter
@Setter
@AllArgsConstructor
public class AddCommentRequest implements VkApiRequest {

    /** Идентификатор сообщества, на чьей стене находится запись, к которой необходимо добавить комментарий. */
    @JsonProperty("owner_id")
    private int ownerId;

    /** Идентификатор записи на стене */
    @JsonProperty("post_id")
    private long postId;

    /** Текст комментария */
    private String message;
}
