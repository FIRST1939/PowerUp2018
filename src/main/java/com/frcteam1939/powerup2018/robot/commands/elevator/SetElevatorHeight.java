package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorHeight extends Command {

	private double height;

	public SetElevatorHeight(double height) {
		this.requires(Robot.elevator);
		this.height = height;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.setHeight(this.height);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(this.height - Robot.elevator.getHeight()) < 20;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		Robot.elevator.stop();
	}
}
