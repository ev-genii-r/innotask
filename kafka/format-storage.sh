#!/bin/bash

# Generate a cluster ID if not provided
if [ -z "$KAFKA_CLUSTER_ID" ]; then
    echo "Generating Kafka cluster ID..."
    KAFKA_CLUSTER_ID=$(/opt/kafka/bin/kafka-storage.sh random-uuid)
    echo "Generated cluster ID: $KAFKA_CLUSTER_ID"
    export KAFKA_CLUSTER_ID
fi

echo "Using Kafka cluster ID: $KAFKA_CLUSTER_ID"

# Format storage if not already formatted
if [ ! -f /tmp/kraft-combined-logs/meta.properties ]; then
    echo "Formatting Kafka storage for cluster ID $KAFKA_CLUSTER_ID..."
    /opt/kafka/bin/kafka-storage.sh format \
        -t "$KAFKA_CLUSTER_ID" \
        -c /tmp/kraft.properties \
        --ignore-formatted
    echo "Storage formatted successfully."
else
    echo "Kafka storage already formatted."
fi

echo "Starting Kafka with KRaft..."
echo "Configuration:"
cat /tmp/kraft.properties
echo ""

# Start Kafka
exec /opt/kafka/bin/kafka-server-start.sh /tmp/kraft.properties