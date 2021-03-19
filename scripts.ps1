#to install the makina-compiler jar
mvn install:install-file -Dfile=<path-to-jar> -DgroupId="xyz.colinholzman.makina" -DartifactId=makina-compiler -Dversion=0 -Dpackaging=jar -DgeneratePom=true -X

#to package for deployment
mvn package

#to deploy (after logging in and selecting project)
gcloud functions deploy MakinaCompile --trigger-http --entry-point xyz.colinholzman.makina.MakinaFunction --runtime java11 --source=target/deployment
