INSERT INTO users (user_id, username, password, enabled, role) VALUES
	('1', 'peter@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true, 'ROLE_USER'),
	('2', 'john@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true, 'ROLE_USER'),
	('3', 'katie@example.com', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', true, 'ROLE_USER'),
	('4', '$2a$10$P2GkxFjc2GUGCFq9JUHgbOlWWBUHN0LzKSFFQZNGPY6eiz.cpQ1tm', '$2a$10$iyHO73XAyI0rT18FKYUX2.gb2jRRQp5T4yU3BFKYmg4qtK1OyiWBm', true, 'ROLE_SIGNUP');

INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
  ('poc', 'secret', 'read,write', 'password,refresh_token', null, null, 36000, 36000, null, true),
  ('app', null, 'read,write', 'password,refresh_token', null, null, 60, 36000, null, true);

INSERT INTO people (person_id, name, age, username) VALUES 
	(1, 'Peter', 25, 'peter@example.com'),
	(2, 'John', 30, 'john@example.com'),
	(3, 'Katie', 18, 'katie@example.com');
	
INSERT INTO skills (skill_id, person_id, name, level) VALUES
	(1, 1, 'Juggling', 'GOOD'),
	(2, 1, 'Dancing', 'AWESOME'),
	(3, 2, 'Juggling', 'AWESOME'),
	(4, 2, 'Story-telling', 'GODLIKE'),
	(5, 3, 'Singing', 'GOOD');

INSERT INTO parties (party_id, location, party_date) VALUES 
	(1, 'Old Folks Club', '2016-09-20'),
	(2, 'Luxury Yacht Party', '2016-12-05');
	
INSERT INTO people_parties (person_id, party_id) VALUES
	(1, 1),
	(1, 2),
	(2, 1),
	(3, 2);
	