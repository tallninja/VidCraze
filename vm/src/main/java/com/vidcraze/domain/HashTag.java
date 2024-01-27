/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:24 AM
 */
package com.vidcraze.domain;

import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.config.annotation.SerdeConfig;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hashtags")
public class HashTag {
    @Id
    private String tag;

    @ManyToMany(mappedBy = "hashTags", cascade = CascadeType.DETACH)
    private Set<Video> videos;

    @Override
    public String toString() {
        return tag;
    }
}
