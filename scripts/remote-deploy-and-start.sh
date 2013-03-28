cd bloodtorrent-web
sh ./scripts/stopDropwizard.sh
mvn dbdeploy:update
nohup java -jar target/bloodtorrent-1.0.0-SNAPSHOT.jar server src/main/resources/configurations.yml &
