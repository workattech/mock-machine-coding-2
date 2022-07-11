<?php

class Ticket {
	private $ticketId;
	private $vehicle;

	function __construct( Vehicle $vehicle, $floor, $slot, $parkingLotId ) {
		$this->vehicle = $vehicle;
		$this->ticketId = $parkingLotId.'_'.$floor.'_'.$slot;
	}
	
	public function getVehicle() {
		return $this->vehicle;
	}

	public function getTicketId() {
		return $this->ticketId;
	}

	public function setTicketId( $ticketId ) {
		$this->ticketId = $ticketId;
	}

	public function getTicketInfo( $ticketId ) {
		return "Registration Number: {$this->vehicle->getRegNo()} and Color: {$this->vehicle->getColor()}";
	}
}
