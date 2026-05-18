@echo off
echo [Compiling Project...]
javac src/com/rpg/Main.java

if %errorlevel% neq 0 (
  echo [Compilation Failed!]
  pause
  exit /b %errorlevel%
  )

echo [Running Application...]
echo.
java -cp src com.rpg.Main
echo.
pause
