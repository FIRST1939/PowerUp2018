package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CubeManipulatorGamepadControl extends Command {

	private boolean wheelsAreIn = true;
	private boolean wheelsAreOut = false;

	private boolean isLowered = false;
	private boolean isMiddle = true;
	private boolean isRaised = false;

	public CubeManipulatorGamepadControl() {
		this.requires(Robot.cubeManipulator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (Robot.oi.gamepad.a.get()) {
			Robot.cubeManipulator.cubeManipulatorLower();
		}

		if (Robot.oi.gamepad.x.get()) {
			Robot.cubeManipulator.cubeManipulatorMiddle();
		}

		if (Robot.oi.gamepad.y.get()) {
			Robot.cubeManipulator.cubeManipulatorRaise();
		}

		if (Robot.oi.gamepad.leftTrigger.get()) {
			Robot.cubeManipulator.cubeManipulatorWheelsIn();
		}

		if (Robot.oi.gamepad.rightTrigger.get()) {
			Robot.cubeManipulator.cubeManipulatorWheelsOut();
		}

		Robot.cubeManipulator.set(-Robot.oi.gamepad.getLeftY());
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
