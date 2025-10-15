# 🎬 CineMatch Swing V3 (Deluxe)

UI **Swing** moderne (FlatLaf), stockage **JSON**, intégration **TMDB**, IA **Ollama (Qwen)**.

## Prérequis
- Java 17+ / Maven 3.8+
- Ollama :
  ```bash
  ollama pull qwen2.5:7b-instruct
  ollama serve
  ```
- (Optionnel mais recommandé) **TMDB** : crée une clé et exporte :
  ```bash
  export TMDB_API_KEY=ta_cle_tmdb
  export TMDB_REGION=FR   # par défaut FR
  ```

## Lancer
```bash
mvn clean package
mvn exec:java
```
ou
```bash
java -jar target/cinematch-swing-v3-1.0.0.jar
```

## Fonctionnalités
- Thème moderne **FlatLaf** (sombre par défaut)
- 3 outils : similaire, **Tinder infini**, liste d’envies + descriptions IA
- **Historique** des actions
- **JSON**: `storage.json` (titres + statut + date)
- **TMDB** pour films similaires, affiches et plateformes (si `TMDB_API_KEY`)
- **Posters** chargés depuis TMDB (image.tmdb.org)

## Statuts
- `envie`, `pas_interesse`, `deja_vu`, `aime`

