package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorGamepadControl extends Command {

	private static final double DEAD_BAND = 0.1;

	public ElevatorGamepadControl() {
		this.requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {

		if (Robot.elevator.getHeight() <= DistanceConstants.LOW_LIMIT) {
			Robot.cubeManipulator.set(0);
		}

		if (Robot.elevator.getHeight() >= DistanceConstants.HIGH_LIMIT) {
			Robot.cubeManipulator.set(0);
		}

		if (Robot.elevator.getHeight() <= 9) {
			Robot.cubeManipulator.cubeManipulatorLower();
		}

		double move = Robot.oi.gamepad.getLeftY();
		if (move < DEAD_BAND) {
			move = 0;
		}

		Robot.elevator.set(move);

		Robot.oi.gamepad.start.whenPressed(new SetElevatorHeight(DistanceConstants.PORTAL));
		Robot.oi.gamepad.back.whenPressed(new SetElevatorHeight(8));
		Robot.oi.gamepad.leftButton.whenPressed(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		Robot.oi.gamepad.rightButton.whenPressed(new SetElevatorHeight(DistanceConstants.ELEVATOR_SCALE));
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
