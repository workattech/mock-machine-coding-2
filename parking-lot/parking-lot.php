<?php

class ParkingLot {
	private $id;
	private $vehicle;
	private $totalSlots;
	private $parkingFloor;
	private $booking;
	/*
		this map will contain floor as key and all parking slots on that floor as value
		$parkingLotMap = [
							1 -> array('TRUCK', 'BIKE', 'BIKE', 'CAR', 'CAR', $ticket Obj, 'CAR');
							....
							....
						]
	*/

	public static $parkingLotMap = array();

	public function createLot( $id, $numOfFloors, $numOfSlots ) {
		$this->id = $id;
		$this->totalSlots = $numOfSlots;
		
		$this->addFloor( $numOfFloors, $numOfSlots );
		echo "Created parking lot with {$numOfFloors} floors and {$numOfSlots} slots per floor".PHP_EOL;
	}

	public function getParkingLotId() {
		return $this->id;
	}

	public function addFloor( $numOfFloors, $numOfSlots ) {
		$slots = array();

		for ( $i=1; $i <= $numOfSlots ; $i++ ) {
			if ( $i == 1 ) {
				$slots[$i] = 'TRUCK';
			}
			elseif ($i == 2 || $i == 3) {
				$slots[$i] = 'BIKE';
			}
			else {
				$slots[$i] = 'CAR';
			}
		}

		for ($i=1; $i <= $numOfFloors ; $i++) {
			self::$parkingLotMap[$i] = $slots;
		}
	}

	public function parkVehicle( Vehicle $vehicle ) {
		if ($this->getFreeSlotsCount($vehicle->getVehicleType()) < 1) {
			echo "Parking Lot Full" . PHP_EOL;
			return;
		}

		$this->bookParkingTicket($vehicle);
	}

	public function bookParkingTicket(Vehicle $vehicle){
		foreach (self::$parkingLotMap as $floor => $slots) {
			for ($i=1; $i <= sizeof($slots); $i++) { 
				if ($slots[$i] == $vehicle->getVehicleType()) {
					$ticket = new Ticket($vehicle, $floor, $i, $this->getParkingLotId());
					ParkingLot::$parkingLotMap[$floor][$i] = $ticket;
					echo "Parked vehicle. Ticket ID: {$ticket->getTicketId()}" . PHP_EOL;
					return;
				}
			}
		}
	}

	public function unparkVehicle( $ticketId ) {
		foreach (self::$parkingLotMap as $floor => $slots) {
			for ($i=1; $i <= sizeof($slots); $i++) { 
				if ($slots[$i] instanceof Ticket && strtoupper($slots[$i]->getTicketId()) == $ticketId) {
					if ( $i == 1 ) {
						$allowedVehicle = 'TRUCK';
					}
					elseif ($i == 2 || $i == 3) {
						$allowedVehicle = 'BIKE';
					}
					else {
						$allowedVehicle = 'CAR';
					}
					self::$parkingLotMap[$floor][$i] = $allowedVehicle;
					echo "Unparked vehicle with {$slots[$i]->getTicketInfo($ticketId)}" . PHP_EOL;
					return;
				}
			}
		}
		echo "Invalid Ticket" . PHP_EOL;
	}

	public function printFreeSlots( $vehicleType ) {
		$freeSlots = '';
		for ($i=1; $i <= sizeof(self::$parkingLotMap); $i++) {
			for ($j=1; $j <= sizeof(self::$parkingLotMap[$i]); $j++) {
				if (strtoupper($vehicleType) == self::$parkingLotMap[$i][$j]) {
					$freeSlots .= ' '. $j; 
				}
			}
			echo "Free slots for {$vehicleType} on Floor {$i}: {$freeSlots}" . PHP_EOL;
			$freeSlots = '';
		}
	}

	public function printOccupiedSlots( $vehicleType ) {
		$occupiedSlots = '';
		for ($i=1; $i <= sizeof(self::$parkingLotMap); $i++) {
			for ($j=1; $j <= sizeof(self::$parkingLotMap[$i]); $j++) {
				if ( $j == 1 ) {
					$allowedVehicle = 'TRUCK';
				}
				elseif ($j == 2 || $j == 3) {
					$allowedVehicle = 'BIKE';
				}
				else {
					$allowedVehicle = 'CAR';
				}
				if (self::$parkingLotMap[$i][$j] instanceof Ticket && $vehicleType == self::$parkingLotMap[$i][$j]->getVehicle()->getVehicleType()) {
					$occupiedSlots .= ' ' . $j; 
				}
			}
			echo "Occupied slots for {$vehicleType} on Floor {$i}: {$occupiedSlots}" . PHP_EOL;
			$occupiedSlots = '';
		}
	}																													

	public function printFreeSlotsCount($vehicleType) {
		$slotsCountPerFloor = 0;
		for ($i=1; $i <= sizeof(self::$parkingLotMap); $i++) {
			for ($j=1; $j <= sizeof(self::$parkingLotMap[$i]); $j++) {
				if (strtoupper($vehicleType) == self::$parkingLotMap[$i][$j]) {
					$slotsCountPerFloor++;
				}
			}
			echo "No. of free slots for {$vehicleType} on Floor {$i}: {$slotsCountPerFloor}" . PHP_EOL;
			$slotsCountPerFloor = 0;
		}
	}

	public function getFreeSlotsCount($vehicleType) {
		$slotsTotalCount = 0;
		for ($i=1; $i <= sizeof(self::$parkingLotMap); $i++) {
			for ($j=1; $j <= sizeof(self::$parkingLotMap[$i]); $j++) {
				if (strtoupper($vehicleType) == self::$parkingLotMap[$i][$j]) {
					$slotsTotalCount++;
				}
			}
		}
		return $slotsTotalCount;
	}
}
