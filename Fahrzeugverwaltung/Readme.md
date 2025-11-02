# Fahrzeugverwaltung

Der Microservice "Fahrzeugverwaltung" ist die zentrale Komponente für die Verwaltung des Fahrzeugbestands in der Car-Rental-Anwendung. Er ist verantwortlich für das Hinzufügen, Aktualisieren, Löschen und Abrufen von Fahrzeugdaten.

## Funktionalitäten

- **Fahrzeugmanagement:** Hinzufügen, Bearbeiten und Entfernen von Fahrzeugen aus dem System.
- **Fahrzeugabfrage:** Abrufen einer Liste aller Fahrzeuge oder der Details eines einzelnen Fahrzeugs.
- **Status-Tracking:** Verfolgung des aktuellen Status eines Fahrzeugs (z.B. verfügbar, vermietet, in Wartung).

## Technologien

- **Sprache:** Java
- **Framework:** Spring Boot
- **Build-Tool:** Maven
- **Datenbank:** PostgreSQL
- **Containerisierung:** Docker

## API-Endpunkte

Die Funktionalitäten werden über eine RESTful-API mit dem Basis-Pfad `/api/fahrzeuge` bereitgestellt.

| Methode | Endpoint              | Beschreibung                                                                                                                            |
|---------|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `GET`   | `/`                   | Gibt eine Liste aller im System vorhandenen Fahrzeuge zurück.                                                                           |
| `GET`   | `/{id}`               | Gibt die vollständigen Details eines einzelnen Fahrzeugs anhand seiner eindeutigen ID zurück.                                             |
| `POST`  | `/`                   | Erstellt ein neues Fahrzeug. Die Fahrzeugdaten (z.B. Marke, Modell, Kennzeichen) werden im Request-Body als JSON-Objekt erwartet.         |
| `PUT`   | `/{id}`               | Aktualisiert die Informationen eines bestehenden Fahrzeugs. Die neuen Daten werden im Request-Body als JSON-Objekt übergeben.             |
| `DELETE`| `/{id}`               | Löscht ein Fahrzeug anhand seiner ID endgültig aus der Datenbank.                                                                        |