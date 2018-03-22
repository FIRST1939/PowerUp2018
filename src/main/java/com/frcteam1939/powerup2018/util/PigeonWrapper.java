package com.frcteam1939.powerup2018.util;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PigeonWrapper extends PigeonIMU implements PIDSource {

	private PIDSourceType type = PIDSourceType.kDisplacement;

	public PigeonWrapper(int deviceNumber) {
		super(deviceNumber);
	}

	public PigeonWrapper(TalonSRX talonSRX) {
		super(talonSRX);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.type = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return this.type;
	}

	@Override
	public double pidGet() {
		return this.getFusedHeading();
	}

}
