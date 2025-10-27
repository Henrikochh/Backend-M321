# Reporting – Backend Modul

Dieses Repository enthält das **Backend-Modul für das Reporting** innerhalb des Schulprojekts *Autovermietung*.
Es wurde mit **Spring Boot (Java)** entwickelt und ist für die Generierung und Bereitstellung von Berichten und Analysen zuständig.

---

## Überblick

Das Reporting-Modul aggregiert Daten von verschiedenen Microservices (Benutzerverwaltung, Fahrzeugverwaltung, Buchungssystem), um **umfassende Berichte und Analysen** zu erstellen. Dies umfasst beispielsweise Umsatzberichte, Fahrzeugauslastung oder Benutzerstatistiken.

---

## Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Build Tool:** Gradle
- **Containerization:** Docker

---

## Hauptfunktionen

| Funktion | Beschreibung |
|-----------|---------------|
| **Berichtsgenerierung** | Erstellt verschiedene Arten von Berichten (z.B. Monatsumsatz, Fahrzeugauslastung). |
| **Datenaggregation** | Sammelt und verarbeitet Daten von anderen Diensten. |
| **Berichtsabruf** | GET-Endpoints zum Abrufen generierter Berichte. |
| **Analysefunktionen** | Bietet Endpunkte für grundlegende Datenanalysen. |

---

## Architektur

Das Modul agiert als eigenständiger Microservice, der Daten von anderen Diensten über deren APIs konsumiert und verarbeitet.

---

## API-Endpoints (Beispiele)

| Methode | Endpoint | Beschreibung |
|----------|-----------|---------------|
| `GET` | `/api/reports/sales` | Gibt einen Umsatzbericht zurück |
| `GET` | `/api/reports/vehicle-utilization` | Gibt einen Bericht zur Fahrzeugauslastung zurück |
| `GET` | `/api/reports/users` | Gibt einen Bericht über Benutzerstatistiken zurück |
