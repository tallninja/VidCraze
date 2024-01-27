/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 4:32 PM
 */
package com.vidcraze.dtos;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Serdeable
@Introspected
public class ViewDTO {

    private Integer id;
    private String user;
    private Integer video;

}
