/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveByJoystick;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	private static final int TIMEOUT_MS = 0;

	private static final int MAX_SPEED = 200;

	private static final int CPR = 1024;
	private static final int WHEEL_DIAMETER = 6;
	private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
	private static final double MAX_TURN_OUTPUT = 0.25;

	private static final int posIndex = 0;
	private static final double posP = 0.2;
	private static final double posI = 0;
	private static final double posD = 0;

	public PIDController turnPID;
	private static final double turnF = 0.25;
	private static final double turnP = 0.5;
	private static final double turnI = 0;
	private static final double turnD = 0;

	private TalonSRX frontLeft = new TalonSRX(RobotMap.leftFrontTalon);
	private VictorSPX midLeft = new VictorSPX(RobotMap.leftMidVictor);
	private VictorSPX backLeft = new VictorSPX(RobotMap.leftBackVictor);
	private TalonSRX frontRight = new TalonSRX(RobotMap.rightFrontTalon);
	private VictorSPX midRight = new VictorSPX(RobotMap.rightMidVictor);
	private VictorSPX backRight = new VictorSPX(RobotMap.rightBackVictor);

	private AHRS navx;

	public Drivetrain() {
		this.setupMasterTalons();

		this.frontLeft.setInverted(true);
		this.midLeft.setInverted(true);
		this.backLeft.setInverted(true);

		this.midLeft.follow(this.frontLeft);
		this.backLeft.follow(this.frontLeft);
		this.midRight.follow(this.frontRight);
		this.backRight.follow(this.frontRight);

		this.navx = new AHRS(SerialPort.Port.kUSB);
		this.navx.setPIDSourceType(PIDSourceType.kDisplacement);
		this.turnPID = new PIDController(turnP, turnI, turnD, turnF, this.navx, output -> {});
		this.turnPID.setInputRange(-180, 180);
		this.turnPID.setContinuous(true);
		this.turnPID.setOutputRange(-MAX_TURN_OUTPUT, MAX_TURN_OUTPUT);
		this.turnPID.setSetpoint(0);
		this.turnPID.enable();
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveByJoystick());
	}

	// Get Methods

	public boolean isMoving() {
		return Math.abs(this.getLeftSpeed()) > 1 || Math.abs(this.getRightSpeed()) > 1;
	}

	public double getLeftSpeed() {
		return this.frontLeft.getSelectedSensorVelocity(0);
	}

	public double getRightSpeed() {
		return this.frontRight.getSelectedSensorVelocity(0);
	}

	public double getLeftPosition() {
		return this.frontLeft.getSelectedSensorPosition(0) / 50;
	}

	public double getRightPosition() {
		return this.frontRight.getSelectedSensorPosition(0) / 50;
	}

	public double getLeftVoltage() {
		return this.frontLeft.getMotorOutputVoltage();
	}

	public double getRightVoltage() {
		return this.frontRight.getMotorOutputVoltage();
	}

	public double getLeftPercentOutput() {
		return this.frontLeft.getMotorOutputPercent();
	}

	public double getRightPercentOutput() {
		return this.frontRight.getMotorOutputPercent();
	}

	public double getLeftError() {
		return this.frontLeft.getClosedLoopError(0);
	}

	public double getRightError() {
		return this.frontRight.getClosedLoopError(0);
	}

	public double getHeading() {
		if (this.navx.isConnected()) {
			return this.navx.pidGet();
		} else {
			return 0;
		}
	}

	public double getTurnSpeed() {
		if (this.navx.isConnected()) {
			return this.navx.getRate();
		} else {
			return 0;
		}
	}

	public double getRevolutions() {
		return this.frontLeft.getSelectedSensorPosition(0) / CPR;
	}

	public double getLeftRawUnits() {
		return this.frontLeft.getSelectedSensorPosition(0);
	}

	public double getRightRawUnits() {
		return this.frontRight.getSelectedSensorPosition(0);
	}
	// Set Methods

	public void setPercentOutput(double leftPercent, double rightPercent) {
		this.frontLeft.set(ControlMode.PercentOutput, leftPercent);
		this.frontRight.set(ControlMode.PercentOutput, rightPercent);
	}

	public void setSpeed(double leftSpeed, double rightSpeed) {
		this.frontLeft.set(ControlMode.Velocity, leftSpeed);
		this.frontRight.set(ControlMode.Velocity, rightSpeed);
	}

	public void setPosition(double position) {
		double newPosition = -position * 50;
		this.frontLeft.set(ControlMode.Position, newPosition);
		this.frontRight.set(ControlMode.Position, newPosition);
	}

	public void stop() {
		this.setPercentOutput(0, 0);
	}

	public void zeroEncoders() {
		this.frontLeft.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
		this.frontRight.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}

	public void resetGyro() {
		if (this.navx != null) {
			this.navx.reset();
		}
	}

	public void drive(double moveValue, double rotateValue) {
		double leftMotorSpeed;
		double rightMotorSpeed;
		if (moveValue > 0.0) {
			if (rotateValue > 0.0) {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
			} else {
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			}
		} else {
			if (rotateValue > 0.0) {
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				rightMotorSpeed = moveValue + rotateValue;
			} else {
				leftMotorSpeed = moveValue - rotateValue;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
			}
		}

		this.setPercentOutput(leftMotorSpeed, rightMotorSpeed);

		SmartDashboard.putNumber("Move Output", moveValue);
		SmartDashboard.putNumber("Turn Output", rotateValue);
	}

	public void driveDistance(double distance) {
		double newDistance = -distance * 50;
		this.frontLeft.set(ControlMode.MotionMagic, newDistance);
		this.frontRight.set(ControlMode.MotionMagic, newDistance);
	}

	public void enableBrakeMode() {
		this.frontLeft.setNeutralMode(NeutralMode.Brake);
		this.midLeft.setNeutralMode(NeutralMode.Brake);
		this.backLeft.setNeutralMode(NeutralMode.Brake);
		this.frontRight.setNeutralMode(NeutralMode.Brake);
		this.midRight.setNeutralMode(NeutralMode.Brake);
		this.backRight.setNeutralMode(NeutralMode.Brake);
	}

	public void disableBrakeMode() {
		this.frontLeft.setNeutralMode(NeutralMode.Coast);
		this.midLeft.setNeutralMode(NeutralMode.Coast);
		this.backLeft.setNeutralMode(NeutralMode.Coast);
		this.frontRight.setNeutralMode(NeutralMode.Coast);
		this.midRight.setNeutralMode(NeutralMode.Coast);
		this.backRight.setNeutralMode(NeutralMode.Coast);
	}

	public void setPositionPID(double P, double I, double D) {
		this.frontLeft.selectProfileSlot(posIndex, 0);
		this.frontRight.selectProfileSlot(posIndex, 0);
		this.frontLeft.config_kP(posIndex, P, TIMEOUT_MS);
		this.frontLeft.config_kI(posIndex, I, TIMEOUT_MS);
		this.frontLeft.config_kD(posIndex, D, TIMEOUT_MS);
		this.frontRight.config_kP(posIndex, P, TIMEOUT_MS);
		this.frontRight.config_kI(posIndex, I, TIMEOUT_MS);
		this.frontRight.config_kD(posIndex, D, TIMEOUT_MS);
	}

	public void setTurnPID(double P, double I, double D, double F) {
		this.turnPID.setPID(P, I, D, F);
	}

	// Private Convenience Methods

	private void setupMasterTalons() {
		this.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.setPositionPID(posP, posI, posD);
		this.frontLeft.configNominalOutputForward(+0, TIMEOUT_MS);
		this.frontRight.configNominalOutputForward(+0, TIMEOUT_MS);
		this.frontLeft.configNominalOutputReverse(-0, TIMEOUT_MS);
		this.frontRight.configNominalOutputReverse(-0, TIMEOUT_MS);
		this.frontLeft.configPeakOutputForward(+1, TIMEOUT_MS);
		this.frontRight.configPeakOutputForward(+1, TIMEOUT_MS);
		this.frontLeft.configPeakOutputReverse(-1, TIMEOUT_MS);
		this.frontRight.configPeakOutputReverse(-1, TIMEOUT_MS);
		this.frontLeft.enableVoltageCompensation(true);
		this.frontRight.enableVoltageCompensation(true);
		this.frontLeft.configOpenloopRamp(.25, TIMEOUT_MS);
		this.frontRight.configOpenloopRamp(.25, TIMEOUT_MS);
		this.frontLeft.configAllowableClosedloopError(posIndex, 1000, TIMEOUT_MS);
		this.frontRight.configAllowableClosedloopError(posIndex, 1000, TIMEOUT_MS);
		this.frontLeft.configMotionCruiseVelocity((int) (MAX_SPEED * 0.15), TIMEOUT_MS);
		this.frontRight.configMotionCruiseVelocity((int) (MAX_SPEED * 0.15), TIMEOUT_MS);
		this.frontLeft.configMotionAcceleration((int) (MAX_SPEED * .075), TIMEOUT_MS);
		this.frontRight.configMotionAcceleration((int) (MAX_SPEED * .075), TIMEOUT_MS);
		this.frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TIMEOUT_MS);
		this.frontLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TIMEOUT_MS);
		this.frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, TIMEOUT_MS);
		this.frontRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, TIMEOUT_MS);
	}
}
