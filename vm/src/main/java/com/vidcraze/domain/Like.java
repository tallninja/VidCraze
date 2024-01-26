/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:25 AM
 */
package com.vidcraze.domain;

import io.micronaut.data.annotation.MappedEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String user;

    @ManyToOne
    @JoinColumn(name = "video")
    private Video video;

}
