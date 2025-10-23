#!/bin/bash

print_help() {
  cat <<EOF
Usage: $0 <command>

Available commands:
  compilar    Compile the Java sources into the 'out' directory (clears DB files)
  cadastrar   Run the appconsole.Cadastrar interactive console
  listar      Run the appconsole.Listar console
  alterar     Run the appconsole.Alterar console
  apagar      Run the appconsole.Apagar console
  consultar   Run the appconsole.Consultar console
  help, -h, --help
              Show this help message
EOF
}

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

  help|-h|--help)
    print_help
    ;;

  *)
    echo "Argumento inválido. Use '$0 help' para ver os comandos disponíveis."
    ;;  
esac
