/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 9:33 AM
 */
package com.vidcraze.dtos;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;


@Data
@Builder
@Serdeable
public class SubscriptionDTO {

    private Integer id;

    private String user;

    private SmHashTagDTO hashTag;

}
