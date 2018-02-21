package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorGamepadControl extends Command {

	private int count = 3;

	public ElevatorGamepadControl() {
		this.requires(Robot.elevator);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		double move = -Robot.oi.gamepad.getLeftY();

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

		if (move < 0) {
			move = move * 0.5;
		}

		Robot.elevator.set(move);

		if (Robot.oi.gamepad.back.get()) {
			Robot.elevator.stop();
		}

		if (Robot.elevator.isAtTop()) {
			Robot.elevator.setEncoder(70);
		}

		if (Robot.elevator.isAtBottom()) {
			Robot.elevator.setEncoder(7);
		}

		if (Robot.elevator.isCloseToTop()) {
			Robot.elevator.setEncoder(66);
		}

		if (Robot.elevator.isCloseToBottom()) {
			Robot.elevator.setEncoder(11);
		}

		// Robot.oi.gamepad.y.whenPressed(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		// if (Robot.oi.gamepad.rightButton.get()) {
		//	if (this.count % 3 == 0) {
		//		Robot.elevator.setHeight(DistanceConstants.ELEVATOR_SWITCH);
		//		this.count = 2;
		//	}

		//	else if (this.count % 3 == 2) {
		//		Robot.elevator.setHeight(DistanceConstants.ELEVATOR_SCALE);
		//		this.count = 1;
		//	}
		//
		//	else if (this.count % 3 == 1) {
		//	Robot.elevator.setHeight(12);
		//		this.count = 3;
		//	}
		//}
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
