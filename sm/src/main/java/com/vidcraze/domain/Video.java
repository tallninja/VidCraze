/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:14 AM
 */
package com.vidcraze.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@Serdeable
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class Video {

    @Id
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String user;

    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "video_hashtags",
            joinColumns = @JoinColumn(name = "video"),
            inverseJoinColumns = @JoinColumn(name = "hashtag")
    )
    private Set<HashTag> hashTags;

    private Integer likes;

}
