#This script creates our DB
drop database if exists airport;

create database if not exists airport;

use airport;

create table if not exists Addresses(
	id serial,
	country varchar(45) not null,
	locality varchar(45) not null,
	street varchar(45) null,
	build_number varchar(45) null,
	primary key(id)
);

create table if not exists Airports(
	id serial,
	address_id bigint unsigned not null,
	name varchar(45) not null,
    primary key(id),
    constraint fk_airports_address_id foreign key(address_id) references addresses(id)
		on update no action
        on delete cascade
);

create table if not exists Directions(
	id serial,
	airport_id bigint unsigned not null,
	time timestamp not null,
	primary key(id),
    constraint fk_directions_airport_id foreign key(airport_id) references airports(id)
		on update cascade
        on delete cascade
);

create table if not exists Airlines(
	id serial,
    name varchar(45) not null,
	country varchar(40) not null,
	primary key(id)
);

create table if not exists Aircrafts(
	id serial,
	airline_id bigint unsigned not null,
	board_number varchar(45) unique not null,
    type varchar(45) not null,
    seats_count integer unsigned not null default(0),
    primary key(id),
    constraint fk_aircrafts_airline_id foreign key(airline_id) references airlines(id)
		on update cascade
        on delete cascade
);

create table if not exists Positions(
	id serial,
	name varchar(45) not null,
	primary key(id)
);

create table if not exists Employees(
	id serial,
	position_id bigint unsigned not null,
	airline_id bigint unsigned not null,
	name varchar(45) not null,
	surname varchar(45) not null,
	primary key(id),
    constraint fk_employees_position_id foreign key(position_id) references positions(id)
		on update cascade
        on delete cascade,
	constraint fk_employees_airline_id foreign key(airline_id) references airlines(id)
		on update cascade
        on delete cascade
);

create table if not exists Flights(
	id serial,
	aircraft_id bigint unsigned not null,
    direction_from bigint unsigned not null,
    direction_to bigint unsigned not null,
    employee_id bigint unsigned not null,
    number integer unsigned unique not null,
    primary key(id),
    constraint fk_flights_aircraft_id foreign key(aircraft_id) references aircrafts(id)
		on update cascade
        on delete cascade,
	constraint fk_flights_direction_from foreign key(direction_from) references directions(id)
		on update cascade
        on delete cascade,
	constraint fk_flights_direction_to foreign key(direction_to) references directions(id)
		on update cascade
        on delete cascade,
    constraint fk_flights_employee_id foreign key(employee_id) references employees(id)
        on update cascade
        on delete cascade
);

create table if not exists airport_flights(
	id serial,
    airport_id bigint unsigned not null,
    flight_id bigint unsigned not null,
    primary key(id),
    constraint fk_airport_flights_airport_id foreign key(airport_id) references airports(id)
		on update cascade
        on delete cascade,
	constraint fk_airport_flights_flight_id foreign key(flight_id) references flights(id)
		on update cascade
        on delete cascade
    );

create table if not exists Passengers(
	id serial,
	name varchar(45) not null,
	surname varchar(45) not null,
	passport_number varchar(45) unique not null,
	primary key(id)
);

create table if not exists Tickets(
	id serial,
	passenger_id bigint unsigned not null,
	flight_id bigint unsigned not null,
	date_of_sell timestamp not null,
	cost double not null,
	primary key(id),
    constraint fk_tickets_passenger_id foreign key(passenger_id) references passengers(id)
		on update cascade
        on delete cascade,
	constraint fk_tickets_flight_id foreign key(flight_id) references flights(id)
		on update cascade
        on delete cascade
);

create index idx_tickets_passenger_id 
	on Tickets(passenger_id);