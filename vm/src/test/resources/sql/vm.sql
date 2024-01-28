INSERT INTO videos (id, title, username)
VALUES
    (11, 'New Video 1', 'user 1'),
    (12, 'New Video 2', 'user 2'),
    (33, 'New Video 3', 'user 3');

INSERT INTO views (id, username, video)
VALUES
    (11, 'user 1', 11),
    (12, 'user 2', 12),
    (13, 'user 1', 13);

INSERT INTO likes (id, username, video)
VALUES
    (11, 'user 1', 11),
    (12, 'user 2', 12),
    (13, 'user 1', 13);

INSERT INTO dislikes (id, username, video)
VALUES
    (11, 'user 1', 11),
    (12, 'user 2', 12),
    (13, 'user 1', 13);