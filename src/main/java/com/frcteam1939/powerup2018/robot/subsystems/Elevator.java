package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.elevator.ElevatorGamepadControl;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private static final int TIMEOUT_MS = 10;

	private static final double P = 0;
	private static final double I = 0;
	private static final double D = 0;
	private static final double F = 0;

	private TalonSRX talon = new TalonSRX(RobotMap.elevatorTalon);

	public Elevator() {
		this.talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, TIMEOUT_MS);
		this.talon.config_kP(0, P, TIMEOUT_MS);
		this.talon.config_kI(0, I, TIMEOUT_MS);
		this.talon.config_kD(0, D, TIMEOUT_MS);
		this.talon.config_kF(0, F, TIMEOUT_MS);
	}

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ElevatorGamepadControl());
	}
}
