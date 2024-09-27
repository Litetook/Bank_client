INSERT INTO USERS (user_id, name, email, password)
VALUES
(NEXTVAL('user_sequence'), :name, :email, password)
RETURNING user_id;