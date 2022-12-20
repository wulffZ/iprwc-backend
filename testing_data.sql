USE webshop;

SET FOREIGN_KEY_CHECKS = 0;
DELETE FROM user;
DELETE FROM user_roles;
DELETE FROM car;
DELETE FROM category;


INSERT INTO category (id, title, description) VALUES
    (1, "Sport cars", "For the driver who wants a car to be more than a mode of transport to get from point A to point B, for it to be an experience unto itself."),
    (2, "Luxury cars","For the passenger in the rear seat, that would like to recline and experience his car in absolute silence and comfort.");

INSERT INTO user (id, name, email, password) VALUES
    (1, "admin", "admin@mail.com", "$2a$12$h1UE/EEwniH9dzgrlUBfVudi4Oc9K0JDEBoL3kgcGQVkWcOZBZ76W"),
    (2, "user", "user@mail.com", "$2a$12$.mOnb.2BAqq77irG6VJuhOF.f5WxED/bykqJrZ1KiSsnngXxIYZ.K");

INSERT INTO car (id, title, description, category_id, engine_displacement, manufacturer, cylinders, year, km, thumbnail_uri) VALUES
    (1, "Lexus LS 400", "LS400 in spectacular state, 1 owner garage kept. This car can still go many comfortable kilometers.", 2, 4000, "Lexus / Toyota", 8, 1993, 178984, null),
    (2, "Honda NSX","Super rare sport car from legendary car brand Honda. With low km's on the clock this one is a collectors item.", 1, 3000, "Honda", 6, 1994, 47780, null);

INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2);

