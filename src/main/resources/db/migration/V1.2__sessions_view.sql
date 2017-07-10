CREATE VIEW "poolmate"."sessions" AS
  SELECT
    s.*,
    count(t.id)                                                       AS total_sets,
    coalesce(sum(t.swimming_time), 0)                                 AS total_swimming_time,
    coalesce(sum(t.rest_time), 0)                                     AS total_rest_time,
    coalesce(sum(t.swimming_time), 0) + coalesce(sum(t.rest_time), 0) AS total_time,
    coalesce(sum(t.laps), 0)                                          AS total_laps,
    coalesce(sum(t.laps) * s.pool_length, 0)                          AS total_distance
  FROM "poolmate"."session" AS s
    LEFT OUTER JOIN "poolmate"."session_set" AS t ON s.id = t.session_id
  GROUP BY s.id
  ORDER BY s.id;
