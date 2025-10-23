#!/bin/bash

case $1 in
  compilar)
    javac -cp lib/db4o-8.1.249.16099-core-java5.jar -d out src/**/*.java && rm banco.db4o; rm sequencias.db4o
    ;;

  cadastrar)
    java -cp out:lib/db4o-8.1.249.16099-core-java5.jar appconsole.Cadastrar
    ;;

  listar)
    java -cp out:lib/db4o-8.1.249.16099-core-java5.jar appconsole.Listar
    ;;

  alterar)
    java -cp out:lib/db4o-8.1.249.16099-core-java5.jar appconsole.Alterar
    ;;

  apagar)
    java -cp out:lib/db4o-8.1.249.16099-core-java5.jar appconsole.Apagar
    ;;
  
  consultar)
    java -cp out:lib/db4o-8.1.249.16099-core-java5.jar appconsole.Consultar
    ;;

  *)
    echo "argumento inválido"
    ;;  
esac
