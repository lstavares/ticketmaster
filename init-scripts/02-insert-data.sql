-- Connect into ticket_management database
\c ticketmaster_database;

-- Populating the Severity Table only if there is no data
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Severity) THEN
        INSERT INTO Severity (id, level) VALUES
        (1, 'Issue high'),
        (2, 'High'),
        (3, 'Medium'),
        (4, 'Low');
    END IF;
END $$;

-- Populating the Category Table only if there is no data
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Category) THEN
        -- Nível 1
        INSERT INTO Category (name) VALUES
        ('Hardware'),
        ('Software');

        -- Nível 2
        INSERT INTO Category (name, parent_id) VALUES
        ('Computadores', 1),
        ('Periféricos', 1),
        ('Sistemas Operacionais', 2),
        ('Aplicativos', 2);

        -- Nível 3
        INSERT INTO Category (name, parent_id) VALUES
        ('Desktops', 3),
        ('Laptops', 3),
        ('Impressoras', 4),
        ('Monitores', 4),
        ('Windows', 5),
        ('Linux', 5),
        ('Office', 6),
        ('Navegadores', 6);
    END IF;
END $$;

-- Populating the Tickets table only if it doesn't already exist
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Ticket) THEN
        INSERT INTO Ticket (title, description, created_at, customer_id, category_id, subcategory_id, severity_id) VALUES
        ('Computador não liga', 'O computador não está ligando após a última atualização.', '2024-08-01 10:00:00', 1, 3, 8, 1),
        ('Erro no Excel', 'O Excel está travando ao abrir planilhas grandes.', '2024-08-02 11:00:00', 2, 2, 13, 2),
        ('Problema na impressora', 'A impressora não está imprimindo corretamente.', '2024-08-03 12:00:00', 3, 4, 9, 3),
        ('Sistema operacional corrompido', 'O sistema operacional Windows está corrompido após uma queda de energia.', '2024-08-04 09:00:00', 1, 5, 11, 1),
        ('Falha ao iniciar o navegador', 'O navegador está travando ao ser iniciado.', '2024-08-05 14:00:00', 2, 6, 14, 2);
    END IF;
END $$;
