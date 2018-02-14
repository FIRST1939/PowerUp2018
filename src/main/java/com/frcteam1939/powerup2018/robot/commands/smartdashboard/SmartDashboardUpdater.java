package com.frcteam1939.powerup2018.robot.commands.smartdashboard;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

	public SmartDashboardUpdater() {
		this.requires(Robot.smartDashboard);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		SmartDashboard.putNumber("Left Speed", Robot.drivetrain.getLeftSpeed());
		SmartDashboard.putNumber("Left Position", Robot.drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Left Voltage", Robot.drivetrain.getLeftVoltage());
		SmartDashboard.putNumber("Left Error", Robot.drivetrain.getLeftError());
		SmartDashboard.putNumber("Left Percent Output", Robot.drivetrain.getLeftPercentOutput());

		SmartDashboard.putNumber("Right Speed", Robot.drivetrain.getRightSpeed());
		SmartDashboard.putNumber("Right Position", Robot.drivetrain.getRightPosition());
		SmartDashboard.putNumber("Right Voltage", Robot.drivetrain.getRightVoltage());
		SmartDashboard.putNumber("Right Error", Robot.drivetrain.getRightError());
		SmartDashboard.putNumber("Right Percent Output", Robot.drivetrain.getRightPercentOutput());

		SmartDashboard.putNumber("Heading", Robot.drivetrain.getHeading());

		SmartDashboard.putNumber("Elevator Revolutions", Robot.elevator.getRevolutions());
		SmartDashboard.putNumber("Elevator Height", Robot.elevator.getHeight());
		SmartDashboard.putNumber("Elevator Speed", Robot.elevator.getSpeed());

		SmartDashboard.putNumber("Climber Revolutions", Robot.climber.getPosition());

		SmartDashboard.putNumber("Pressure", Robot.getPressure());

		SmartDashboard.putBoolean("Have Cube", Robot.cubeManipulator.haveCube());

		SmartDashboard.putData("Turn PID", Robot.drivetrain.turnPID);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
