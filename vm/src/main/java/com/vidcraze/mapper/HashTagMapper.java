/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 1:22 AM
 */
package com.vidcraze.mapper;

import com.vidcraze.domain.HashTag;
import com.vidcraze.repository.HashTagRepository;
import jakarta.inject.Singleton;

@Singleton
public class HashTagMapper {

    private final HashTagRepository hashTagRepository;

    public HashTagMapper(HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public HashTag toHashTag(String tag) {
        return hashTagRepository
                .findById(tag)
                .orElseGet(
                        () -> hashTagRepository
                                .save(HashTag.builder().tag(tag).build())
                );
    }

}
