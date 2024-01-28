/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 6:25 PM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {

    @Query("FROM HashTag h ORDER BY h.likes DESC LIMIT 10")
    List<HashTag> findTopTenTrendingHashTags();

}
