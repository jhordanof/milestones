FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/redpontis-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-mysql.sh wait-for-mysql.sh

RUN apt-get update && apt-get install -y netcat-openbsd
RUN chmod +x wait-for-mysql.sh

CMD ["./wait-for-mysql.sh", "db", "3306", "java", "-jar", "app.jar"]
