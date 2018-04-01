package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToZero extends Command {

	public ElevatorToZero() {
		this.requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.set(-0.5);
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.isCloseToBottom() || Robot.elevator.isAtBottom();
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
