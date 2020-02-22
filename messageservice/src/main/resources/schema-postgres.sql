DROP TABLE IF EXISTS "chat";
CREATE TABLE "public"."chat" (
    "id" bigint NOT NULL,
    "name" character varying(255),
    CONSTRAINT "chat_pkey" PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "chat_users";
CREATE TABLE "public"."chat_users" (
    "chat_id" bigint NOT NULL,
    "users" character varying(255),
    FOREIGN KEY (chat_id) REFERENCES chat(id) NOT DEFERRABLE
);

CREATE UNIQUE ON chat_users (chat_id, users);

DROP TABLE IF EXISTS "message";
CREATE TABLE "public"."message" (
    "id" bigint NOT NULL,
    "chat_id" bigint NOT NULL,
    "data" character varying(255),
    "sender_id" character varying(255),
    "type" character varying(255) NOT NULL,
    CONSTRAINT "message_pkey" PRIMARY KEY ("id"),
    FOREIGN KEY (chat_id) REFERENCES chat(id) NOT DEFERRABLE
);