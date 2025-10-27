# Reporting & Statistik Microservice

## Übersicht

Der Reporting & Statistik Microservice ist ein **stateless Spring Boot Service**, der Live-Auswertungen und Echtzeit-Statistiken für das Autovermietungssystem bereitstellt. Er aggregiert Daten von anderen Microservices über REST-APIs und generiert Berichte **on-demand ohne eigene Datenpersistierung**.

## Funktionen

### Revenue Reports (Live-Umsatzberichte)
- **Tägliche Umsatzberichte**: Live-Berechnung von täglichen Umsatzstatistiken
- **Zeitraumberichte**: On-demand Umsatzauswertungen für Datumsbereiche
- **Buchungsstatistiken**: Echtzeit-Analyse von aktiven und abgeschlossenen Buchungen

### Fahrzeugnutzungsberichte
- **Live-Auslastung**: Echtzeit-Berechnung der Fahrzeugauslastung
- **Top-Performance**: Live-Ranking der meistgenutzten Fahrzeuge
- **Nutzungstrends**: On-demand Analyse der Fahrzeugnutzung

### Kundenberichte
- **Live-Kundenanalytik**: Echtzeit-Kundenstatistiken aus aktuellen Daten
- **Top-Kunden-Ranking**: Live-Identifikation der wertvollsten Kunden
- **Dynamische Segmentierung**: Automatische Klassifizierung (INACTIVE, ACTIVE, PREMIUM)

### Echtzeit-Statistiken
- **Live-Dashboard**: Aktuelle Systemstatistiken ohne Verzögerung
- **KPI-Tracking**: Live-Verfolgung wichtiger Leistungskennzahlen
- **Zero-Latency Reports**: Immer aktuelle Daten durch API-Integration

## Technologie-Stack

- **Framework**: Spring Boot 3.5.6
- **Sprache**: Java 17
- **Build-Tool**: Gradle
- **Architektur**: Stateless Microservice (keine eigene DB)
- **Data Source**: REST API Integration
- **Testing**: JUnit 5
- **Containerisierung**: Docker
- **HTTP-Client**: Spring RestTemplate

## API Endpoints

### Revenue Reports
```http
POST /reporting/revenue/daily?date=2024-01-15
GET /reporting/revenue/range?startDate=2024-01-01&endDate=2024-01-31
```

### Vehicle Usage Reports
```http
POST /reporting/vehicles/{vehicleId}/usage?reportDate=2024-01-15
GET /reporting/vehicles/top-performing
```

### Customer Reports
```http
POST /reporting/customers/{customerId}
GET /reporting/customers/top
```

### General Statistics
```http
GET /reporting/statistics
GET /reporting/health
```

## Datenmodelle

### RevenueReport
- Tägliche, monatliche und jährliche Umsätze
- Anzahl Buchungen und aktive Mieten
- Abgeschlossene Mietvorgänge

### VehicleUsageReport
- Fahrzeugauslastung und -nutzung
- Anzahl Miettage und Vermietungen
- Performance-Metriken

### CustomerReport
- Kundensegmentierung (INACTIVE, ACTIVE, PREMIUM)
- Gesamtausgaben und Buchungshistorie
- Kundenaktivität und -status

## Installation & Setup

### Lokale Entwicklung

1. **Anwendung starten**
```bash
cd Reporting/
./gradlew bootRun
```

### Docker Deployment

1. **Service bauen und starten**
```bash
cd Reporting/
docker-compose up --build
```

2. **Service verfügbar unter**
- Application: http://localhost:8084

## Konfiguration

### Service URLs
Der Service kommuniziert mit folgenden externen Services:
- **Benutzerverwaltung**: http://localhost:8081
- **Fahrzeugverwaltung**: http://localhost:8082  
- **Buchungssystem**: http://localhost:8083

### Application Properties
```properties
server.port=8084
logging.level.com.CarRental.Reporting=INFO
```

## Testing

### Unit Tests ausführen
```bash
./gradlew test
```

### Integration Tests
JUnit 5 Tests für Service-Layer und Controller mit Mockito.

## API Dokumentation

### Beispiel-Anfragen

#### Täglichen Umsatzbericht generieren
```bash
curl -X POST "http://localhost:8084/reporting/revenue/daily?date=2024-01-15"
```

#### Gesamtstatistiken abrufen
```bash
curl -X GET "http://localhost:8084/reporting/statistics"
```

#### Fahrzeugnutzungsbericht erstellen
```bash
curl -X POST "http://localhost:8084/reporting/vehicles/123/usage?reportDate=2024-01-15"
```

#### Top-Kunden abrufen
```bash
curl -X GET "http://localhost:8084/reporting/customers/top"
```

## Live Report Operations

### Revenue Reports
- **GENERATE**: POST /reporting/revenue/daily - Echtzeit-Berechnung aus aktuellen Buchungsdaten
- **RETRIEVE**: GET /reporting/revenue/range - Live-Aggregation über Zeitraum
- **REFRESH**: Automatische Aktualisierung bei jeder Anfrage

### Vehicle Usage Reports  
- **GENERATE**: POST /reporting/vehicles/{id}/usage - Live-Auswertung der Fahrzeugnutzung
- **RETRIEVE**: GET /reporting/vehicles/top-performing - On-demand Top-Fahrzeug-Analyse
- **REFRESH**: Echtzeit-Daten von Fahrzeugverwaltung

### Customer Reports
- **GENERATE**: POST /reporting/customers/{id} - Live-Kundenprofil mit aktuellen Daten
- **RETRIEVE**: GET /reporting/customers/top - Echtzeit Top-Kunden-Ranking
- **REFRESH**: Aktuelle Daten aus Benutzer- und Buchungssystem

## Deployment

### Produktionsumgebung
- **AWS/Azure**: Docker Container Deployment (Stateless)
- **Load Balancing**: Horizontale Skalierung ohne Datensynchronisation
- **Service Discovery**: Über API Gateway
- **Monitoring**: Health checks und Logging

## Architektur

### Stateless Microservice Pattern
- **Service-to-Service Communication**: REST API Calls zu anderen Services
- **Live Data Aggregation**: Echtzeit-Datenabfrage ohne lokale Persistierung
- **On-Demand Reporting**: Berichte werden bei Anfrage generiert
- **No Database**: Komplett zustandslose Architektur

### Fehlerbehandlung
- **Circuit Breaker**: Für externe Service Calls
- **Retry Logic**: Bei temporären Ausfällen der Datenquellen
- **Graceful Degradation**: Partielle Berichte bei Service-Ausfällen
- **Fast Recovery**: Keine Datensynchronisation notwendig