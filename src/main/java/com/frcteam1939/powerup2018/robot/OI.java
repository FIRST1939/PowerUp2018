package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.SetCubeManipulatorSpeed;
import com.frcteam1939.powerup2018.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public final Joystick left = new Joystick(0);
	public final Joystick right = new Joystick(1);
	public final Gamepad gamepad = new Gamepad(2);

	public OI() {
		this.gamepad.a.whenPressed(new SetCubeManipulatorSpeed(.1));
		this.gamepad.b.whenPressed(new SetCubeManipulatorSpeed(0));
	}
}
