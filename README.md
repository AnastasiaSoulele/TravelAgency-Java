# Travel Agency Management System

A desktop application for managing a travel agency, built in Java
with a Swing GUI. Developed as a university Database course project.

##  Overview

The system provides a full management interface for a travel agency,
covering entities such as trips, reservations, destinations, guides,
drivers, workers, and more — all connected through a relational database.

##  Entities Managed

| Entity | Description |
|--------|-------------|
| `TravelAgency` | Core agency info |
| `Branch` | Agency branches |
| `Trip` | Available trips |
| `Destination` | Travel destinations |
| `Reservation` | Customer reservations |
| `Event` | Events during trips |
| `Guide` | Tour guides |
| `Driver` | Trip drivers |
| `Worker` | Agency staff |
| `Admin` | Admin panel |
| `Languages` | Languages spoken by guides |
| `Phones` | Contact phone numbers |
| `Offers` | Available trip offers |
| `Manages` | Branch management relations |
| `Travel_to` | Trip-destination relations |
| `Reservation_Offers` | Offers linked to reservations |
| `IT` | IT department management |

##  How to Run

Open the project in **NetBeans** (recommended, due to `.form` files)
and run `Main.java`.

```bash
# Or compile manually
javac Main.java
java Main
```

##  Technologies
- Java
- Java Swing (GUI)
- NetBeans GUI Builder
- JDBC / Relational Database

##  Context
University project — Databases course.
