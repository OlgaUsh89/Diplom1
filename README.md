
## Процедура запуска автотестов ##

1. Запустить IntelliJ IDEA
2. Запустить виртуальную машину PuTTY
3. Запустить контейнеры в виртуальной машине командой `docker-compose up`
4. Запустить DBeaver, создать соединения с MySQL, PostgreSQL.
5. Запустить приложение в командной строке IntelliJ IDEA командами:

`java -jar aqa-shop.jar -P:jdbc.url=jdbc:mysql://185.119.57.64:3306/app` (для запуска с подключением к MySQL)

`java -jar aqa-shop.jar -P:jdbc.url=jdbc:postgresql://185.119.57.64:5432/app` (для запуска с подключением к PostgreSQL)

6. Запустить тесты во вкладке Terminal командой:

`./gradlew clean test -DdbUrl=jdbc:mysql://185.119.57.64:3306/app` (для запуска с подключением к MySQL)

`./gradlew clean test -DdbUrl=jdbc:postgresql://185.119.57.64:5432/app` (для запуска с подключением к PostgreSQL)

7. Получить отчет после полного завершения тестов командой `./gradlew allureReport`, а далее - `./gradlew allureServe
8. Закрыть отчет командой `Ctrl + C`, подтвердить выход, нажав на `Y`
9. Закрыть приложение командой `CTRL + C` в первой вкладке Terminal
10.  После остановки приложения остановить контейнеры командой `docker-compose down`
