#!/bin/bash
set -e

DATA_DIR="/var/lib/kafka/data"

if [ ! -f "$DATA_DIR/meta.properties" ]; then
  kafka-storage.sh format \
    -t "$CLUSTER_ID" \
    -c /etc/kafka/kraft/server.properties \
    --ignore-formatted
fi

exec kafka-server-start.sh /etc/kafka/kraft/server.properties
