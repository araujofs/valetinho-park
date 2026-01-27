@echo off
REM ====================================
REM Valetinho Park - Scripts Uteis
REM ====================================

:menu
cls
echo.
echo ========================================
echo     VALETINHO PARK - MENU PRINCIPAL
echo ========================================
echo.
echo 1. Iniciar Banco de Dados (Docker)
echo 2. Parar Banco de Dados
echo 3. Ver Status do Banco
echo.
echo 4. Compilar Projeto
echo.
echo 5. Interface Grafica (Swing)
echo.
echo 6. Console - Cadastrar Dados
echo 7. Console - Listar Dados
echo 8. Console - Consultar Dados
echo 9. Console - Alterar Dados
echo 10. Console - Apagar Dados
echo.
echo 11. Limpar e Recompilar
echo 12. Reiniciar Banco (APAGA DADOS!)
echo.
echo 0. Sair
echo.
echo ========================================
set /p opcao="Escolha uma opcao: "

if "%opcao%"=="1" goto iniciar_banco
if "%opcao%"=="2" goto parar_banco
if "%opcao%"=="3" goto status_banco
if "%opcao%"=="4" goto compilar
if "%opcao%"=="5" goto swing
if "%opcao%"=="6" goto cadastrar
if "%opcao%"=="7" goto listar
if "%opcao%"=="8" goto consultar
if "%opcao%"=="9" goto alterar
if "%opcao%"=="10" goto apagar
if "%opcao%"=="11" goto limpar
if "%opcao%"=="12" goto reiniciar_banco
if "%opcao%"=="0" goto sair
goto menu

:iniciar_banco
cls
echo.
echo ========================================
echo   Iniciando PostgreSQL no Docker...
echo ========================================
echo.
docker-compose up -d
echo.
echo Aguarde alguns segundos...
timeout /t 5 /nobreak >nul
docker ps
echo.
pause
goto menu

:parar_banco
cls
echo.
echo ========================================
echo   Parando PostgreSQL...
echo ========================================
echo.
docker-compose down
echo.
pause
goto menu

:status_banco
cls
echo.
echo ========================================
echo   Status do Banco de Dados
echo ========================================
echo.
docker ps
echo.
echo ========================================
echo   Logs (ultimas 20 linhas):
echo ========================================
echo.
docker-compose logs --tail=20 db
echo.
pause
goto menu

:compilar
cls
echo.
echo ========================================
echo   Compilando Projeto...
echo ========================================
echo.
mvn clean compile
echo.
pause
goto menu

:swing
cls
echo.
echo ========================================
echo   Iniciando Interface Grafica...
echo ========================================
echo.
echo Aguarde a janela abrir...
echo (Pressione Ctrl+C para voltar ao menu)
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appswing.TelaPrincipal"
echo.
pause
goto menu

:cadastrar
cls
echo.
echo ========================================
echo   Cadastrando Dados de Exemplo...
echo ========================================
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Cadastrar"
echo.
pause
goto menu

:listar
cls
echo.
echo ========================================
echo   Listando Todos os Dados...
echo ========================================
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Listar"
echo.
pause
goto menu

:consultar
cls
echo.
echo ========================================
echo   Executando Consultas...
echo ========================================
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Consultar"
echo.
pause
goto menu

:alterar
cls
echo.
echo ========================================
echo   Demonstrando Alteracoes...
echo ========================================
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Alterar"
echo.
pause
goto menu

:apagar
cls
echo.
echo ========================================
echo   Demonstrando Exclusoes...
echo ========================================
echo.
mvn exec:java -Dexec.mainClass="br.com.valetinho.appconsole.Apagar"
echo.
pause
goto menu

:limpar
cls
echo.
echo ========================================
echo   Limpando e Recompilando...
echo ========================================
echo.
mvn clean compile
echo.
pause
goto menu

:reiniciar_banco
cls
echo.
echo ========================================
echo   ATENCAO: TODOS OS DADOS SERAO PERDIDOS!
echo ========================================
echo.
set /p confirma="Deseja continuar? (S/N): "
if /i "%confirma%"=="S" (
    echo.
    echo Parando container...
    docker-compose down -v
    echo.
    echo Iniciando container...
    docker-compose up -d
    echo.
    echo Banco reiniciado com sucesso!
    timeout /t 3 /nobreak >nul
) else (
    echo Operacao cancelada.
)
echo.
pause
goto menu

:sair
cls
echo.
echo ========================================
echo   Encerrando...
echo ========================================
echo.
echo Deseja parar o banco de dados? (S/N)
set /p parar="Escolha: "
if /i "%parar%"=="S" (
    docker-compose down
    echo Banco de dados parado.
)
echo.
echo Ate logo!
timeout /t 2 /nobreak >nul
exit
