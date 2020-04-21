FROM adoptopenjdk:14-jre-hotspot

ARG ARTIFACT
ENV JAR_FILE $ARTIFACT
ADD $ARTIFACT /app/
CMD exec java $JVM_OPTS -jar /app/$JAR_FILE
