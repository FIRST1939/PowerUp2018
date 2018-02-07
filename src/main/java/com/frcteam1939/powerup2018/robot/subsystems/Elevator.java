package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.elevator.ElevatorGamepadControl;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private static final int TIMEOUT_MS = 10;

	private static final int CPR = 4096;
	private static final double DISTANCE_PER_REV = 0;

	private static final int elevatorIndex = 1;
	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;

	private TalonSRX talon = new TalonSRX(RobotMap.elevatorTalon);

	public Elevator() {
		this.talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.talon.selectProfileSlot(elevatorIndex, 0);
		this.talon.config_kP(elevatorIndex, P, TIMEOUT_MS);
		this.talon.config_kI(elevatorIndex, I, TIMEOUT_MS);
		this.talon.config_kD(elevatorIndex, D, TIMEOUT_MS);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ElevatorGamepadControl());
	}

	public void setHeight(double height) {
		this.talon.set(ControlMode.MotionMagic, height);
	}

	public void zeroEncoder() {
		this.talon.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}

	public double getHeight() {
		return this.talon.getSelectedSensorPosition(1); // Divide by CPR, multiply by distance traveled per rev, any additional calc, then add lowest height
	}
}
