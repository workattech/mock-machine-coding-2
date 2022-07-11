<?php

require_once "vehicle.php";
require_once "parking-lot.php";
require_once "tickets.php";


$prarkingLot1 = new ParkingLot();
$prarkingLot1->createLot('PR1234', 2, 6);

$prarkingLot1->printFreeSlotsCount('CAR');
$prarkingLot1->printFreeSlotsCount('BIKE');
$prarkingLot1->printFreeSlotsCount('TRUCK'); 

$prarkingLot1->printFreeSlots('CAR'); 
$prarkingLot1->printFreeSlots('BIKE'); 
$prarkingLot1->printFreeSlots('TRUCK'); 

$prarkingLot1->printOccupiedSlots('CAR'); 
$prarkingLot1->printOccupiedSlots('BIKE'); 
$prarkingLot1->printOccupiedSlots('TRUCK'); 

$vehicle1 = new Vehicle('CAR', 'KA-01-DB-1234', 'BLACK');
$vehicle2 = new Vehicle('CAR', 'KA-02-CB-1334', 'RED');
$vehicle3 = new Vehicle('CAR', 'KA-01-DB-1133', 'BLACK');
$vehicle4 = new Vehicle('CAR', 'KA-05-HJ-8432', 'WHITE');
$vehicle5 = new Vehicle('CAR', 'WB-45-HO-9032', 'WHITE');
$vehicle6 = new Vehicle('CAR', 'KA-01-DF-8230', 'BLACK');
$vehicle7 = new Vehicle('CAR', 'KA-21-HS-2347', 'RED');

$prarkingLot1->parkVehicle($vehicle1); 
$prarkingLot1->parkVehicle($vehicle2); 
$prarkingLot1->parkVehicle($vehicle3); 
$prarkingLot1->parkVehicle($vehicle4); 
$prarkingLot1->parkVehicle($vehicle5); 
$prarkingLot1->parkVehicle($vehicle6); 
$prarkingLot1->parkVehicle($vehicle7); 

$prarkingLot1->printFreeSlotsCount('CAR');
$prarkingLot1->printFreeSlotsCount('BIKE');
$prarkingLot1->printFreeSlotsCount('TRUCK'); 

$prarkingLot1->unparkVehicle('PR1234_2_5'); 
$prarkingLot1->unparkVehicle('PR1234_2_5'); 
$prarkingLot1->unparkVehicle('PR1234_2_7'); 

$prarkingLot1->printFreeSlotsCount('CAR');
$prarkingLot1->printFreeSlotsCount('BIKE');
$prarkingLot1->printFreeSlotsCount('TRUCK'); 

$prarkingLot1->printFreeSlots('CAR'); 
$prarkingLot1->printFreeSlots('BIKE'); 
$prarkingLot1->printFreeSlots('TRUCK'); 

$prarkingLot1->printOccupiedSlots('CAR'); 
$prarkingLot1->printOccupiedSlots('BIKE'); 
$prarkingLot1->printOccupiedSlots('TRUCK');


$vehicle8 = new Vehicle('BIKE', 'KA-01-DB-1541', 'BLACK');
$vehicle9 = new Vehicle('TRUCK', 'KA-32-SJ-5389', 'ORANGE');
$vehicle10 = new Vehicle('TRUCK', 'KL-54-DN-4582', 'GREEN');
$vehicle11 = new Vehicle('TRUCK', 'KL-12-HF-4542', 'GREEN');

$prarkingLot1->parkVehicle($vehicle8); 
$prarkingLot1->parkVehicle($vehicle9); 
$prarkingLot1->parkVehicle($vehicle10); 
$prarkingLot1->parkVehicle($vehicle11); 

$prarkingLot1->printFreeSlotsCount('CAR');
$prarkingLot1->printFreeSlotsCount('BIKE');
$prarkingLot1->printFreeSlotsCount('TRUCK'); 

$prarkingLot1->printFreeSlots('CAR'); 
$prarkingLot1->printFreeSlots('BIKE'); 
$prarkingLot1->printFreeSlots('TRUCK'); 

$prarkingLot1->printOccupiedSlots('CAR'); 
$prarkingLot1->printOccupiedSlots('BIKE'); 
$prarkingLot1->printOccupiedSlots('TRUCK'); 
	


