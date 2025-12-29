FROM apache/kafka:3.7.0

RUN echo "node.id=1" >> /etc/kafka/kraft/server.properties && \
    echo "process.roles=broker,controller" >> /etc/kafka/kraft/server.properties && \
    echo "listeners=PLAINTEXT://:9092,CONTROLLER://:9093" >> /etc/kafka/kraft/server.properties && \
    echo "advertised.listeners=PLAINTEXT://kafka:9092" >> /etc/kafka/kraft/server.properties && \
    echo "controller.listener.names=CONTROLLER" >> /etc/kafka/kraft/server.properties && \
    echo "controller.quorum.voters=1@kafka:9093" >> /etc/kafka/kraft/server.properties && \
    echo "log.dirs=/tmp/kraft-combined-logs" >> /etc/kafka/kraft/server.properties

COPY --chmod=755 format-storage.sh /tmp/format-storage.sh

CMD ["/tmp/format-storage.sh"]