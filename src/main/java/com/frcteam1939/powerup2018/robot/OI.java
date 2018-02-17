package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

	public final Joystick left = new Joystick(0);
	public final Joystick right = new Joystick(1);
	public final Gamepad gamepad = new Gamepad(2);

	private boolean wheelsAreIn = true;
	private boolean wheelsAreOut = false;

	private boolean isLowered = false;
	private boolean isMiddle = true;
	private boolean isRaised = false;

	public OI() {
		if (this.gamepad.a.get()) {
			if (this.isRaised) {
				Robot.cubeManipulator.cubeManipulatorMiddle();
				this.isMiddle = true;
				this.isRaised = false;
				this.isLowered = false;
			}

			if (this.isMiddle) {
				Robot.cubeManipulator.cubeManipulatorLower();
				this.isLowered = true;
				this.isMiddle = false;
				this.isRaised = false;
			}

			if (this.isLowered) {
				Robot.cubeManipulator.cubeManipulatorRaise();
				this.isRaised = true;
				this.isMiddle = false;
				this.isLowered = false;
			}
		}

		if (this.gamepad.x.get()) {
			if (this.wheelsAreIn) {
				Robot.cubeManipulator.cubeManipulatorWheelsOut();
				this.wheelsAreOut = true;
				this.wheelsAreIn = false;
			}

			if (this.wheelsAreOut) {
				Robot.cubeManipulator.cubeManipulatorWheelsIn();
				this.wheelsAreIn = true;
				this.wheelsAreOut = false;
			}
		}

		Robot.cubeManipulator.set(-this.gamepad.getRightY());
	}
}
