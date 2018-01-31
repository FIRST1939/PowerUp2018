package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {

	private double angle;

	public TurnToAngle(double angle) {
		this.requires(Robot.drivetrain);
		this.angle = angle;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.drivetrain.driveDistance(DistanceConstants.AUTO_LINE);
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
