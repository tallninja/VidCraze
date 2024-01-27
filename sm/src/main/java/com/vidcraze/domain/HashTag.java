/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 9:35 AM
 */
package com.vidcraze.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

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

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "hashTag")
    private List<Subscription> subscriptions;

}
