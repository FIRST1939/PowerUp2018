package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.util.Gamepad;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

	public final Joystick left = new Joystick(0);
	public final Joystick right = new Joystick(1);
	public final Gamepad gamepad = new Gamepad(2);

	public OI() {}
}
