$env:JAVA_HOME = "C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\"
$env:Path = "C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin\;" + $env:Path

mvn compile
