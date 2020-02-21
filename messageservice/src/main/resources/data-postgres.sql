INSERT INTO "chat" ("id", "name") VALUES
(0,	'user1, user2'),
(1,	'user1, user3');

INSERT INTO "chat_users" ("chat_id", "users") VALUES
(0,	'user1'),
(0,	'user2'),
(1,	'user1'),
(1,	'user3');

INSERT INTO "message" ("id", "data", "sender_id", "type", "chat_id") VALUES
(0,	'Hello!!',	'user1',	'TEXT',	0),
(1,	'Hey',	'3',	'TEXT',	1);