package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetCubeManipulatorSpeed extends Command {

	private double speed;

	public SetCubeManipulatorSpeed(double speed) {
		this.requires(Robot.cubeManipulator);
		this.speed = speed;
	}

	@Override
	public void initialize() {}

	@Override
	protected void execute() {
		Robot.cubeManipulator.set(this.speed);
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
