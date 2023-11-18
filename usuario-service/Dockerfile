FROM ubuntu:latest
LABEL authors="Yamila"
COPY target/usuarioservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8088
CMD ["java", "-jar", "app.jar"]
ENTRYPOINT ["top", "-b"]