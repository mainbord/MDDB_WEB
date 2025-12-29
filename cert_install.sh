#!/bin/bash

# Скрипт с запросом пароля для keystore
echo "=== Импорт SSL сертификата в Java Keystore ==="

read -p "Введите хост (например, example.com): " HOST
read -p "Введите порт (по умолчанию 443): " PORT
PORT=${PORT:-443}

read -p "Введите алиас для сертификата (по умолчанию: ${HOST}-cert): " ALIAS
ALIAS=${ALIAS:-"${HOST}-cert"}

read -p "Путь к keystore (по умолчанию: \$JAVA_HOME/jre/lib/security/cacerts): " KEYSTORE_PATH
KEYSTORE_PATH=${KEYSTORE_PATH:-"$JAVA_HOME/jre/lib/security/cacerts"}

# Скачивание сертификата
echo "Получаю сертификат с $HOST:$PORT..."
openssl s_client -connect "${HOST}:${PORT}" -showcerts </dev/null 2>/dev/null | \
    openssl x509 -outform PEM > /tmp/temp_cert.crt

if [ $? -ne 0 ]; then
    echo "Ошибка: Не удалось получить сертификат"
    exit 1
fi

# Импорт (с запросом пароля)
echo "Импортирую сертификат..."
keytool -importcert -trustcacerts \
    -keystore "$KEYSTORE_PATH" \
    -alias "$ALIAS" \
    -file /tmp/temp_cert.crt

# Проверка
echo "Проверяю импорт..."
keytool -list -keystore "$KEYSTORE_PATH" -alias "$ALIAS"

# Очистка
rm -f /tmp/temp_cert.crt
echo "Готово!"