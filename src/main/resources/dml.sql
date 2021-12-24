#This script adds data to the DB

use airport;

insert into Addresses(country, locality) values
    ('Netherlands', 'Amsterdam'),
    ('Russia', 'Moscow'),
    ('Ukraine', 'Kiev'),
    ('Poland', 'Warshaw'),
    ('France', 'Paris'),
    ('United Kingdom', 'London'),
    ('Germany', 'Berlin'),
    ('Italy', 'Rome'),
    ('Spain', 'Madrid'),
    ('Turkey', 'Istanbul');

insert into Airports(address_id, name) values
    (1, 'Amsterdam Airport Schiphol'),
    (2, 'Sheremetyevo International Airport'),
    (3, 'Boryspil International Airport'),
    (4, 'Warsaw Chopin Airport'),
    (5, 'Charles de Gaulle Airport'),
    (6, 'Heathrow Airport'),
    (7, 'Berlin Tegel Airport'),
    (8, 'Leonardo da Vinci–Fiumicino Airport'),
    (9, 'Adolfo Suárez Madrid–Barajas Airport'),
    (10, 'Istanbul Airport');

insert into Directions(airport_id, time) values
    (6, str_to_date('23-11-2021 12:00', '%d-%c-%Y %H:%i')),
    (5, str_to_date('23-11-2021 13:00', '%d-%c-%Y %H:%i')),
    (6, str_to_date('23-11-2021 21:00', '%d-%c-%Y %H:%i')),
    (5, str_to_date('23-11-2021 20:00', '%d-%c-%Y %H:%i'));

insert into Airlines(name, country) values
    ('Amsterdam Airlines','Netherlands'),
    ('Aeroflot', 'Russia'),
    ('Ukraine International Airlines', 'Ukraine'),
    ('LOT', 'Poland'),
    ('Air France', 'France'),
    ('British Airways', 'United Kingdom'),
    ('Lufthansa', 'Germany'),
    ('Alitalia', 'Italy'),
    ('Spanish Airline', 'Spanish'),
    ('Turkish Airlines', 'Turkey');

insert into Aircrafts(airline_id, board_number, type, seats_count) values
    (1, 'a523654667', 'airbus A330', 750),
    (1, 'a6342352', 'airbus A330', 750),
    (2, 't4321', 'ty-204', 210),
    (2, 't23332', 'ty-204', 210),
    (2, 't44312', 'ty-204', 210),
    (2, 't532123', 'ty-204', 210),
    (4, 'a86546743', 'airbus A380', 850),
    (5, 'a2356467547', 'airbus A310', 650),
    (6, 'a534245235', 'airbus A380', 850),
    (7, 'b145325356', 'booing 737', 450),
    (7, 'b53567889', 'booing 737', 450),
    (8, 'a3211234', 'airbus A380', 850),
    (9, 'a422231', 'airbus A380', 850),
    (10, 'a54433', 'airbus A380', 850),
    (10, 'a43253233', 'airbus A380', 850);

insert into Positions(name) values
    ('director'),
    ('manager'),
    ('first pilot'),
    ('second pilot'),
    ('stewardess'),
    ('technician');

insert into Employees(position_id, airline_id, name, surname) values
    (3, 6,'Anton', 'Jons'),
    (4, 6, 'Fredy', 'Mercier'),
    (5, 6, 'Joana', 'Lither');

insert into Flights(aircraft_id, direction_from, direction_to, employee_id, number) values
    (9, 1, 2, 1, 6501),
    (9, 4, 3, 1, 5601);

insert into airport_flights(airport_id, flight_id) values
    (6,1),
    (5,1);

insert into Passengers(name, surname, passport_number) values
    ('Edward', 'Gregory', 'GT234567'),
    ('Gavin', 'White', 'RD987234'),
    ('Scott', 'Simpson', 'LD857346'),
    ('Aron', 'Scot', 'TC553422'),
    ('Sandra', 'Gardner', 'YC622333'),
    ('Emma', 'Richards', 'UC097834'),
    ('Anne', 'Gordon', 'TD6452234'),
    ('Philomena', 'Haynes', 'OK946264'),
    ('Joshua', 'Cox', 'JC465027');

insert into Tickets(passenger_id, flight_id, date_of_sell, cost) values
    (1, 1, str_to_date('20-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (2, 1, str_to_date('19-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (2, 2, str_to_date('19-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50),
    (3, 2, str_to_date('14-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50),
    (4, 1, str_to_date('14-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (5, 2, str_to_date('14-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50),
    (6, 1, str_to_date('14-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (6, 2, str_to_date('13-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50),
    (7, 1, str_to_date('10-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (8, 1, str_to_date('13-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (8, 2, str_to_date('13-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50),
    (9, 1, str_to_date('12-11-2021 15:53', '%d-%c-%Y %H:%i'), 45.50),
    (9, 2, str_to_date('12-11-2021 15:53', '%d-%c-%Y %H:%i'), 43.50);