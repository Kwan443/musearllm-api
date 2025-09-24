# 创建新的生产镜像层
FROM openjdk:21-jdk

#ENV env="test"

# 设置容器启动时的工作目录
WORKDIR /app

# 从构建阶段复制编译好的JAR包到生产镜像
COPY ./target/musearllm-0.0.1-SNAPSHOT.jar /app/api.jar

# 指定运行环境, 要注意改
#ENV spring.profiles.active=${env}

# 定义容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "/app/api.jar"]

# 可选：设置环境变量，例如数据库连接、端口等
# ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dbhost:port/dbname
# ENV SERVER_PORT=8080
