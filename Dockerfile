FROM tomcat
COPY expensetracker-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/
WORKDIR /usr/local/tomcat/bin
CMD ["./startup.sh"]