INSERT INTO theme (id, title, description) VALUES
('e947bfb8-ec0a-4d1b-8f55-e9b18f19324e', 'theme1', 'theme1 description'),
('437121d0-463b-4343-a416-813818dcf4b1', 'theme2', 'theme2 description'),
('68078eae-8e37-4e60-93a0-256ce00d8029', 'theme3', 'theme3 description'),
('68078eae-8e37-4e60-93a0-256ce00d8030', 'theme4', 'theme4 description');

INSERT INTO consensus (id, theme_id, due_date, state) VALUES
('7a4f17b2-87f4-4069-a696-f38771e5849d', 'e947bfb8-ec0a-4d1b-8f55-e9b18f19324e', DATEADD('DAY', +2, CURRENT_TIMESTAMP()), 0),
('539ddf5c-c283-40fa-962a-61ea262c354c', '437121d0-463b-4343-a416-813818dcf4b1', DATEADD('DAY', +2, CURRENT_TIMESTAMP()), 0),
('a3d0bbba-868e-4045-9554-e74369fa36c5', '68078eae-8e37-4e60-93a0-256ce00d8029', DATEADD('SECOND', -2, CURRENT_TIMESTAMP()), 0);

INSERT INTO consensus_participant_votes (consensus_id, participant, vote) VALUES
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme1')), 'user1@tecnocampus.cat', 3),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme1')), 'user2@tecnocampus.cat', 3),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme1')), 'user3@tecnocampus.cat', 3);

INSERT INTO consensus_participant_votes (consensus_id, participant, vote) VALUES
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme2')), 'user1@tecnocampus.cat', 3),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme2')), 'user2@tecnocampus.cat', 3),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme2')), 'user3@tecnocampus.cat', 3);

INSERT INTO consensus_participant_votes (consensus_id, participant, vote) VALUES
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme3')), 'user1@tecnocampus.cat', 0),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme3')), 'user2@tecnocampus.cat', 1),
((SELECT id FROM consensus WHERE theme_id = (SELECT id FROM theme WHERE title = 'theme3')), 'user3@tecnocampus.cat', 3);
