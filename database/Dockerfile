FROM neo4j:4.4.0

RUN cd plugins && \
    wget https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/4.4.0.11/apoc-4.4.0.11-all.jar && \
    cd ..

COPY cyphers import/cyphers
COPY conf/apoc.conf conf/apoc.conf
