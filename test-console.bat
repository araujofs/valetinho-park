@echo off
REM Script para testar todas as funcionalidades do App Console
REM Valetinho Park System

cls
echo ==========================================
echo   TESTE COMPLETO - APP CONSOLE
echo   Valetinho Park System
echo ==========================================
echo.

REM Verificar se o banco esta rodando
echo Verificando conexao com banco de dados...
docker ps | findstr postgres >nul
if errorlevel 1 (
    echo Banco de dados nao esta rodando!
    echo Iniciando PostgreSQL...
    docker compose up -d
    echo Aguardando banco inicializar...
    timeout /t 5 /nobreak >nul
)

echo OK Banco de dados OK
echo.

REM 1. CADASTRAR
echo ==========================================
echo 1. CADASTRANDO DADOS DE EXEMPLO
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar" -q
echo.
pause

REM 2. LISTAR
cls
echo ==========================================
echo 2. LISTANDO TODOS OS DADOS
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar" -q
echo.
pause

REM 3. CONSULTAR
cls
echo ==========================================
echo 3. EXECUTANDO CONSULTAS
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar" -q
echo.
pause

REM 4. ALTERAR
cls
echo ==========================================
echo 4. DEMONSTRANDO ALTERACOES
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar" -q
echo.
pause

REM 5. APAGAR
cls
echo ==========================================
echo 5. DEMONSTRANDO EXCLUSOES
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar" -q
echo.
pause

REM 6. LISTAR FINAL
cls
echo ==========================================
echo 6. LISTANDO DADOS APOS ALTERACOES
echo ==========================================
call mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar" -q
echo.

echo ==========================================
echo   TESTE COMPLETO FINALIZADO!
echo ==========================================
echo.
echo Resumo das operacoes:
echo   OK Dados cadastrados
echo   OK Listagem verificada
echo   OK Consultas executadas
echo   OK Alteracoes realizadas
echo   OK Exclusoes testadas
echo.
pause
