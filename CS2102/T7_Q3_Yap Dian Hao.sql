-- q3
CREATE FUNCTION exceed_max_hours() RETURNS TRIGGER AS $$
DECLARE
    maxHours INTEGER;
BEGIN
    SELECT hours INTO maxHours
    FROM WorkType
    WHERE wid = NEW.wid;
    IF (NEW.hours > maxHours) THEN
        NEW.hours := maxHours;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER max_hours_trigger
BEFORE INSERT OR UPDATE ON Works
FOR EACH ROW EXECUTE FUNCTION exceed_max_hours();
