mvn compile

mvn package

gcloud functions deploy MakinaCompile --trigger-http --entry-point xyz.colinholzman.makina.MakinaFunction --runtime java11 --source=target/deployment
