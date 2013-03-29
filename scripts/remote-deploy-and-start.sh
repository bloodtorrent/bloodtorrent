cd bloodtorrent-web
sh ./scripts/stopDropwizard.sh
mvn dbdeploy:update
mvn antrun:run
