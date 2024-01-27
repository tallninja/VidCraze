/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/26/24 : 6:25 PM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface HashTagRepository extends JpaRepository<HashTag, String> {
}
