/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 4:31 PM
 */
package com.vidcraze.dtos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@Serdeable
@Introspected
public class LikeDTO {

    private Integer id;
    private String user;
    private Integer video;
    private Set<String> hashTags;

}
