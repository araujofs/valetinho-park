#!/bin/bash

# Script para testar todas as funcionalidades do App Console
# Valetinho Park System

echo "=========================================="
echo "  TESTE COMPLETO - APP CONSOLE"
echo "  Valetinho Park System"
echo "=========================================="
echo ""

# Verificar se o banco está rodando
echo "Verificando conexão com banco de dados..."
if ! docker ps | grep -q postgres; then
    echo "⚠️  Banco de dados não está rodando!"
    echo "Iniciando PostgreSQL..."
    sudo docker compose up -d
    echo "Aguardando banco inicializar..."
    sleep 5
fi

echo "✓ Banco de dados OK"
echo ""

# 1. CADASTRAR
echo "=========================================="
echo "1. CADASTRANDO DADOS DE EXEMPLO"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar" -q
echo ""
echo "Pressione ENTER para continuar..."
read

# 2. LISTAR
echo ""
echo "=========================================="
echo "2. LISTANDO TODOS OS DADOS"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar" -q
echo ""
echo "Pressione ENTER para continuar..."
read

# 3. CONSULTAR
echo ""
echo "=========================================="
echo "3. EXECUTANDO CONSULTAS"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar" -q
echo ""
echo "Pressione ENTER para continuar..."
read

# 4. ALTERAR
echo ""
echo "=========================================="
echo "4. DEMONSTRANDO ALTERAÇÕES"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar" -q
echo ""
echo "Pressione ENTER para continuar..."
read

# 5. APAGAR
echo ""
echo "=========================================="
echo "5. DEMONSTRANDO EXCLUSÕES"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar" -q
echo ""
echo "Pressione ENTER para continuar..."
read

# 6. LISTAR FINAL
echo ""
echo "=========================================="
echo "6. LISTANDO DADOS APÓS ALTERAÇÕES"
echo "=========================================="
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar" -q
echo ""

echo "=========================================="
echo "  TESTE COMPLETO FINALIZADO!"
echo "=========================================="
echo ""
echo "Resumo das operações:"
echo "  ✓ Dados cadastrados"
echo "  ✓ Listagem verificada"
echo "  ✓ Consultas executadas"
echo "  ✓ Alterações realizadas"
echo "  ✓ Exclusões testadas"
echo ""
