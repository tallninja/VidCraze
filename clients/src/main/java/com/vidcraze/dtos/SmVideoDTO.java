/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:14 AM
 */
package com.vidcraze.dtos;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Set;

@Data
@Builder
@Serdeable
public class SmVideoDTO {

    private Integer id;

    private String title;

    private String user;

    private Set<SmHashTagDTO> hashTags;

    private Integer likes;

}
