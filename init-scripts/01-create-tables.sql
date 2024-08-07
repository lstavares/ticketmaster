-- Connect into ticket_management database
\c ticketmaster_database;

-- Create Customer table if not exists
DO $$ BEGIN
    CREATE TABLE IF NOT EXISTS Customer (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(255) NOT NULL
    );
EXCEPTION
    WHEN duplicate_table THEN RAISE NOTICE 'Table Customer already exists.';
END $$;

-- Create Severity table if not exists
DO $$ BEGIN
    CREATE TABLE IF NOT EXISTS Severity (
        id SERIAL PRIMARY KEY,
        level VARCHAR(50) NOT NULL
    );
EXCEPTION
    WHEN duplicate_table THEN RAISE NOTICE 'Table Severity already exists.';
END $$;

-- Create Category table if not exists
DO $$ BEGIN
    CREATE TABLE IF NOT EXISTS Category (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        parent_id INTEGER,
        FOREIGN KEY (parent_id) REFERENCES Category (id) ON DELETE CASCADE
    );
EXCEPTION
    WHEN duplicate_table THEN RAISE NOTICE 'Table Category already exists.';
END $$;

-- Create Ticket table if not exists
DO $$ BEGIN
    CREATE TABLE IF NOT EXISTS Ticket (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        customer_id INTEGER,
        category_id INTEGER,
        subcategory_id INTEGER,
        severity_id INTEGER,
        FOREIGN KEY (customer_id) REFERENCES Customer (id) ON DELETE SET NULL,
        FOREIGN KEY (category_id) REFERENCES Category (id) ON DELETE SET NULL,
        FOREIGN KEY (subcategory_id) REFERENCES Category (id) ON DELETE SET NULL,
        FOREIGN KEY (severity_id) REFERENCES Severity (id) ON DELETE SET NULL
    );
EXCEPTION
    WHEN duplicate_table THEN RAISE NOTICE 'Table Ticket already exists.';
END $$;
