package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.elevator.ElevatorGamepadControl;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private static final int TIMEOUT_MS = 0;

	private static final int CPR = 4096;
	private static final double DISTANCE_PER_REV = 10;
	private static final double MAX_SPEED = 0;

	private static final int elevatorIndex = 1;
	private static final double P = 0.25;
	private static final double I = 0;
	private static final double D = 0;

	private boolean isRaising = false;
	private boolean isLowering = false;

	private TalonSRX talon = new TalonSRX(RobotMap.elevatorTalon);
	private DigitalInput isAtTop = new DigitalInput(RobotMap.elevatorAtTop);
	private DigitalInput isAtBottom = new DigitalInput(RobotMap.elevatorAtBottom);
	private DigitalInput isCloseToTop = new DigitalInput(RobotMap.elevatorCloseToTop);
	private DigitalInput isCloseToBottom = new DigitalInput(RobotMap.elevatorCloseToBottom);

	public Elevator() {
		this.talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TIMEOUT_MS);
		this.talon.setSensorPhase(true);
		this.talon.selectProfileSlot(elevatorIndex, 0);
		this.talon.config_kP(elevatorIndex, P, TIMEOUT_MS);
		this.talon.config_kI(elevatorIndex, I, TIMEOUT_MS);
		this.talon.config_kD(elevatorIndex, D, TIMEOUT_MS);
		this.talon.configNominalOutputForward(+0, TIMEOUT_MS);
		this.talon.configNominalOutputReverse(-0, TIMEOUT_MS);
		this.talon.configPeakOutputForward(+1, TIMEOUT_MS);
		this.talon.configPeakOutputReverse(-1, TIMEOUT_MS);
		this.talon.configAllowableClosedloopError(elevatorIndex, 1000, TIMEOUT_MS);
		this.talon.configMotionCruiseVelocity((int) (MAX_SPEED * 0.7), TIMEOUT_MS);
		this.talon.configMotionAcceleration((int) (MAX_SPEED * .25), TIMEOUT_MS);
		this.talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TIMEOUT_MS);
		this.talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TIMEOUT_MS);
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

	public void setPID(double P, double I, double D) {
		this.talon.selectProfileSlot(elevatorIndex, 0);
		this.talon.config_kP(elevatorIndex, P, TIMEOUT_MS);
		this.talon.config_kI(elevatorIndex, I, TIMEOUT_MS);
		this.talon.config_kD(elevatorIndex, D, TIMEOUT_MS);
	}

	public void zeroEncoder() {
		this.talon.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}

	public void setEncoder(double value) {
		int newValue = (int) (value * CPR / DISTANCE_PER_REV);
		this.talon.setSelectedSensorPosition(newValue, 0, TIMEOUT_MS);
	}

	public double getRevolutions() {
		return this.talon.getSelectedSensorPosition(0) / CPR;
	}

	public double getRawUnits() {
		return this.talon.getSelectedSensorPosition(0);
	}

	public double getHeight() {
		return this.talon.getSelectedSensorPosition(0) / CPR * DISTANCE_PER_REV;
	}

	public double getSpeed() {
		return this.talon.getSelectedSensorVelocity(0);
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

	public boolean isRaising() {
		return this.isRaising;
	}

	public boolean isLowering() {
		return this.isLowering;
	}

	public boolean isAtTop() {
		return this.isAtTop.get();
	}

	public boolean isAtBottom() {
		return this.isAtBottom.get();
	}

	public boolean isCloseToTop() {
		return this.isCloseToTop.get();
	}

	public boolean isCloseToBottom() {
		return this.isCloseToBottom.get();
	}
}
