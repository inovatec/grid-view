@Echo off

REM Dados da instalacao
REM Arquivo de instalacao
set POSTGRE_SQL_FILE=%1
REM Pasta destino da instalacao do Postgre
set POSTGRE_SQL_DESTINATION_FOLDER=%2
REM Constante removendo as aspas do parametro anterior
set POSTGRE_SQL_DESTINATION_FOLDER_BIN=%POSTGRE_SQL_DESTINATION_FOLDER:"=%\bin
REM Arquivo SQL contendo o script de criacao do banco
set DATABASE_CREATE_SQL_FILE=%3

REM Dados do Servidor de Banco de Dados
set server=localhost
set database=postgres
set port=5432
set username=postgres
set password=pass@dmn3
set service_name=postgreSQL
set service_password=serv@pass2

REM Exibir mensagem de instalacao
echo Configurando servidor de banco de dados. Por favor, nao encerre a aplicacao...

REM Executar comando para instalacao do banco de dados
%POSTGRE_SQL_FILE% --unattendedmodeui minimal --mode unattended --superpassword %password% --servicename %service_name% --servicepassword %service_password% --serverport %port% --prefix %POSTGRE_SQL_DESTINATION_FOLDER% --datadir %POSTGRE_SQL_DESTINATION_FOLDER%\data

REM echo Configurando variaveis de ambiente...

REM Adicionando o path do PSQL as variaveis de ambiente
REM setx /M PATH "%PATH%;%POSTGRE_SQL_DESTINATION_FOLDER_BIN%"

REM Senha para utilizacao do usuario do banco como variavel temporaria
SET PGPASSWORD=%password%

echo Criando o banco de dados da aplicacao...

REM Run psql application
"%POSTGRE_SQL_DESTINATION_FOLDER_BIN%\psql" -h %server% -U %username% -d %database% -p %port% -f %DATABASE_CREATE_SQL_FILE%

echo Configuracoes do banco de dados finalizadas com sucesso!

exit