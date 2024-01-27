/**
 * Author: Ernest Wambua
 * Email: ernestwambua2@gmail.com
 * Date: 1/27/24 : 11:41 AM
 */
package com.vidcraze;

import com.vidcraze.domain.HashTag;
import com.vidcraze.domain.Subscription;
import com.vidcraze.repository.HashTagRepository;
import com.vidcraze.repository.SubscriptionRepository;
import com.vidcraze.domain.Video;
import com.vidcraze.repository.VideoRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Singleton
public class SmService {

    private final SubscriptionRepository subscriptionRepository;
    private final VideoRepository videoRepository;
    private final HashTagRepository hashTagRepository;
    private final SmProducer smProducer;

    public SmService(SubscriptionRepository subscriptionRepository, VideoRepository videoRepository, HashTagRepository hashTagRepository, SmProducer smProducer) {
        this.subscriptionRepository = subscriptionRepository;
        this.videoRepository = videoRepository;
        this.hashTagRepository = hashTagRepository;
        this.smProducer = smProducer;
    }

    public Subscription subscribe(String user, String hashTag) throws Exception {
        Optional<HashTag> tag = hashTagRepository.findById(hashTag);
        if (tag.isPresent()) {
            HashTag _hashTag = tag.get();
            Subscription subscription = Subscription.builder()
                    .user(user)
                    .hashTag(_hashTag)
                    .build();
            Subscription newSub = subscriptionRepository.save(subscription);
            log.info("Subscribed: " + newSub);
            smProducer.subscribe(newSub.getId(), newSub);
            return newSub;
        }
        throw new Exception("Hashtag " + hashTag + " not found.");
    }

    public List<Subscription> subscribe(String user, Set<HashTag> hashTags) {
        Set<Subscription> subscriptions = new HashSet<>();

        for (HashTag hashTag : hashTags) {
            subscriptions.add(Subscription.builder()
                    .user(user)
                    .hashTag(hashTag)
                    .build()
            );
        }

        return subscriptionRepository.saveAll(subscriptions);
    }

    public void unsubscribe(Integer id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        subscriptionRepository.deleteById(id);
        log.info("Unsubscribed: " + subscription);
        subscription.ifPresent(value -> smProducer.unsubscribe(value.getId(), value));
    }

    public List<Video> getTrendingVideosBySubscription(Integer id) throws Exception {
        Subscription subscription = findSubscriptionById(id);
        return videoRepository.findTopVideosWithHashTag(subscription.getHashTag());
    }

    public List<Video> getTrendingVideos(HashTag hashTag) {
        return videoRepository.findTopVideosWithHashTag(hashTag);
    }

    private Subscription findSubscriptionById(Integer id) throws Exception {
        return subscriptionRepository.findById(id).orElseThrow(() -> {
                String message = "Subscription " + id + "not found.";
                log.error(message);
                return new Exception(message);
        });
    }

    private List<Subscription> findAllSubscriptionsByUser(String user) {
        return subscriptionRepository.findByUser(user);
    }

}
