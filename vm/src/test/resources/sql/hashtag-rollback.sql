DELETE FROM video_hashtags
WHERE video IN (1, 2, 3);

DELETE FROM videos
WHERE id IN (1, 2, 3);

DELETE FROM hashtags
WHERE tag IN ('Tag 1', 'Tag 2', 'Tag 3');