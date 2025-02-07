--liquibase formatted sql

--changeset anisimovSA:TRANSLATES.translate-view.1
DROP VIEW IF EXISTS translates.translates_view;
CREATE OR REPLACE VIEW translates.translates_view AS
SELECT t.origin,
       t.id AS translate_id,
       t.translate,
       ut.counter
FROM translates.user_translates ut
         JOIN translates.translates t ON t.id = ut.translate_id;


