package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.util.Gamepad;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.*;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public final Joystick left = new Joystick(0);
	public final Joystick right = new Joystick(1);
	public final Gamepad gamepad = new Gamepad(2);
	
	public OI(){
		
	//when right trigger is pressed the in-take will release the cube and deactivate the piston.
	
	this.gamepad.rightTrigger.whenPressed(new SetCubeManipulatorSpeed(CubeManipulator.OUT_SPEED));
	this.gamepad.rightTrigger.whenPressed(new CubeManipulatorDrop());
	
	//When right triggered is pressed then the in-take will take in the cube and activate the grabbing piston.
	this.gamepad.leftTrigger.whenPressed(new SetCubeManipulatorSpeed(CubeManipulator.IN_SPEED));
	this.gamepad.rightTrigger.whenPressed(new CubeManipulatorGrab());

	
	
	
	}
}
