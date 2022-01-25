#!/bin/bash
# wait for rabbit

set -e
host="$1"
shift
until rabbitmq-diagnostics ping; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

>&2 echo "Postgres is up - executing command"
exec "$@"