# Création d'une API de Gestion de Tâches avec Spring Boot, MongoDB et Redis

## Titre du TP
Création d'une API RESTful avec Spring Boot, MongoDB et Redis

## Objectifs du TP
1. Mettre en place une API RESTful pour la gestion des tâches
2. Optimiser les performances avec un système de cache (Redis)
3. Dockeriser l'application pour un déploiement simplifié

## Outils et Technologies Nécessaires

### 1. Java Development Kit (JDK)
Version recommandée : **17+**

Installation :
- Télécharger depuis Oracle ou OpenJDK
- Sous Linux :
  ```bash
  sudo apt update
  sudo apt install openjdk-17-jdk
  ```
- Vérifiez l'installation :
  ```bash
  java -version
  ```

### 2. Maven
Utilisé pour gérer les dépendances du projet

Installation :
- Télécharger depuis Maven
- Sous Linux :
  ```bash
  sudo apt update
  sudo apt install maven
  ```
- Vérifiez l'installation :
  ```bash
  mvn -version
  ```

### 3. Spring Boot
- Framework pour créer l'API REST
- Inclus dans le projet via Maven

### 4. MongoDB
Base de données NoSQL pour stocker les tâches

Installation :
- Guide officiel MongoDB
- Sous Linux :
  ```bash
  sudo apt install -y mongodb
  sudo systemctl start mongodb
  sudo systemctl enable mongodb
  ```
- Vérifiez que MongoDB fonctionne :
  ```bash
  mongo
  ```

### 5. Redis
Système de cache en mémoire

Installation :
- Guide officiel Redis
- Sous Linux :
  ```bash
  sudo apt install redis
  sudo systemctl start redis
  sudo systemctl enable redis
  ```
- Vérifiez que Redis fonctionne :
  ```bash
  redis-cli ping
  ```

### 6. Docker et Docker Compose
Pour containeriser l'application

Installation :
- Guide officiel Docker
- Sous Linux :
  ```bash
  sudo apt update
  sudo apt install docker.io docker-compose
  sudo systemctl start docker
  sudo systemctl enable docker
  ```

### 7. Postman (ou équivalent)
- Pour tester les requêtes API
- Télécharger depuis Postman

## Structure du Projet
1. `TaskController` : Gestion des routes API (GET, POST, DELETE, PUT)
2. `TaskService` : Logique métier avec annotations pour la mise en cache
3. `TaskRepository` : Accès à la base de données MongoDB
4. **Docker Compose** : Fichier `docker-compose.yml` pour lancer MongoDB, Redis, et Spring Boot

## Étapes du TP

### 1. Cloner le projet
```bash
git clone https://github.com/ttacett/spring-redis-mongodb-demo.git
cd spring-redis-mongodb-demo
```

### 2. Configurer les outils
- Vérifiez que Java, Maven, MongoDB, Redis et Docker sont installés

### 3. Lancer MongoDB et Redis localement
```bash
sudo systemctl start mongodb
sudo systemctl start redis
```

### 4. Compiler et lancer l'application Spring Boot
```bash
mvn clean install
mvn spring-boot:run
```

### 5. Tester l'API avec Postman
- **POST** : Créer une tâche
- **GET** : Récupérer une tâche par son ID
- **PUT** : Modifier une tâche par son ID
- **DELETE** : Supprimer une tâche

### 6. Dockerisation (Optionnel)
Lancer avec Docker Compose :
```bash
docker-compose up --build
```
