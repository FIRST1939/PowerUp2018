package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Aim extends Command {
	public Aim() {
		this.requires(Robot.drivetrain);
		this.requires(Robot.vision);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.drivetrain.drive(0, Robot.vision.getX());
		
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
