
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

	private static final int TIMEOUT_MS = 0;

	private static final int CPR = 4096;
	public double MAX_REV = 0.8;

	public Climber() {
		this.talon.setNeutralMode(NeutralMode.Brake);
		this.talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, TIMEOUT_MS);
		this.talon.setSensorPhase(true);
		this.talon.configOpenloopRamp(2, TIMEOUT_MS);
		this.talon.configNominalOutputForward(+0, TIMEOUT_MS);
		this.talon.configNominalOutputReverse(-0, TIMEOUT_MS);
		this.talon.configPeakOutputForward(+1, TIMEOUT_MS);
		this.talon.configPeakOutputReverse(-1, TIMEOUT_MS);
		this.talon.enableVoltageCompensation(true);
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

	public void zeroEncoder() {
		this.talon.setSelectedSensorPosition(0, 0, TIMEOUT_MS);
	}
}
