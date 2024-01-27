/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 7:01 PM
 */

package com.vidcraze;

import com.vidcraze.domain.Subscription;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface SmProducer {

    @Topic("subscribe")
    void subscribe(@KafkaKey Integer subscriptionId, Subscription subscription);

    @Topic("unsubscribe")
    void unsubscribe(@KafkaKey Integer subscriptionId, Subscription subscription);

}
