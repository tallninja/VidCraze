/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 12:25 AM
 */
package com.vidcraze.domain;

import io.micronaut.data.annotation.MappedEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "views")
public class View {

    @Id @GeneratedValue
    private Integer id;

    @Column(name = "username", nullable = false)
    private String user;

    @ManyToOne
    @JoinColumn(name = "video", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Video video;

}
