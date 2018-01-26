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
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveByJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	private static final int TIMEOUT_MS = 10;

	private static final double lowGearLimit = 0.6;

	private static final int posIndex = 0;
	private static final double posP = 0;
	private static final double posI = 0;
	private static final double posD = 0;

	private TalonSRX frontLeft = new TalonSRX(RobotMap.leftFrontTalon);
	private TalonSRX midLeft = new TalonSRX(RobotMap.leftMidTalon);
	private TalonSRX backLeft = new TalonSRX(RobotMap.leftBackTalon);
	private TalonSRX frontRight = new TalonSRX(RobotMap.rightFrontTalon);
	private TalonSRX midRight = new TalonSRX(RobotMap.rightMidTalon);
	private TalonSRX backRight = new TalonSRX(RobotMap.rightBackTalon);

	private PigeonIMU pigeon = new PigeonIMU(this.frontLeft);

	private DoubleSolenoid leftShiftingGearbox = new DoubleSolenoid(RobotMap.PCM, RobotMap.leftShiftingGearboxUp, RobotMap.leftShiftingGearboxDown);
	private DoubleSolenoid rightShiftingGearbox = new DoubleSolenoid(RobotMap.PCM, RobotMap.rightShiftingGearboxUp, RobotMap.rightShiftingGearboxDown);

	public Drivetrain() {
		this.setupMasterTalons();
		this.midLeft.follow(this.frontLeft);
		this.backLeft.follow(this.frontLeft);
		this.midRight.follow(this.frontRight);
		this.backRight.follow(this.frontRight);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveByJoystick());
	}

	public void setPercentOutput(double leftPercent, double rightPercent) {
		this.frontLeft.set(ControlMode.PercentOutput, leftPercent);
		this.frontRight.set(ControlMode.PercentOutput, rightPercent);
	}

	public void stop() {
		this.setPercentOutput(0, 0);
	}

	public void shiftingGearboxLow() {
		this.leftShiftingGearbox.set(DoubleSolenoid.Value.kReverse);
		this.rightShiftingGearbox.set(DoubleSolenoid.Value.kReverse);
	}

	public void shiftingGearboxHigh() {
		this.leftShiftingGearbox.set(DoubleSolenoid.Value.kForward);
		this.rightShiftingGearbox.set(DoubleSolenoid.Value.kForward);
	}

	public void zeroEncoders() {
		this.frontLeft.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
		this.frontRight.getSensorCollection().setQuadraturePosition(0, TIMEOUT_MS);
	}

	public void drive(double moveValue, double rotateValue) {

		boolean highGear = false;
		if (moveValue > lowGearLimit && !highGear) {
			this.shiftingGearboxHigh();
			highGear = true;
		}

		if (moveValue < lowGearLimit && highGear) {
			this.shiftingGearboxLow();
			highGear = false;
		}

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
		this.frontLeft.set(ControlMode.MotionMagic, distance);
		this.frontRight.set(ControlMode.MotionMagic, distance);
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

	private void setupMasterTalons() {
		this.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.setPositionPID(posP, posI, posD);
		this.frontLeft.configNominalOutputForward(+0f, TIMEOUT_MS);
		this.frontRight.configNominalOutputForward(+0f, TIMEOUT_MS);
		this.frontLeft.configNominalOutputReverse(-0f, TIMEOUT_MS);
		this.frontRight.configNominalOutputReverse(-0f, TIMEOUT_MS);
		this.frontLeft.configPeakOutputForward(+12f, TIMEOUT_MS);
		this.frontRight.configPeakOutputForward(+12f, TIMEOUT_MS);
		this.frontLeft.configPeakOutputReverse(-12f, TIMEOUT_MS);
		this.frontRight.configPeakOutputReverse(-12f, TIMEOUT_MS);
		this.frontLeft.enableVoltageCompensation(true);
		this.frontRight.enableVoltageCompensation(true);
	}
}
