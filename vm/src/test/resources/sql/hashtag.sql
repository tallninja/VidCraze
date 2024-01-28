INSERT INTO videos (id, title, username)
VALUES
    (1, 'New Video 1', 'user 1'),
    (2, 'New Video 2', 'user 2'),
    (3, 'New Video 3', 'user 3');

INSERT INTO hashtags ( tag )
VALUES ('Tag 1'), ('Tag 2'), ('Tag 3');

INSERT INTO video_hashtags (video, hashtag)
VALUES (1, 'Tag 1'), (2, 'Tag 2'), (3, 'Tag 3');