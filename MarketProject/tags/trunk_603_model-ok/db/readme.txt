-----------------------
Lancer le serveur de bd
-----------------------
java -cp hsqldb.jar org.hsqldb.Server -database.0 file:./isimarket -dbname.0 isimarket

-----------------------
Lancer l'agent hsqldb
-----------------------
java -cp hsqldb.jar org.hsqldb.util.DatabaseManager --url jdbc:hsqldb:hsql://localhost/isimarket