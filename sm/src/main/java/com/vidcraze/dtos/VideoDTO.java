/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 1:11 AM
 */
package com.vidcraze.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@Serdeable
@Introspected
public class VideoDTO {

    private Integer id;

    private String title;

    private String user;

    private Integer views;

    private Integer likes;

    private Integer dislikes;

    @JsonProperty("hashtags")
    private Set<String> hashTags;

}
