#!/bin/bash
set -e
echo "Iniciando el script de inicialización de la base de datos...V5"

psql -h localhost -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER springboot WITH PASSWORD 'qwerty';
    CREATE DATABASE springboot;
    GRANT ALL PRIVILEGES ON DATABASE springboot TO springboot;
EOSQL

echo "Base de datos y usuario creados con éxito!"
