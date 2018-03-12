package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToScale extends Command {

	private double time;

	public ElevatorToScale() {
		this.requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.elevator.set(1);
		this.time = this.timeSinceInitialized();
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.isCloseToTop() || Robot.elevator.isAtTop() || this.time > 3;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
