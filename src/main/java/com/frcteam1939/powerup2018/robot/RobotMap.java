package com.frcteam1939.powerup2018.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers floating around.
 */
public class RobotMap {

	// Talons/Victors
	public static final int leftFrontTalon = 10;
	public static final int leftMidTalon = 33;
	public static final int leftBackTalon = 31;
	public static final int rightFrontTalon = 12;
	public static final int rightMidTalon = 32;
	public static final int rightBackTalon = 30;
	public static final int climberTalon = 25;
	public static final int elevatorTalon = 27;
	public static final int masterCubeManipulatorTalon = 28;
	public static final int slaveCubeManipulatorTalon = 13;

	// Solenoids

	// One double solenoid on 0 and 1, second double solenoid on 6 and 7, one single solenoid on 2
	public static final int PCM = 0;
	public static final int leftShiftingGearboxUp = 0;
	public static final int leftShiftingGearboxDown = 0;
	public static final int rightShiftingGearboxUp = 0;
	public static final int rightShiftingGearboxDown = 0;
	public static final int climberSolenoid = 0;
	public static final int cubeManipulatorAngleTop = 0;
	public static final int cubeManipulatorAngleBottom = 0;
	public static final int cubeManipulatorWheelsSolenoid = 0;
	public static final int leftShiftingGearbox = 0;
	public static final int rightShiftingGearbox = 0;

	// Analog
	public static final int pressureSensor = 0;

	// DIO
	public static final int climbMax = 0;
	public static final int climbMin = 0;
	public static final int cubeManipulatorBanner = 0;

}
