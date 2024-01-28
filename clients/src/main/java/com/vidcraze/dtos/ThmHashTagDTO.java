/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 6:21 PM
 */
package com.vidcraze.dtos;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Data
@Builder
@Serdeable
public class ThmHashTagDTO {

    private String tag;

    private Integer likes;

}
