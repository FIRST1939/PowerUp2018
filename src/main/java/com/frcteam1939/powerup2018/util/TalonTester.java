package com.frcteam1939.powerup2018.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TalonTester extends Command {

	public TalonTester() {
		this.requires(Robot.elevator);
		this.requires(Robot.drivetrain);
		this.requires(Robot.climber);
		this.requires(Robot.cubeManipulator);
	}

	@Override
	protected void initialize() {
		SmartDashboard.putNumber("Talon ID", 0);
	}

	@Override
	protected void execute() {
		TalonSRX talon = new TalonSRX((int) SmartDashboard.getNumber("Talon ID", 0));
		talon.set(ControlMode.PercentOutput, Robot.oi.left.getY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
