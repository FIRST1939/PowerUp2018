package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {

	public static final double OUT_SPEED = 1.0;
	public static final double IN_SPEED = -1.0;
	private TalonSRX masterTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);
	private TalonSRX slaveTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);

	public void CubeIntake() {

	}

	public CubeManipulator() {
		//	slaveTalon.setInverted(true);
		this.slaveTalon.follow(this.masterTalon);
	}

	@Override
	public void initDefaultCommand() {

	}

	public void set(double value) {
		this.masterTalon.set(ControlMode.PercentOutput, value);
	}
}
