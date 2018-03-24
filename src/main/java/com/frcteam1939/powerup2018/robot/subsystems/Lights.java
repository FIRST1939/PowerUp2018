package com.frcteam1939.powerup2018.robot.subsystems;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lights extends Subsystem {

	private DigitalOutput pin1 = new DigitalOutput(RobotMap.pinOne);
	private DigitalOutput pin2 = new DigitalOutput(RobotMap.pinTwo);
	private DigitalOutput pin3 = new DigitalOutput(RobotMap.pinThree);
	private DigitalOutput pin4 = new DigitalOutput(RobotMap.pinFour);

	// intToBits()
	//
	// Converts an integer into an array of bits representing the integer
    // in binary.  'NUM_BITS' defines the number of bits we need from the
    // integar.  Conversion is done using bitwise comparisions.

	private boolean[] intToBits(int value) {
		final int NUM_BITS = 4;
		boolean[] bits = new boolean[NUM_BITS];
		for (int i = (NUM_BITS - 1); i >= 0; i--) {
			bits[i] = (value & (1 << i)) != 0;
		}
		return bits;
	}

	// getLightProgram()
	//
	// Figure out what lighting program we want to do based upon robot
	// and game status

	// Program Schedule:
	//
	//  0 - Driver station not attached (test mode)
	//  1 - Robot disabled
	//  2 - Autonomous mode (Blue Alliance)
	//  3 - Elevator rising (Blue Alliance)
	//  4 - Elevator descending (Blue Alliance)
	//  5 - Elevator at top (Blue Alliance)
	//  6 - Climbing
	//  7 - Default (Blue Alliance)
	//  8 - Not used
	//  9 - Not used
	// 10 - Autonomous mode (Red Alliance)
	// 11 - Elevator rising (Red Alliance)
	// 12 - Elevator descending (Red Alliance)
	// 13 - Elevator at top (Red Alliance)
	// 14 - Climbing
	// 15 - Default (Red Alliance)

	private int getLightProgram() {

		boolean isAllianceBlue = (DriverStation.getInstance().getAlliance() == Alliance.Blue);

		// No attached drivers station
		if (!DriverStation.getInstance().isDSAttached()) {
			return 0;
		}

		// Not enabled
		if (!DriverStation.getInstance().isEnabled()) {
			return 1;
		}

		// In autonomous mode
		if (DriverStation.getInstance().isAutonomous()) {
			return (isAllianceBlue) ? 2 : 10;
		}

		// Elevator going up
		if (Robot.elevator.isRaising()) {
			return (isAllianceBlue) ? 3 : 11;
		}

		// Elevator going down
		if (Robot.elevator.isLowering()) {
			return (isAllianceBlue) ? 4 : 12;
		}

		// Elevator at top
		if (Robot.elevator.isAtTop()) {
			return (isAllianceBlue) ? 5 : 13;
		}

		// Climbing
		if (Robot.climber.hasClimbed()) {
			return (isAllianceBlue) ? 6 : 14;
		}

		return (isAllianceBlue) ? 7 : 15; // default light program
	}

	// Launch a new thread to continuously monitor robot and game state
	// and set the light program accordingly by communicating with the
	// Arduino via the RoboRIO pins
	//
	public Lights() {
		new Thread(() -> {

			int testCount = 0;	

			while (!Thread.interrupted()) {

				int lightProgram = getLightProgram();

				// Handle test mode (driver station not attached)
				if (lightProgram == 0) {
					lightProgram = testCount / 100;  // integer division
					testCount = (testCount == 1590) ? 0 : (testCount + 1);
				}

				// Show the selected program on the dashboard
				SmartDashboard.putNumber("i", lightProgram);

				// Turn on or off appropriate digital port pins according to value
				boolean[] bits = intToBits(lightProgram);
				Lights.this.pin1.set(bits[3]);
				Lights.this.pin2.set(bits[2]);
				Lights.this.pin3.set(bits[1]);
				Lights.this.pin4.set(bits[0]);

				try {
					Thread.sleep(100);
				} catch (Exception e) {}
			}
		}).start();
	}

	@Override
	protected void initDefaultCommand() {}
}
