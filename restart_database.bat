@echo off
:: Obtener la ruta donde está el script
set "script_dir=%~dp0"

:: Buscar el archivo de backup más reciente en la carpeta del script
for /f "delims=" %%I in ('dir /b /o-d "%script_dir%backup_*.sql" 2^>nul') do (
    set "backup_file=%script_dir%%%I"
    goto :found
)

:: Si no encuentra un backup, muestra un mensaje y sale
echo No se encontró ningún archivo de backup en la carpeta.
pause
exit /b

:found
echo Restaurando backup desde: %backup_file%

:: Restaurar el backup en PostgreSQL dentro de Docker
docker exec -i postgres_gasuq psql -U admin -d gasuq < "%backup_file%"

echo Restauración completada.
pause
