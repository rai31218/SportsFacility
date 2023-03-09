FROM openjdk:8
COPY docker/id_rsa /root/.ssh/ 
COPY docker/config /root/.ssh/ 
RUN apk update; apk --no-cache add curl; apk add openssh; chmod 600 /root/.ssh/id_rsa
RUN git config --global user.name "rai31218" && \
    git config --global user.email "deboparna.chakraborty1996@gmail.com"
LABEL maintainer="Rai"
VOLUME /tmp
#COPY UnlimitedJCEPolicyJDK8/* /usr/lib/jvm/java-1.8-openjdk/jre/lib/security/
EXPOSE 8888
ADD target/config-server.jar config-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/config-server.jar"]

