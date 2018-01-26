package com.frcteam1939.powerup2018.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly reduces the number of magic numbers floating around.
 */
public class RobotMap {

	// Talons/Victors
	public static final int leftFrontTalon = 12;
	public static final int leftMidTalon = 0;
	public static final int leftBackTalon = 11;
	public static final int rightFrontTalon = 28;
	public static final int rightMidTalon = 0;
	public static final int rightBackTalon = 25;
	public static final int elevatorTalon = 0;
	public static final int cubeManipulatorTalon = 29;

	// Solenoids

	// One double solenoid on 0 and 1, second double solenoid on 6 and 7, one single solenoid on 2
	public static final int PCM = 0;
	public static final int cubeManipulatorRetractSolenoid = 0;
	public static final int cubeManipulatorGrabSolenoid = 0;
	public static final int leftShiftingGearbox = 0;
	public static final int rightShiftingGearbox = 0;

	// Analog
	public static final int pressureSensor = 0;

	// DIO

}
