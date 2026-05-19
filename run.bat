@echo off
echo [Compiling Project...]
dir /s /b src\*.java > sources.txt
javac -d bin @sources.txt
del sources.txt

if %errorlevel% neq 0 (
    echo [Compilation Failed!]
    pause
    exit /b %errorlevel%
)

echo [Running Application...]
echo.
java -cp bin com.rpg.Main
echo.
pause
