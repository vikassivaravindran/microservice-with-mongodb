FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/product-2.0.0-SNAPSHOT.jar product.jar
RUN sh -c 'touch /product.jar'
ENV JAVA_OPTS=""
EXPOSE 9015
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /product.jar" ]
