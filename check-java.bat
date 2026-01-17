@echo off
REM Quick Java compilation check - runs faster than full build
REM Windows version

echo 🔍 Java syntax kontrol ediliyor...
echo.

REM Run Gradle compileJava task (faster than full build)
call gradlew.bat compileJava --console=plain > %TEMP%\compile-output.txt 2>&1
set EXIT_CODE=%ERRORLEVEL%

echo.
if %EXIT_CODE% EQU 0 (
    echo ✅ Java kodu hatasız!
    echo.
    echo Özet:
    findstr /C:"BUILD SUCCESSFUL" %TEMP%\compile-output.txt
) else (
    echo ❌ Java derleme hataları bulundu!
    echo.
    echo Hatalar:
    findstr /C:"error:" %TEMP%\compile-output.txt | more
    echo.
    echo Daha fazla bilgi için: %TEMP%\compile-output.txt
)

exit /b %EXIT_CODE%
