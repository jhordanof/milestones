host="$1"
port="$2"
shift 2

until nc -z "$host" "$port"; do
  echo "Esperando a que MySQL este disponible en el host '$host' y puerto $port"
  sleep 2
done

echo "MySQL esta disponible. Iniciando aplicacion..."
exec "$@"
