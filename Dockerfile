FROM tomcat:8.5.57-jdk8
COPY target/mobile-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"
ENTRYPOINT ["catalina.sh", "jpda", "run"]
