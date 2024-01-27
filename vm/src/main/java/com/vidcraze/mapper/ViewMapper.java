/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 5:01 PM
 */
package com.vidcraze.mapper;

import com.vidcraze.domain.View;
import com.vidcraze.dtos.ViewDTO;
import jakarta.inject.Singleton;

@Singleton
public class ViewMapper {

    public ViewDTO toViewDTO(View view) {
        return ViewDTO.builder()
                .id(view.getId())
                .user(view.getUser())
                .video(view.getVideo().getId())
                .build();
    }

}
