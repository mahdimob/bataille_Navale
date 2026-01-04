# Jeu de Bataille Navale (Java)

Projet réalisé dans le cadre de la Licence Informatique. Ce logiciel implémente un jeu de Bataille Navale complet avec une architecture **MVC** (Modèle-Vue-Contrôleur) et un système d'écouteurs pour la mise à jour de l'interface.

## 👥 Membres de l'équipe
* **Mahdi MOBAREK**
* **Akcel ARAB**
* **Belaid AZIL**
* **Massinissa MEGHIRA**


## 🏗️ Architecture du projet
Le projet est organisé selon la structure suivante :
* `src/` : Code source Java (Modèle, Vue, Contrôleur, Écouteurs).
* `test/` : Tests unitaires JUnit.
* `lib/` : Bibliothèques externes (JUnit, Mockito).
* `rapport/` : Documentation et rapport de projet.
* `build/` : Fichiers compilés (générés automatiquement).

---

## 🚀 Commandes Ant disponibles

Ce projet utilise **Apache Ant** pour la gestion de la compilation et de l'exécution. Voici les commandes disponibles à la racine du projet :

### 🛠️ Compilation
```bash
ant compile


Voici un texte structuré et professionnel en Markdown que vous pouvez copier-coller directement dans votre fichier README.md sur GitHub. J'ai corrigé les petites fautes de frappe et organisé le contenu pour qu'il soit clair pour quiconque visite votre dépôt.

Markdown

# Jeu de Bataille Navale (Java)

Projet réalisé dans le cadre de la Licence Informatique. Ce logiciel implémente un jeu de Bataille Navale complet avec une architecture **MVC** (Modèle-Vue-Contrôleur) et un système d'écouteurs pour la mise à jour de l'interface.

## 👥 Membres de l'équipe
* **Akcel ARAB**
* **Belaid AZIL**
* **Massinissa MEGHIRA**
* **Mahdi MOBAREK**

## 🏗️ Architecture du projet
Le projet est organisé selon la structure suivante :
* `src/` : Code source Java (Modèle, Vue, Contrôleur, Écouteurs).
* `test/` : Tests unitaires JUnit.
* `lib/` : Bibliothèques externes (JUnit, Mockito).
* `rapport/` : Documentation et rapport de projet.
* `build/` : Fichiers compilés (générés automatiquement).

---

## 🚀 Commandes Ant disponibles

Ce projet utilise **Apache Ant** pour la gestion de la compilation et de l'exécution. Voici les commandes disponibles à la racine du projet :

### 🛠️ Compilation
```bash
ant compile
Compile les fichiers sources Java et génère les fichiers .class dans le dossier build/.

🖥️ Exécuter le programme (Interface Graphique)
Bash

ant run
Lance l'application principale avec l'interface graphique (Swing).

⌨️ Exécuter le mode Terminal
Bash

ant demo
Lance le programme en mode console pour tester les mécaniques de jeu dans le terminal.

🧪 Exécution des tests
Bash

ant test
Lance les tests unitaires pour vérifier le bon fonctionnement du modèle (notamment la classe BateauTest).

🧹 Nettoyer le projet
Bash

ant clean
Supprime le dossier build/ et les fichiers compilés pour remettre le projet à neuf.
