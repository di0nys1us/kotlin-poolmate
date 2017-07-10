INSERT INTO "poolmate"."user" ("created_by", "modified_by", "first_name", "last_name", "email", "hashed_password")
VALUES
  ('test@test', 'test@test', 'Test', 'Test', 'test@test',
   (SELECT crypt('secret', gen_salt('bf'))));

INSERT INTO "poolmate"."role" ("created_by", "modified_by", "name")
VALUES
  ('test@test', 'test@test', 'ADMIN'),
  ('test@test', 'test@test', 'USER');

INSERT INTO "poolmate"."user_role" (user_id, role_id, created_by, modified_by)
VALUES
  ((SELECT "id"
    FROM "poolmate"."user"
    WHERE email = 'test@test'), (SELECT "id"
                                 FROM "poolmate"."role"
                                 WHERE "name" = 'ADMIN'), 'test@test', 'test@test'),
  ((SELECT "id"
    FROM "poolmate"."user"
    WHERE email = 'test@test'), (SELECT "id"
                                 FROM "poolmate"."role"
                                 WHERE "name" = 'USER'), 'test@test', 'test@test');

INSERT INTO "poolmate"."session" ("created_by", "modified_by", "user_id", "date")
VALUES
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."user"
                              WHERE email = 'test@test'), '2016-05-01'),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."user"
                              WHERE email = 'test@test'), '2016-05-10'),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."user"
                              WHERE email = 'test@test'), '2016-05-12');

INSERT INTO "poolmate"."session_set" ("created_by", "modified_by", "session_id", "number", "swimming_time", "rest_time", "laps", "average_strokes", "speed", "efficiency_index")
VALUES
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-01'), 0, 120e9, 60e9, 2, 25, 110, 40),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-01'), 1, 240e9, 60e9, 4, 25, 105, 35),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-10'), 0, 260e9, 60e9, 4, 25, 115, 45),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-10'), 1, 480e9, 60e9, 6, 25, 95, 40),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-12'), 0, 480e9, 60e9, 6, 27, 95, 40),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-12'), 1, 260e9, 60e9, 4, 23, 85, 45),
  ('test@test', 'test@test', (SELECT "id"
                              FROM "poolmate"."session"
                              WHERE "date" = '2016-05-12'), 2, 120e9, 60e9, 2, 29, 75, 50);
