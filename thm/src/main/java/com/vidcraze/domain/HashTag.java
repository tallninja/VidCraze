/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 6:21 PM
 */
package com.vidcraze.domain;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@Serdeable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hashtags")
public class HashTag {

    @Id
    private String tag;

    private Integer likes;

}
