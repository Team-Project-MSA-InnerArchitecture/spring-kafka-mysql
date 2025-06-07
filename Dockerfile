# 1단계: 빌드용 스테이지
FROM gradle:8.2.1-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# 2단계: 실행용 스테이지
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
