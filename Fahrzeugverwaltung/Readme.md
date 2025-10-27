# Fahrzeugverwaltung – Backend Modul

Dieses Repository enthält das **Backend-Modul für die Fahrzeugverwaltung** innerhalb des Schulprojekts *Autovermietung*.
Es wurde mit **Spring Boot (Java)** entwickelt und ist für die Verwaltung sämtlicher Fahrzeugdaten im System zuständig.

---

## Überblick

Die Fahrzeugverwaltung ermöglicht das **Anlegen, Bearbeiten, Löschen und Abrufen von Fahrzeugen** sowie die Verwaltung ihres aktuellen **Status** (*verfügbar*, *vermietet*, *in Wartung*).
Alle Funktionen werden über standardisierte **REST-Schnittstellen (CRUD-Operationen)** bereitgestellt und vom Frontend (React.js) konsumiert.

---

## Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Database:** PostgreSQL oder MySQL
- **Build Tool:** Maven
- **Containerization:** Docker

---

## Hauptfunktionen

| Funktion | Beschreibung |
|-----------|---------------|
| **Fahrzeuge anlegen** | POST-Endpoint zum Erstellen neuer Fahrzeuge mit Attributen wie Marke, Modell, Baujahr, Kennzeichen, Preis pro Tag etc. |
| **Fahrzeuge abrufen** | GET-Endpoints zur Abfrage aller oder einzelner Fahrzeuge. |
| **Fahrzeuge aktualisieren** | PUT/PATCH-Endpoints zum Ändern von Fahrzeuginformationen oder Status. |
| **Fahrzeuge löschen** | DELETE-Endpoint zum Entfernen nicht mehr verfügbarer Fahrzeuge. |
| **Statusverwaltung** | Verwaltung des Fahrzeugstatus (*verfügbar*, *vermietet*, *in Wartung*). |

---

## Architektur

Das Modul ist nach dem klassischen **Spring-Boot-Layered-Architecture-Pattern** aufgebaut:

com.example.autovermietung.vehicles
┣ controller → REST-Controller für HTTP-Anfragen
┣ service → Geschäftslogik und Validierungen
┣ repository → Datenbankzugriff (Spring Data JPA)
┗ model/entity → Fahrzeug-Entität und Datenmodell

---

## API-Endpoints (Beispiele)

| Methode | Endpoint | Beschreibung |
|----------|-----------|---------------|
| `GET` | `/api/fahrzeuge` | Gibt alle Fahrzeuge zurück |
| `GET` | `/api/fahrzeuge/{id}` | Gibt ein einzelnes Fahrzeug anhand der ID zurück |
| `POST` | `/api/fahrzeuge` | Legt ein neues Fahrzeug an |
| `PUT` | `/api/fahrzeuge/{id}` | Aktualisiert ein bestehendes Fahrzeug |
| `DELETE` | `/api/fahrzeuge/{id}` | Löscht ein Fahrzeug |