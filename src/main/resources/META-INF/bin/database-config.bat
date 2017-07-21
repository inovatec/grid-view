@Echo off

REM Dados da instalacao
set POSTGRE_SQL_FILE=%1
set POSTGRE_SQL_DESTINATION_FOLDER=%2

REM Dados do Servidor de Banco de Dados
set server=localhost
set database=postgres
set port=5432
set username=postgres
set password=pass@dmn3
set service_name=postgreSQL
set service_password=serv@pass2

REM Exibir mensagem de instalacao
echo Por favor, aguarde enquanto instalamos o servidor de banco de dados da aplicacao...

REM Executar comando para instalacao do banco de dados
%POSTGRE_SQL_FILE% --unattendedmodeui minimal --mode unattended --superpassword %password% --servicename %service_name% --servicepassword %service_password% --serverport %port% --prefix %POSTGRE_SQL_DESTINATION_FOLDER% --datadir %POSTGRE_SQL_DESTINATION_FOLDER%\data

echo >
echo >
echo Criando o banco de dados da aplicacao...

REM Senha para utilizacao do usuario do banco como variavel temporaria
SET PGPASSWORD=%password%

REM Run psql application
%POSTGRE_SQL_DESTINATION_FOLDER%\bin\psql.exe -h %server% -U %username% -d %database% -p %port% --password %password%

REM Criar banco de dados
CREATE DATABASE grid WITH ENCODING 'UTF8' TEMPLATE template1;

\q

echo >
echo >
echo Configuracoes do banco de dados concluida.

exit