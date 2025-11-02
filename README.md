
# 📦 BitPacking - Integer Array Compression in Java

## 🧠 Overview

BitPacking est un projet Java visant à optimiser la transmission de tableaux d'entiers en les compressant tout en conservant un accès direct à chaque élément. Trois méthodes de compression ont été développées :

- **Split** : Compression sans chevauchement, chaque entier est encodé sur un nombre fixe de bits et stocké dans des entiers de 32 bits.
- **Overlap** : Compression avec chevauchement, les entiers peuvent être répartis sur deux entiers de 32 bits pour maximiser l’utilisation de l’espace.
- **Overflow** : Compression avec zone de débordement, les grandes valeurs sont stockées séparément pour éviter de pénaliser l’ensemble du tableau.

## 🛠️ Prérequis

- Java 17 ou supérieur
- Un IDE Java (IntelliJ, Eclipse, etc.) ou un terminal avec `javac` et `java`

## 📁 Structure du projet

bitpacking/ ├── compression/
│   ├── BitPacking.java              # Interface commune
│   ├── BitPackingSplit.java        # Méthode Split
│   ├── BitPackingOverlap.java      # Méthode Overlap
│   └── BitPackingOverflow\.java     # Méthode Overflow
├── factory/
│   └── BitPackingFactory.java      # Factory Pattern
├── benchmark/
│   └── Benchmark.java              # Benchmarks automatisés
├── Main.java                       # Point d’entrée principal

## 🚀 Utilisation

1. **Compilation du projet** :

javac bitpacking/**/*.java Main.java

2.  **Exécution du programme principal** :

java Main

Le programme exécutera automatiquement des benchmarks sur différentes méthodes de compression et affichera les résultats dans la console.

## 📊 Benchmarks

Le programme mesure :

*   Le temps de compression (`compress`)
*   Le temps de décompression (`decompress`)
*   Le temps d’accès à un élément (`get(i)`)
*   Le ratio de compression
*   Le seuil de latence à partir duquel la compression devient rentable

Les benchmarks sont réalisés sur des tableaux de tailles et de contenus variés (petites, moyennes et grandes valeurs).

## 🧩 Design Patterns utilisés

*   **Strategy Pattern** : Permet d'encapsuler les différentes stratégies de compression derrière une interface commune (`BitPacking`). Cela facilite l’extension et la maintenance du code.
*   **Factory Pattern** : Permet de créer dynamiquement une instance de la méthode de compression souhaitée via la classe `BitPackingFactory`.


## 👤 Auteur

*   Mathis Levesque Du Rostu
