#FROM eclipse-temurin:17-jdk-jammy AS build
#ENV HOME=/usr/app
#ENV APP_FILE wallet-service-0.0.1-SNAPSHOT.jar
#RUN mkdir -p $HOME
#WORKDIR $HOME
#ADD . $HOME
#RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package
#RUN ls -l /usr/app/target
#
#FROM eclipse-temurin:17-jre-jammy
#ARG JAR_FILE=/usr/app/target/$APP_FILE
#COPY --from=build $JAR_FILE /app/$APP_FILE
#EXPOSE 8080
#ENTRYPOINT java -jar /app/$APP_FILE

FROM eclipse-temurin:17-jdk-jammy
ENV APP_FILE wallet-service-0.0.1-SNAPSHOT.jar
ENV APP_HOME /app
EXPOSE 8080
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
RUN curl https://google.com
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]