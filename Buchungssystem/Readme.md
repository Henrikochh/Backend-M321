# Buchungssystem – Backend Modul

Dieses Repository enthält das **Backend-Modul für das Buchungssystem** innerhalb des Schulprojekts *Autovermietung*.
Es wurde mit **Spring Boot (Java)** entwickelt und ist für die Verwaltung sämtlicher Buchungen und deren Status im System zuständig.

---

## Überblick

Das Buchungssystem ermöglicht das **Anlegen, Bearbeiten, Löschen und Abrufen von Buchungen** sowie die Verwaltung ihres aktuellen **Status** (*bestätigt*, *ausstehend*, *storniert*).
Alle Funktionen werden über standardisierte **REST-Schnittstellen (CRUD-Operationen)** bereitgestellt.

---

## Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Database:** PostgreSQL
- **Testing:** JUnit 5
- **Build Tool:** Gradle
- **Containerization:** Docker
- **Database Migration:** Flyway

---

## Hauptfunktionen

| Funktion | Beschreibung |
|-----------|---------------|
| **Buchungen anlegen** | POST-Endpoint zum Erstellen neuer Buchungen mit Attributen wie Benutzer-ID, Fahrzeug-ID, Mietzeitraum. |
| **Buchungen abrufen** | GET-Endpoints zur Abfrage aller oder einzelner Buchungen. |
| **Buchungen aktualisieren** | PUT/PATCH-Endpoints zum Ändern von Buchungsinformationen oder Status. |
| **Buchungen löschen** | DELETE-Endpoint zum Entfernen von Buchungen. |
| **Statusverwaltung** | Verwaltung des Buchungsstatus (z.B. *bestätigt*, *ausstehend*, *storniert*). |
| **Verfügbarkeitsprüfung** | Überprüfung der Fahrzeugverfügbarkeit für einen bestimmten Zeitraum. |

---

## Architektur

Das Modul ist nach dem klassischen **Spring-Boot-Layered-Architecture-Pattern** aufgebaut:

com.CarRental.Buchungssystem
┣ controller → REST-Controller für HTTP-Anfragen
┣ service → Geschäftslogik und Validierungen
┣ repository → Datenbankzugriff (Spring Data JPA)
┗ model/entity → Buchungs-Entität und Datenmodell

---

## API-Endpoints (Beispiele)

| Methode | Endpoint | Beschreibung |
|----------|-----------|---------------|
| `GET` | `/api/bookings` | Gibt alle Buchungen zurück |
| `GET` | `/api/bookings/{id}` | Gibt eine einzelne Buchung anhand der ID zurück |
| `POST` | `/api/bookings` | Legt eine neue Buchung an |
| `PUT` | `/api/bookings/{id}` | Aktualisiert eine bestehende Buchung |
| `DELETE` | `/api/bookings/{id}` | Löscht eine Buchung |
