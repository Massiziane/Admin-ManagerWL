# WorkoutLog Manager

**WorkoutLog Manager** est une application de bureau JavaFX destinée aux administrateurs, permettant la gestion et la manipulation des données liées à l’entraînement.  
Elle offre une interface claire et complète pour consulter, modifier et manipuler toutes les informations du système.

## Fonctionnalités

- Connexion requise avec un **compte administrateur**  
- **CRUD complet** (Création, Lecture, Mise à jour, Suppression) pour toutes les tables de la base de données  
- Ajout, modification et suppression d’entrées  

## Prérequis

- **Java 25** ou version ultérieure  
- **JavaFX 25 SDK**  
- **Maven** (pour la compilation et l’exécution du projet)  

## Exécution de l’application

0. cloner le depot :
```bash
git clone https://github.com/Massiziane/Admin-ManagerWL.git
```

1. Ouvrez un terminal et accédez au dossier du projet :

```bash
cd WorkoutLog
```
2. Exécutez l’application à l’aide de Maven :
```bash
mvn clean javafx:run
```
---
## Structure du projet

**src/main/java/org/example/workoutlog** – Code source Java

**src/main/resources/org/example/workoutlog/assets** – Vues FXML et styles CSS

**src/main/java/org/example/workoutlog/controllers** – Contrôleurs pour les pages FXML

**src/main/java/org/example/workoutlog/dao** – Objets d'accès aux données pour l’interaction avec la base de données

**src/main/java/org/example/workoutlog/model** – Classes de modèle

**src/main/java/org/example/workoutlog/service** – Classes de service et de connexion à la base de données

**src/main/java/org/example/workoutlog/utils** – Classes utilitaires pour les boîtes de dialogue d’ajout/modification/suppression et la gestion des tableaux
