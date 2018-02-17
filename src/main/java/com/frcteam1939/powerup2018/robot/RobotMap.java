package com.frcteam1939.powerup2018.robot;

public class RobotMap {

	// Talons/Victors
	public static final int leftFrontTalon = 10;
	public static final int leftMidVictor = 31;
	public static final int leftBackVictor = 33;
	public static final int rightFrontTalon = 12;
	public static final int rightMidVictor = 30;
	public static final int rightBackVictor = 32;
	public static final int climberTalon = 25;
	public static final int elevatorTalon = 27;
	public static final int masterCubeManipulatorTalon = 28;
	public static final int slaveCubeManipulatorTalon = 13;

	// Solenoids
	public static final int PCM = 0;
	public static final int cubeManipulatorAngleTop = 5;
	public static final int cubeManipulatorAngleBottom = 3;
	public static final int cubeManipulatorWheelsSolenoid = 2;

	// Analog
	public static final int pressureSensor = 0;

	// DIO
	public static final int cubeManipulatorBanner = 0;
	public static final int elevatorAtTop = 4;
	public static final int elevatorAtBottom = 1;
	public static final int elevatorCloseToTop = 3;
	public static final int elevatorCloseToBottom = 2;

}
