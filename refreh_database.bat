@echo off
:: Obtener la ruta donde está el script
set "script_dir=%~dp0"

:: Obtener la fecha y hora actual en formato yyyy-MM-dd_HH-mm-ss
for /f "tokens=2 delims==" %%I in ('"wmic os get localdatetime /value"') do set datetime=%%I
set datetime=%datetime:~0,4%-%datetime:~4,2%-%datetime:~6,2%_%datetime:~8,2%-%datetime:~10,2%-%datetime:~12,2%

:: Crear backup de PostgreSQL en Docker en la misma carpeta del script
docker exec -t postgres_gasuq pg_dump -U admin -d gasuq > "%script_dir%backup_%datetime%.sql"

echo Backup creado en: %script_dir%
pause
