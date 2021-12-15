FROM gradle:7.3.0-jdk17 as builder
WORKDIR /source
COPY --chown=gradle:gradle build.gradle ./
COPY --chown=gradle:gradle settings.gradle ./
COPY --chown=gradle:gradle ./gradle ./
COPY --chown=gradle:gradle ./src ./src/
RUN gradle --no-daemon --console=plain bootJar

FROM openjdk:17-alpine3.14
ENV APP_ROOT "/edcm"
RUN addgroup --gid 1111 --system warehouse \
    && adduser -u 1111 --system --g warehouse warehouse \
    && mkdir --parents ${APP_ROOT} \
    && chown --recursive warehouse:warehouse ${APP_ROOT}
USER warehouse
WORKDIR ${APP_ROOT}
COPY --chown=warehouse:warehouse --from=builder /source/build/libs/edcm-backend.jar ./
EXPOSE 8080
ENTRYPOINT [ "java", "-server", "-Xmx2G", "-Xms2G", "-jar", "edcm-backend.jar" ]