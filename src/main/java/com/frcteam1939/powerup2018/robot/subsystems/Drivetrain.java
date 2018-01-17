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
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveByJoystick;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	private final int TIMEOUT_MS = 10;

	private TalonSRX frontLeft = new TalonSRX(RobotMap.leftFrontTalon);
	private TalonSRX midLeft = new TalonSRX(RobotMap.leftMidTalon);
	private TalonSRX backLeft = new TalonSRX(RobotMap.leftBackTalon);
	private TalonSRX frontRight = new TalonSRX(RobotMap.rightFrontTalon);
	private TalonSRX midRight = new TalonSRX(RobotMap.rightMidTalon);
	private TalonSRX backRight = new TalonSRX(RobotMap.rightBackTalon);

	private Solenoid leftShiftingGearbox = new Solenoid(RobotMap.PCM, RobotMap.leftShiftingGearbox);
	private Solenoid rightShiftingGearbox = new Solenoid(RobotMap.PCM, RobotMap.rightShiftingGearbox);

	public Drivetrain() {
		this.midLeft.follow(this.frontLeft);
		this.backLeft.follow(this.frontLeft);
		this.midRight.follow(this.frontRight);
		this.backRight.follow(this.frontRight);
		this.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, this.TIMEOUT_MS);
		this.frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 1, this.TIMEOUT_MS);
		this.frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, this.TIMEOUT_MS);
		this.frontRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 1, this.TIMEOUT_MS);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new DriveByJoystick());
	}

	public void stop() {
		this.setPercentOutput(0, 0);
	}

	public void setPercentOutput(double rightPercent, double leftPercent) {
		this.frontLeft.set(ControlMode.PercentOutput, leftPercent);
		this.frontRight.set(ControlMode.PercentOutput, rightPercent);
	}

	public void zeroEncoders() {
		this.frontLeft.getSensorCollection().setQuadraturePosition(0, this.TIMEOUT_MS);
		this.frontRight.getSensorCollection().setQuadraturePosition(0, this.TIMEOUT_MS);
	}

	public void shiftingGearboxDown() {
		this.leftShiftingGearbox.set(true);
		this.rightShiftingGearbox.set(true);
	}

	public void shiftingGearboxUp() {
		this.leftShiftingGearbox.set(false);
		this.rightShiftingGearbox.set(false);
	}

	public void drive() {}

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
}
