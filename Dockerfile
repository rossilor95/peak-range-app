FROM gradle:8.4.0-jdk21 AS build

USER root
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build


FROM eclipse-temurin:21-alpine AS runtime

ENV APP_HOME=/home/app_user/app

USER root
RUN adduser --disabled-password app_user
RUN mkdir $APP_HOME
RUN chown --recursive app_user /home/app_user
COPY --from=build /home/gradle/src/build/libs/*.jar $APP_HOME/pif.jar

USER app_user
WORKDIR $APP_HOME
ENTRYPOINT ["java", "-jar", "pif.jar"] 