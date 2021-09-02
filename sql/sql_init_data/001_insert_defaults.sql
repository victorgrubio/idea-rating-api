
USE `IdeaRatingDB` ;

INSERT INTO IdeaRatingDB.users(username) values
('ADMIN'), ('victorgarciar');

INSERT INTO IdeaRatingDB.evaluation_weights(id, name, weight) values
(1, 'LOW', 1),
(2, 'MEDIUM', 2),
(3, 'HIGH', 3);
