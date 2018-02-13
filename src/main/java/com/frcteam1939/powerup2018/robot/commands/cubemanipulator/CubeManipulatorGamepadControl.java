package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;

import edu.wpi.first.wpilibj.command.Command;

public class CubeManipulatorGamepadControl extends Command {

	public CubeManipulatorGamepadControl() {
		this.requires(Robot.cubeManipulator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {

		// Intake cube
		if (Robot.oi.gamepad.a.get()) {
			Robot.cubeManipulator.cubeManipulatorLower();
			Robot.cubeManipulator.set(CubeManipulator.IN_SPEED);
			Robot.cubeManipulator.cubeManipulatorWheelsIn();
			if (Robot.cubeManipulator.haveCube()) {
				Robot.cubeManipulator.set(0);
				Robot.elevator.setHeight(10);
				Robot.cubeManipulator.cubeManipulatorMiddle();
			}
		}

		// Output to scale/switch
		if (Robot.oi.gamepad.x.get()) {
			Robot.cubeManipulator.cubeManipulatorMiddle();
			Robot.cubeManipulator.set(CubeManipulator.OUT_SPEED);
			if (!Robot.cubeManipulator.haveCube()) {
				Robot.cubeManipulator.set(0);
				Robot.cubeManipulator.cubeManipulatorRaise();
			}
		}

		// Output to exchange zone
		if (Robot.oi.gamepad.y.get()) {
			Robot.cubeManipulator.cubeManipulatorLower();
			Robot.cubeManipulator.set(CubeManipulator.OUT_SPEED);
			if (!Robot.cubeManipulator.haveCube()) {
				Robot.cubeManipulator.set(0);
				Robot.cubeManipulator.cubeManipulatorRaise();
			}
		}
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
