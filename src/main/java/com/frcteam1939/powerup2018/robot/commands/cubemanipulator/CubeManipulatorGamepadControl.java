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

		Robot.oi.gamepad.a.whenPressed(new IntakeCube());
		Robot.oi.gamepad.x.whenPressed(new OutputCubeMiddle());
		Robot.oi.gamepad.y.whenPressed(new OutputCube());

		Robot.oi.gamepad.leftTrigger.whenPressed(new SetCubeManipulatorSpeed(CubeManipulator.IN_SPEED));
		Robot.oi.gamepad.leftTrigger.whenReleased(new SetCubeManipulatorSpeed(0));
		Robot.oi.gamepad.rightTrigger.whenPressed(new SetCubeManipulatorSpeed(CubeManipulator.OUT_SPEED));
		Robot.oi.gamepad.rightTrigger.whenReleased(new SetCubeManipulatorSpeed(0));
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
