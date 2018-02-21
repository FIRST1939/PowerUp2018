package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistance extends Command {

	private double distance;
	private boolean initialized = false;

	public DriveDistance(double distance) {
		this.requires(Robot.drivetrain);
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		Robot.drivetrain.zeroEncoders();
		Robot.drivetrain.resetGyro();
		this.initialized = true;
	}

	@Override
	protected void execute() {
		Robot.drivetrain.driveDistance(this.distance);
		Robot.drivetrain.turnPID.setSetpoint(0);
	}

	@Override
	protected boolean isFinished() {
		return this.initialized && this.distance - (Robot.drivetrain.getLeftPosition() + Robot.drivetrain.getRightPosition()) / 2 < 7;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
		this.initialized = false;
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.stop();
		this.initialized = false;
	}
}
