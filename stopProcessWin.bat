for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081 ^| findstr "LISTENING" ') do taskkill /f /pid %%a
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr "LISTENING" ') do taskkill /f /pid %%a

