CREATE EXTENSION pgcrypto;

CREATE TABLE "poolmate"."user" (
  "id"                  BIGSERIAL    NOT NULL,
  "created_at"          TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "created_by"          VARCHAR(255) NOT NULL,
  "modified_at"         TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "modified_by"         VARCHAR(255) NOT NULL,
  "deleted"             BOOLEAN      NOT NULL DEFAULT FALSE,
  "version"             BIGINT       NOT NULL DEFAULT 0,
  "first_name"          VARCHAR(255) NOT NULL,
  "last_name"           VARCHAR(255) NOT NULL,
  "email"               VARCHAR(255) NOT NULL UNIQUE,
  "hashed_password"     TEXT         NOT NULL,
  "account_expired"     BOOLEAN      NOT NULL DEFAULT FALSE,
  "account_locked"      BOOLEAN      NOT NULL DEFAULT FALSE,
  "credentials_expired" BOOLEAN      NOT NULL DEFAULT FALSE,
  "enabled"             BOOLEAN      NOT NULL DEFAULT TRUE,

  PRIMARY KEY ("id")
);

CREATE TABLE "poolmate"."role" (
  "id"          BIGSERIAL    NOT NULL,
  "created_at"  TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "created_by"  VARCHAR(255) NOT NULL,
  "modified_at" TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "modified_by" VARCHAR(255) NOT NULL,
  "deleted"     BOOLEAN      NOT NULL DEFAULT FALSE,
  "version"     BIGINT       NOT NULL DEFAULT 0,
  "name"        VARCHAR(255) NOT NULL UNIQUE,

  PRIMARY KEY ("id")
);

CREATE TABLE "poolmate"."user_role" (
  "user_id"     BIGINT       NOT NULL,
  "role_id"     BIGINT       NOT NULL,
  "created_at"  TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "created_by"  VARCHAR(255) NOT NULL,
  "modified_at" TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "modified_by" VARCHAR(255) NOT NULL,
  "deleted"     BOOLEAN      NOT NULL DEFAULT FALSE,
  "version"     BIGINT       NOT NULL DEFAULT 0,

  PRIMARY KEY ("user_id", "role_id"),
  FOREIGN KEY ("user_id") REFERENCES "poolmate"."user" ("id"),
  FOREIGN KEY ("role_id") REFERENCES "poolmate"."role" ("id")
);

CREATE TABLE "poolmate"."session" (
  "id"          BIGSERIAL    NOT NULL,
  "created_at"  TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "created_by"  VARCHAR(255) NOT NULL,
  "modified_at" TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "modified_by" VARCHAR(255) NOT NULL,
  "deleted"     BOOLEAN      NOT NULL DEFAULT FALSE,
  "version"     BIGINT       NOT NULL DEFAULT 0,
  "user_id"     BIGINT       NOT NULL,
  "date"        DATE         NOT NULL,
  "pool_length" INT          NOT NULL DEFAULT 50,
  "calories"    INT          NOT NULL DEFAULT 0,

  PRIMARY KEY ("id"),
  FOREIGN KEY ("user_id") REFERENCES "poolmate"."user" ("id")
);

CREATE TABLE "poolmate"."session_set" (
  "id"               BIGSERIAL    NOT NULL,
  "created_at"       TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "created_by"       VARCHAR(255) NOT NULL,
  "modified_at"      TIMESTAMP    NOT NULL DEFAULT current_timestamp,
  "modified_by"      VARCHAR(255) NOT NULL,
  "deleted"          BOOLEAN      NOT NULL DEFAULT FALSE,
  "version"          BIGINT       NOT NULL DEFAULT 0,
  "session_id"       BIGINT       NOT NULL,
  "number"           INT          NOT NULL,
  "swimming_time"    BIGINT       NOT NULL DEFAULT 0,
  "rest_time"        BIGINT       NOT NULL DEFAULT 0,
  "laps"             INT          NOT NULL DEFAULT 0,
  "average_strokes"  INT          NOT NULL DEFAULT 0,
  "speed"            INT          NOT NULL DEFAULT 0,
  "efficiency_index" INT          NOT NULL DEFAULT 0,

  PRIMARY KEY ("id"),
  FOREIGN KEY ("session_id") REFERENCES "poolmate"."session" ("id")
);
