# Backend-M321
# Verteiltes System für eine Autovermietung (M321)

Ilija - Fahrzeugverwaltung 8080 5432 <br>
Noah  - Reporting/Statistik 8081 5433 <br>
Vale  - Benutzerverwaltung 8082 5434 <br>
Henri - Buchungssystem 8083 5435 <br>
Dieses Projekt ist eine im Rahmen des Moduls 321 entwickelte, verteilte Applikation für eine Autovermietung. Die Architektur basiert auf einem Microservice-Ansatz, bei dem jede Kernfunktionalität in einem eigenständigen Service gekapselt ist.

## Systemkomponenten

Das System besteht aus den folgenden Microservices:

- **Benutzerverwaltung:** Verwaltet Benutzerdaten (CRUD).
- **Fahrzeugverwaltung:** Verwaltet den Fahrzeugbestand.
- **Buchungssystem:** Wickelt die Reservierung und Buchung von Fahrzeugen ab.
- **Reporting:** Erstellt Statistiken und Berichte zu Auslastung und Umsatz.
- **API Gateway:** Dient als zentraler Eingangspunkt, der alle Anfragen an die entsprechenden Services weiterleitet.

## Technologien

- **Backend:** Spring Boot (Java)
- **Datenbank:** PostgreSQL (ein Schema pro Service)
- **Deployment:** Docker Compose
- **Kommunikation:** RESTful APIs

## Ausführung

Das gesamte System ist containerisiert und kann mit einem einzigen Befehl gestartet werden. Stellen Sie sicher, dass Docker und Docker Compose auf Ihrem System installiert sind.

Führen Sie im Hauptverzeichnis des Projekts den folgenden Befehl aus:

```bash
docker-compose up --build
```

Die Services sind anschliessend über das API-Gateway unter `http://localhost:8080` erreichbar.
