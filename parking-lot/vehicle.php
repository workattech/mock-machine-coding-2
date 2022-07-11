<?php
class Vehicle {
	private $availableTypes = array( 'CAR', 'BIKE', 'TRUCK' );
	private $vehicleType;
	private $regNo;
	private $color;


	function __construct($vehicleType, $regNo, $color){
		if (!in_array($vehicleType, $this->availableTypes)) {
			echo "Invalid vehicle type.";
			return;
		}
		$this->vehicleType = $vehicleType;
		$this->regNo = $regNo;
		$this->color = $color;
	}

	public function getVehicleType() {
		return $this->vehicleType;
	}

	public function setVehicleType( $type ) {
		if (!in_array($type, $this->availableTypes)) {
			echo "Invalid vehicle type.";
			return;
		}
		$this->vehicleType = $type;
	}

	public function getRegNo() {
		return $this->regNo;
	}

	public function setRegNo( $regNo ) {
		$this->regNo = $regNo;
	}

	public function getColor() {
		return $this->color;
	}

	public function setColor($color) {
		$this->color = $color;
	}
}

?>
