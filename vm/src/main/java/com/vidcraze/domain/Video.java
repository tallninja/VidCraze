/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:14 AM
 */
package com.vidcraze.domain;

import io.micronaut.data.annotation.MappedEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "videos")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "username", nullable = false)
    private String user;

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "video_hashtags",
            joinColumns = @JoinColumn(name = "video"),
            inverseJoinColumns = @JoinColumn(name = "hashtag")
    )
    private Set<HashTag> hashTags;

}
