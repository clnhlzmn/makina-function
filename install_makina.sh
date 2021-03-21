mkdir lib

curl https://raw.githubusercontent.com/clnhlzmn/makina/master/makina-compiler/out/artifacts/makina_compiler_jar/makina-compiler.jar --output lib/makina.jar

mvn install:install-file -Dfile=lib/makina.jar -DgroupId="xyz.colinholzman.makina" -DartifactId=makina-compiler -Dversion=0 -Dpackaging=jar -DgeneratePom=true -X
