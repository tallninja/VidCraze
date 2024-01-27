/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 10:21 AM
 */
package com.vidcraze.repository;

import com.vidcraze.domain.HashTag;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.Set;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {

    @Query("FROM HashTag h WHERE h.tag IN :tags")
    Set<HashTag> findAllByTags(Set<String> tags);

}
