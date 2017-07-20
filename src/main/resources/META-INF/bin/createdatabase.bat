@Echo off
set caminho=%1
set postgre_exe_file=%caminho%\postgresql-9.4.12-1-windows.exe

echo %postgre_exe_file%

msiexec /i %postgre_exe_file% /qr INTERNALLAUNCH=1 ADDLOCAL="server,nls,psql" PERMITREMOTE=1 CREATESERVICEUSER=1 DOSERVICE=1 DOINITDB=1 SERVICEDOMAIN="localhost" SERVICEACCOUNT="postgres" SERVICEPASSWORD="123" SUPERUSER="admin" SUPERPASSWORD="12345" ENCODING="UTF-8" TRANSFORMS=:lang_pt_br LISTENPORT=5432 BASEDIR="c:\base_bd" DATADIR="c:\base_bd\data"