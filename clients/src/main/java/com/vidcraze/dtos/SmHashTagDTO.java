/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 9:35 AM
 */
package com.vidcraze.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Data
@Builder
@Serdeable
public class SmHashTagDTO {

    private String tag;

    @JsonIgnore
    private List<SubscriptionDTO> subscriptionDTOS;

}
