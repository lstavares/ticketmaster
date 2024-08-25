#!/bin/sh

# wait-for-backend-app.sh

set -e

host="$1"
shift
cmd="$@"

until curl -sSf "http://$host:8080/ticketmaster/actuator/health" > /dev/null; do
  >&2 echo "Backend is unavailable - sleeping"
  sleep 5
done

>&2 echo "Backend is up - executing command"
exec $cmd
