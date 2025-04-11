call mvn clean package -DskipTests
call docker system prune -a -f
call docker-compose build
call docker-compose up