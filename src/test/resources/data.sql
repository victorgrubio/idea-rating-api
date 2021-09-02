/* INSERTS */
INSERT INTO users(username) values
                                ('ADMIN'), ('victorgarciar');

INSERT INTO evaluation_weights(id, 'name', weight) values
                                                     (1, 'LOW', 1),
                                                     (2, 'MEDIUM', 2),
                                                     (3, 'HIGH', 3);

INSERT INTO ideas(id, title, description, user_id) values
                                                      (1, 'idea admin', 'description admin', 'ADMIN'),
                                                      (2, 'idea victor', 'description victor','victorgarciar');

INSERT INTO evaluation_sentences(content, idea_id, type, evaluation_weight_id) values
                                                                                   ('pro LOW 1', 1, 'PRO', 1),
                                                                                   ('pro MEDIUM 1', 1, 'PRO', 2),
                                                                                   ('pro HIGH 1', 1, 'PRO', 3),

                                                                                   ('pro LOW 2', 2, 'PRO', 1),
                                                                                   ('pro MEDIUM 2', 2, 'PRO', 2),
                                                                                   ('pro HIGH 2', 2, 'PRO', 3);
