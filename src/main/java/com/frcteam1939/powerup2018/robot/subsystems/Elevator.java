package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.DistanceConstants;
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

	public boolean isRaising = false;
	public boolean isLowering = false;

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
		double newHeight = (height - this.getHeight()) * CPR / DISTANCE_PER_REV;
		if (newHeight > 0) {
			this.isRaising = true;
			this.isLowering = false;
		} else {
			this.isRaising = false;
			this.isLowering = true;
		}
		this.talon.set(ControlMode.MotionMagic, newHeight);
	}

	public void set(double value) {
		this.talon.set(ControlMode.PercentOutput, value);

		if (value > 0) {
			this.isRaising = true;
			this.isLowering = false;
		} else {
			this.isRaising = false;
			this.isLowering = true;
		}
	}

	public void zeroEncoder() {
		this.talon.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}

	public double getRevolutions() {
		return this.talon.getSelectedSensorPosition(0) / CPR;
	}

	public double getHeight() {
		return this.talon.getSelectedSensorPosition(0) / CPR * DISTANCE_PER_REV + DistanceConstants.LOW_LIMIT;
	}

	public void enableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Brake);
	}

	public void disableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Coast);
	}

	public void stop() {
		this.set(0);
	}
}
