-- Q3
CREATE OR REPLACE FUNCTION list_r_avg()
RETURNS TABLE(stu_id integer, avg float) AS $$
DECLARE
    curs CURSOR FOR(SELECT sid, score from exams order by sid);
    r RECORD;
    prev_id INT;
BEGIN
    OPEN curs;
    prev_id := -1;
    LOOP
        FETCH curs INTO r;
        EXIT WHEN NOT FOUND;
        stu_id := r.sid;
        IF prev_id <> stu_id THEN avg := revised_avg(stu_id);
        ELSE CONTINUE;
        END IF;
        RETURN NEXT;
        prev_id := r.sid;
    END LOOP;
    CLOSE curs;
END;
$$ LANGUAGE plpgsql;
