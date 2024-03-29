/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 1:12 AM
 */
package com.vidcraze.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
@Serdeable
@Introspected
public class PostVideoDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String user;

    @JsonProperty("hashtags")
    private Set<String> hashTags;

}
