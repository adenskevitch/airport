#This script is for practical use of the DB

use airport;

#view Addresses
select * from Addresses;

#change locality for Germany
update Addresses 
	set	locality='Munich'
    where country='Germany';

#change german airport's name    
update Airports ai join Addresses ad on ai.address_id=ad.id
	set ai.name='Munich Airport' 
    where ad.country='Germany';
 
 #check our changes
select ai.name, ad.country 
    from Airports ai join Addresses ad
		on ai.address_id=ad.id
    where ad.country='Germany';
    
#change direction from Franch to Netherland
update Directions d join Airports ai 
		on d.airport_id=ai.id 
		join Addresses ad 
        on ai.address_id=ad.id
    set d.airport_id=(select id 
						from Airports 
                        where Airports.address_id=(select id 
														from Addresses 
                                                        where Addresses.locality='Amsterdam'))
    where d.airport_id=(select id 
							from Airports 
                            where Airports.address_id=(select id 
														from Addresses 
                                                        where Addresses.locality='Paris'));

#check our changes
select airport_id, time 
	from Directions 
    order by id;  

#change direction time
update Directions
    set time=str_to_date('23-11-2021 12:40', '%d-%c-%Y %H:%i')
    where time=str_to_date('23-11-2021 13:00', '%d-%c-%Y %H:%i') and airport_id=1;         
    
#change direction time
update Directions
    set time=str_to_date('23-11-2021 20:40', '%d-%c-%Y %H:%i')
    where time=str_to_date('23-11-2021 21:00', '%d-%c-%Y %H:%i') and airport_id=6; 
   
#change type and seats count 
update Aircrafts ac join Airlines al
		on ac.airline_id=al.id
	set ac.type='airbus A380', ac.seats_count=850
    where al.country='Netherlands';

#check our changes    
select ac.* 
	from Aircrafts ac join Airlines al
		on ac.airline_id=al.id
	where al.country='Netherlands';
    
#change pilot's name
update Employees e join Positions p
		on e.position_id=p.id
	set e.name='Jhon', e.surname='Smith'
    where p.name='first pilot';
    
#check our changes
select e.name, e.surname 
	from Employees e join Positions p
		on e.position_id=p.id
	where p.name='first pilot';

#flight number one have Netherlands direction now
update airport_flights
	set airport_id=1
    where flight_id=1;
    
#transfer one ty-204 from Russia to Ukraine
update Aircrafts ac join Airlines al
		on ac.airline_id=al.id
	set ac.airline_id=(select id from Airlines where Airlines.country='Ukraine')
    where ac.board_number='t532123';
    
#change ticket cost for first flight
update Tickets 
set cost=44.50
where flight_id=1;
	
#check our changes    
select *from Tickets;

#delete tickets which were purchased earlier than 15 november
delete from Tickets
where date_of_sell<str_to_date('15-11-2021 12:00', '%d-%c-%Y %H:%i');

#delete Aron Scot from Passengers list
delete from Passengers where name='Aron' and surname='Scot';

#delete technician from position list
delete from Positions where name='technician';

#delete all ty-204 type aircraft
delete from Aircrafts where type='ty-204';

#delete Ukraine's and Russian airline
delete from Airlines where country='Russia' or country='Ukraine';

#returns information about all tickets sold after 15 november
select p.name, p.surname, f.number as FlightNumber, df.time as DepartTime, apf.name as 'From', adf.locality, adf.country, dt.time as AriveTime, apt.name as 'To', adt.locality, adt.country 
	from Tickets t 
		left join Passengers p
			on t.passenger_id=p.id
		left join Flights f
			on t.flight_id=f.id
		left join Directions df
			on f.direction_from=df.id
		left join Airports apf
			on df.airport_id=apf.id
		left join Addresses adf
			on apf.address_id=adf.id
		left join Directions dt
			on f.direction_to=dt.id
		left join Airports apt
			on dt.airport_id=apt.id
		left join Addresses adt
			on apt.address_id=adt.id
	where t.date_of_sell>str_to_date('15-11-2021 12:00', '%d-%c-%Y %H:%i') 
		group by FlightNumber 
        having f.number=5601;          

#show type and number of two countries
select al.country, ac.type, count(ac.id) as Total
	from Aircrafts ac 
		left join Airlines al
        on ac.airline_id=al.id
	where al.country='Germany'
    union all
select al.country, ac.type, count(ac.id) as Total
	from Aircrafts ac 
		left join Airlines al
        on ac.airline_id=al.id
	where al.country='France';
    
#show people who bought tickets from 17th to 20th november
select date_of_sell as 'Sale time', name as Name, surname as Surname
	from Tickets t
		left join Passengers p
        on t.passenger_id=p.id
	 where date_of_sell >=str_to_date('17-11-2021 00:00', '%d-%c-%Y %H:%i') 
		 group by date_of_sell 
         having date_of_sell<str_to_date('21-11-2021 00:00', '%d-%c-%Y %H:%i')
        order by name;