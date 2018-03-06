package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.CenterVision;

import edu.wpi.first.wpilibj.command.Command;

public class CubeManipulatorGamepadControl extends Command {

	private boolean isOpen = false;
	private boolean isClosed = true;
	private boolean wasPressed = false;

	public CubeManipulatorGamepadControl() {
		this.requires(Robot.cubeManipulator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {

		// Robot.oi.gamepad.a.whenPressed(new IntakeCube());
		// Robot.oi.gamepad.x.whenPressed(new OutputCubeMiddle());
		// Robot.oi.gamepad.y.whenPressed(new OutputCube());

		Robot.oi.gamepad.a.whenPressed(new CubeManipulatorLower());
		Robot.oi.gamepad.x.whenPressed(new CubeManipulatorMiddle());
		// Robot.oi.gamepad.y.whenPressed(new CubeManipulatorRaise());
		Robot.oi.gamepad.b.whenPressed(new CenterVision());

		double move = -Robot.oi.gamepad.getRightY() / 2;
		Robot.cubeManipulator.set(move);

		// Robot.oi.gamepad.back.whenPressed(new CubeManipulatorLower());
		// Robot.oi.gamepad.start.whenPressed(new CubeManipulatorRaise());

		if (Robot.oi.gamepad.rightButton.get() && !this.wasPressed) {
			this.wasPressed = true;
			if (this.isClosed) {
				Robot.cubeManipulator.cubeManipulatorWheelsOut();
				this.isClosed = false;
				this.isOpen = true;
			}

			else if (this.isOpen) {
				Robot.cubeManipulator.cubeManipulatorWheelsIn();
				this.isClosed = true;
				this.isOpen = false;
			}
		}

		if (!Robot.oi.gamepad.rightButton.get()) {
			this.wasPressed = false;
		}

		else {
			this.wasPressed = true;
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
