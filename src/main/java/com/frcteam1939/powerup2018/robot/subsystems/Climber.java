
package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.climber.ClimberGamepadControl;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	TalonSRX talon = new TalonSRX(RobotMap.climberTalon);

	private static final int CPR = 4096;
	public double MAX_REV = 0;

	public Climber() {
		this.talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());

	}

	public void set(double value) {
		this.talon.set(ControlMode.PercentOutput, value);
	}

	public void enableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Brake);
	}

	public void disableBrakeMode() {
		this.talon.setNeutralMode(NeutralMode.Coast);
	}

	public double getPosition() {
		return this.talon.getSelectedSensorPosition(0) / CPR;
	}
}
