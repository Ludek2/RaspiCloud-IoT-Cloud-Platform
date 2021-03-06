FROM sgrio/ubuntu-base
MAINTAINER SgrAlpha <admin@mail.sgr.io>

ENV DEBIAN_FRONTEND noninteractive

ENV VERSION 8
ENV UPDATE 141
ENV BUILD 15
ENV SIG 336fa29ff2bb4ef291e347e091f7f4a7

ENV JAVA_HOME /usr/lib/jvm/java-${VERSION}-oracle
ENV JRE_HOME ${JAVA_HOME}

RUN apt-get update && apt-get install ca-certificates curl \
        -y --no-install-recommends && \
    curl --silent --location --retry 3 --cacert /etc/ssl/certs/GeoTrust_Global_CA.pem \
    --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
    http://download.oracle.com/otn-pub/java/jdk/"${VERSION}"u"${UPDATE}"-b"${BUILD}"/"${SIG}"/jre-"${VERSION}"u"${UPDATE}"-linux-x64.tar.gz \
    | tar xz -C /tmp && \
    mkdir -p /usr/lib/jvm && mv /tmp/jre1.${VERSION}.0_${UPDATE} "${JAVA_HOME}" && \
    apt-get autoclean && apt-get --purge -y autoremove && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

RUN update-alternatives --install "/usr/bin/java" "java" "${JRE_HOME}/bin/java" 1 && \
    update-alternatives --install "/usr/bin/javaws" "javaws" "${JRE_HOME}/bin/javaws" 1 && \
    update-alternatives --set java "${JAVA_HOME}/bin/java" && \
    update-alternatives --set javaws "${JAVA_HOME}/bin/javaws"

RUN mkdir -p /opt/diss-microservice/
WORKDIR /opt/diss-microservice/
COPY microservice.jar .

CMD java -jar /opt/diss-microservice/microservice.jar
