package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetDrivetrainMotorsSpeed extends Command {

	private double value;

	public SetDrivetrainMotorsSpeed(double value) {
		this.requires(Robot.drivetrain);
		this.value = value;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.setPercentOutput(this.value, this.value);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setPercentOutput(this.value, this.value);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
