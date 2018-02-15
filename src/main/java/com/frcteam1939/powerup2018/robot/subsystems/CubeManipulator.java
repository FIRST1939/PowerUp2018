package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.CubeManipulatorGamepadControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {

	public static final double OUT_SPEED = 1.0;
	public static final double IN_SPEED = -1.0;

	private TalonSRX masterTalon = new TalonSRX(RobotMap.masterCubeManipulatorTalon);
	private TalonSRX slaveTalon = new TalonSRX(RobotMap.slaveCubeManipulatorTalon);

	private DigitalInput banner = new DigitalInput(RobotMap.cubeManipulatorBanner);

	private Solenoid solenoidAngleTop = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleTop);
	private Solenoid solenoidAngleBottom = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleBottom);
	private Solenoid solenoidWheels = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorWheelsSolenoid);

	public CubeManipulator() {
		this.slaveTalon.follow(this.masterTalon);
		this.masterTalon.setInverted(true);
		this.masterTalon.setNeutralMode(NeutralMode.Coast);
		this.slaveTalon.setNeutralMode(NeutralMode.Coast);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new CubeManipulatorGamepadControl());
	}

	public void cubeManipulatorWheelsIn() {
		this.solenoidWheels.set(true);
	}

	public void cubeManipulatorWheelsOut() {
		this.solenoidWheels.set(false);
	}

	public void cubeManipulatorRaise() {
		if (Robot.elevator.getHeight() > 9) {
			this.solenoidAngleBottom.set(false);
			this.solenoidAngleTop.set(false);
		}
	}

	public void cubeManipulatorLower() {
		this.solenoidAngleBottom.set(true);
		this.solenoidAngleTop.set(true);
	}

	public void cubeManipulatorMiddle() {
		if (Robot.elevator.getHeight() > 9) {
			this.solenoidAngleBottom.set(true);
			this.solenoidAngleTop.set(false);
		}
	}

	public void set(double value) {
		this.masterTalon.set(ControlMode.PercentOutput, value);
	}

	public boolean haveCube() {
		return !this.banner.get();
	}
}
