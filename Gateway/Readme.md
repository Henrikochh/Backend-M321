# Gateway – Backend Modul

Dieses Repository enthält das **API Gateway Modul** innerhalb des Schulprojekts *Autovermietung*.
Es wurde mit **Spring Cloud Gateway (Java)** entwickelt und dient als zentraler Einstiegspunkt für alle externen Anfragen an die Microservices.

---

## Überblick

Das Gateway übernimmt wichtige Funktionen wie **Anfragen-Routing**, **Authentifizierung und Autorisierung**, **Lastverteilung** und **Sicherheitsrichtlinien**. Es leitet Anfragen an die entsprechenden Backend-Dienste weiter und schützt die internen Microservices.

---

## Tech Stack

- **Backend:** Spring Cloud Gateway (Java 17+)
- **Service Discovery:** Optional (z.B. Eureka)
- **Build Tool:** Gradle
- **Containerization:** Docker

---

## Hauptfunktionen

| Funktion | Beschreibung |
|-----------|---------------|
| **Anfragen-Routing** | Leitet eingehende HTTP-Anfragen an die korrekten Microservices weiter. |
| **Authentifizierung & Autorisierung** | Überprüft Benutzeridentität und Berechtigungen vor der Weiterleitung von Anfragen. |
| **Lastverteilung** | Verteilt Anfragen auf mehrere Instanzen eines Dienstes. |
| **Sicherheitsrichtlinien** | Implementiert Sicherheitsmaßnahmen wie Ratenbegrenzung und Schutz vor bekannten Angriffen. |
| **Cross-Cutting Concerns** | Zentralisiert Aspekte wie Logging und Monitoring. |

---

## Architektur

Das Modul implementiert das **API Gateway Pattern** und agiert als Reverse Proxy für die nachgelagerten Microservices.

---

## API-Endpoints (Beispiele)

| Methode | Endpoint | Beschreibung |
|----------|-----------|---------------|
| `GET` | `/api/users/{id}` | Leitet Anfragen an den Benutzerverwaltungsdienst weiter |
| `POST` | `/api/vehicles` | Leitet Anfragen an den Fahrzeugverwaltungsdienst weiter |
| `GET` | `/api/bookings` | Leitet Anfragen an den Buchungssystemdienst weiter |
