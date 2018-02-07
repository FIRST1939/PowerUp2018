package com.frcteam1939.powerup2018.commands.climber;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberGamepadControl extends Command {

	private static final double DEAD_BAND = 0.1;

	public ClimberGamepadControl() {
		this.requires(Robot.climber);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double move = Robot.oi.gamepad.getRightY();
		if (Math.abs(move) < DEAD_BAND) {
			move = 0;
		}
		Robot.climber.set(move);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}

}
