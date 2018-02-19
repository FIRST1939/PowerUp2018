package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorGamepadControl extends Command {

	public ElevatorGamepadControl() {
		this.requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double move = -Robot.oi.gamepad.getRightY();

		if (Robot.elevator.isAtTop() && move > 0) {
			move = 0;
			Robot.elevator.stop();
		}

		if (Robot.elevator.isAtBottom() && move < 0) {
			move = 0;
			Robot.elevator.stop();
		}

		if (Robot.elevator.isCloseToTop() && move > 0) {
			move = move * 0.2;
		}

		if (Robot.elevator.isCloseToBottom() && move < 0) {
			Robot.cubeManipulator.cubeManipulatorLower();
			move = move * 0.2;
		}

		Robot.elevator.set(move);

		if (Robot.oi.gamepad.b.get()) {
			Robot.elevator.stop();
		}

		//if (Robot.elevator.isAtTop()) {
		//	Robot.elevator.setEncoder(value);
		//}

		//if (Robot.elevator.isAtBottom()) {
		//	Robot.elevator.setEncoder(7);
		//}

		//if (Robot.elevator.isCloseToTop()) {
		//	Robot.elevator.setEncoder(11);
		//}

		//if (Robot.elevator.isCloseToBottom()) {
		//	Robot.elevator.setEncoder(value);
		//}

		// Robot.oi.gamepad.start.whenPressed(new SetElevatorHeight(DistanceConstants.PORTAL));
		// Robot.oi.gamepad.back.whenPressed(new SetElevatorHeight(12));
		// Robot.oi.gamepad.leftButton.whenPressed(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		// Robot.oi.gamepad.rightButton.whenPressed(new SetElevatorHeight(DistanceConstants.ELEVATOR_SCALE));
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		Robot.elevator.stop();
	}
}
