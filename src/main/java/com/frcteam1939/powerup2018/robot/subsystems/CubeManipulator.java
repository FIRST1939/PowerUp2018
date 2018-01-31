package com.frcteam1939.powerup2018.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {

	public static final double OUT_SPEED = 1.0;
	public static final double IN_SPEED = -1.0;

	// private TalonSRX masterTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);
	// private TalonSRX slaveTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);

	// private DigitalInput banner = new DigitalInput(RobotMap.cubeManipulatorBanner);

	// private Solenoid solenoidAngleTop = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleTop);
	// private Solenoid solenoidAngleBottom = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleBottom);
	// private Solenoid solenoidWheels = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorWheelsSolenoid);

	public CubeManipulator() {
		//	slaveTalon.setInverted(true);
		// this.slaveTalon.follow(this.masterTalon);
	}

	@Override
	public void initDefaultCommand() {}
}
