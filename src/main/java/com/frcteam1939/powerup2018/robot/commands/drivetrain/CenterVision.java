package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CenterVision extends Command{
	private double distance;

	public CenterVision() {
		this.requires(Robot.drivetrain);
		this.requires(Robot.vision);
		this.distance = distance;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.vision.center();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
	}
	

}
