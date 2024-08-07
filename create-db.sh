#!/bin/bash
set -e

# Start the PostgreSQL server in the background
docker-entrypoint.sh postgres &

# Wait for PostgreSQL to start
echo "Waiting for PostgreSQL to start..."
sleep 10

# Check if the database exists
if psql -lqt -U "$POSTGRES_USER" | cut -d \| -f 1 | grep -qw ticketmaster_database; then
  echo "Database ticketmaster_database already exists"
else
  echo "Creating database ticketmaster_database"
  echo "line 18"
  psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE ticketmaster_database;
EOSQL
echo "line 21"
fi

# Wait for PostgreSQL to be ready
wait
