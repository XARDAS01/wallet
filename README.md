# Wallet Service

## Описание проекта

Wallet Service — это приложение к тестовому заданию по управлению кошельками. 

## Требования

- Docker 20.10 или новее
- Docker Compose 1.29 или новее

## Установка и запуск с использованием Docker

### 1. Клонирование репозитория

Склонируйте репозиторий на ваш локальный компьютер:

```properties
git clone git@github.com:XARDAS01/wallet.git
cd wallet
cd wallet-devops
cd docker
```

### 2. Билд и запуск проекта

```properties
docker-compose up --build -d
```

### 3. Логи

```properties
docker logs -f <containerId>
```

### 4. Остановка приложения

```properties
docker-compose down
```

## Swagger API

### Swagger доступен по ссылке
```properties
http://localhost:8081/swagger-ui/index.html#/
```