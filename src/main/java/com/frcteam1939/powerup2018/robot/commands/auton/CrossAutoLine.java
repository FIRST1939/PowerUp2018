package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CrossAutoLine extends Command {

	public CrossAutoLine() {
		this.requires(Robot.drivetrain);
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
