# Benutzerverwaltung – Backend Modul

Dieses Repository enthält das **Backend-Modul für die Benutzerverwaltung** innerhalb des Schulprojekts *Autovermietung*.
Es wurde mit **Spring Boot (Java)** entwickelt und ist für die Verwaltung sämtlicher Benutzerdaten und deren Authentifizierung im System zuständig.

---

## Überblick

Die Benutzerverwaltung ermöglicht das **Anlegen, Bearbeiten, Löschen und Abrufen von Benutzern** sowie die Verwaltung ihrer **Rollen** und die **Authentifizierung**.
Alle Funktionen werden über standardisierte **REST-Schnittstellen (CRUD-Operationen)** bereitgestellt.

---

## Tech Stack

- **Backend:** Spring Boot (Java 17+)
- **Database:** PostgreSQL
- **Testing:** JUnit 5
- **Build Tool:** Gradle
- **Containerization:** Docker

---

## Hauptfunktionen

| Funktion | Beschreibung |
|-----------|---------------|
| **Benutzer anlegen** | POST-Endpoint zum Erstellen neuer Benutzer mit Attributen wie Benutzername, Passwort, E-Mail, Rolle. |
| **Benutzer abrufen** | GET-Endpoints zur Abfrage aller oder einzelner Benutzer. |
| **Benutzer aktualisieren** | PUT/PATCH-Endpoints zum Ändern von Benutzerinformationen oder Rollen. |
| **Benutzer löschen** | DELETE-Endpoint zum Entfernen von Benutzern. |
| **Rollenverwaltung** | Zuweisung und Verwaltung von Benutzerrollen (z.B. ADMIN, USER). |
| **Authentifizierung** | Bereitstellung von Authentifizierungsmechanismen. |

---

## Architektur

Das Modul ist nach dem klassischen **Spring-Boot-Layered-Architecture-Pattern** aufgebaut:

com.CarRental.Benutzerverwaltung
┣ controller → REST-Controller für HTTP-Anfragen
┣ service → Geschäftslogik und Validierungen
┣ repository → Datenbankzugriff (Spring Data JPA)
┗ model/entity → Benutzer-Entität und Datenmodell

---

## API-Endpoints (Beispiele)

| Methode | Endpoint | Beschreibung |
|----------|-----------|---------------|
| `GET` | `/api/users` | Gibt alle Benutzer zurück |
| `GET` | `/api/users/{id}` | Gibt einen einzelnen Benutzer anhand der ID zurück |
| `POST` | `/api/users` | Legt einen neuen Benutzer an |
| `PUT` | `/api/users/{id}` | Aktualisiert einen bestehenden Benutzer |
| `DELETE` | `/api/users/{id}` | Löscht einen Benutzer |
