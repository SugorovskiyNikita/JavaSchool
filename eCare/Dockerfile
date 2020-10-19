FROM tomcat:9.0.38-jdk8
COPY target/eCare-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"
ENTRYPOINT ["catalina.sh", "jpda", "run"]
