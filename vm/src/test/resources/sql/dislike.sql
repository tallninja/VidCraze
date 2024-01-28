INSERT INTO videos (id, title, username)
VALUES
    (1, 'New Video 1', 'user 1'),
    (2, 'New Video 2', 'user 2'),
    (3, 'New Video 3', 'user 3');

INSERT INTO dislikes (id, username, video)
VALUES
    (11, 'user 1', 1),
    (12, 'user 2', 2),
    (13, 'user 1', 3);