ALTER TABLE system
    ADD CONSTRAINT uc_system_name UNIQUE (name);

CREATE UNIQUE INDEX idx_stationentity_system_unq ON station (system, name);

ALTER TABLE station
    DROP CONSTRAINT idx_stationentity_name_unq;

CREATE INDEX idx_stationentity_name ON station (name);
