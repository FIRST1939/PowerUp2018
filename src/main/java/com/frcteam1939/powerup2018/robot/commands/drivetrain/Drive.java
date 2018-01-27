package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {

	private double value;

	public Drive(double value) {
		this.requires(Robot.drivetrain);
		this.value = value;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.drivetrain.driveDistance(this.value);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.stop();
	}
}