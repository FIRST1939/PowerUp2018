package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorMotorSpeed extends Command {

	private double value;

	public SetElevatorMotorSpeed(double value) {
		this.requires(Robot.elevator);
		this.value = value;
	}

	@Override
	protected void initialize() {
		Robot.elevator.set(this.value);
	}

	@Override
	protected void execute() {
		Robot.elevator.set(this.value);
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
