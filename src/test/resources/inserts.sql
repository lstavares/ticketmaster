INSERT INTO severity (id, level) VALUES
        (1, 'Issue high'),
        (2, 'High'),
        (3, 'Medium'),
        (4, 'Low');

INSERT INTO category (name) VALUES ('Category 1'), ('Category 2');
INSERT INTO category (name, parent_id) VALUES ('Subcategory 1', 1), ('Subcategory 2', 2);

INSERT INTO ticket (title, description, customer_id) VALUES ('', 'Description 1', 1);
