Добавить валидации:

Для всех id - @NotNull при обновлении сущности, @Null при создании.

BankBookDto.number - не пустой

BankBookDto.amount - больше или равен 0

Создать сущность в БД currency в новой схеме dict с полями id (сиквенс из БД) и name (имя валюты, USD и т.д.). Реализовать репозиторий и заменить в CurrencyValidator Set на наш репозиторий и выполнять проверку на наличие значения в БД.

Добавить сущность в БД bank_book для BankBookEntity (все поля должны быть обязательные выполнить валидацию), добавить репозиторий в BankBookService (добавить маппинг для Entity и Dto) и переписать все операции на использование репозитория (ветка JPA)


Реализовать сервис, который будет выполнять переводы между двумя bank_book (добавить проверку одинаковых валют и баланса) или двумя user (тут необходимо добавить проверку на наличие у пользователя счета с данной валютой и баланс) (ветка TransactionService)
