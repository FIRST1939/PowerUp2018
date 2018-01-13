
package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.robot.subsystems.Drivetrain;
import com.frcteam1939.powerup2018.robot.subsystems.Elevator;
import com.frcteam1939.powerup2018.robot.subsystems.Pixy;
import com.frcteam1939.powerup2018.robot.subsystems.SmartDashboardSubsystem;
import com.frcteam1939.powerup2018.util.DoNothing;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {

	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static SmartDashboardSubsystem smartDashboard;
	public static Pixy pixy;
	{
		try {
			drivetrain = new Drivetrain();
			elevator = new Elevator();
			smartDashboard = new SmartDashboardSubsystem();
			pixy = new Pixy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public static OI oi;
	private Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("\n==========================================");
		System.out.println("         PowerUp 2018 Intializing");

		oi = new OI();

		SmartDashboard.putData(Scheduler.getInstance());

		System.out.println("           Finished Intializing");
		System.out.println("==========================================/n");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem information you want to clear when the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example) or additional comparisons to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		this.autonomousCommand = this.getAutonomousCommand(gameData);
		SmartDashboard.putString("Autonomous Command", this.autonomousCommand.getName());

		if (this.autonomousCommand != null) {
			this.autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (this.autonomousCommand != null) {
			this.autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private Command getAutonomousCommand(String gameData) {
		Command chosenCommand = new DoNothing();

		String startingPosition = "";
		String firstChoice = "";
		String secondChoice = "";
		String thirdChoice = "";

		if (SmartDashboard.getBoolean("Left", false)) {
			startingPosition = "Left";
		}

		else if (SmartDashboard.getBoolean("Center", false)) {
			startingPosition = "Center";
		}

		else if (SmartDashboard.getBoolean("Right", false)) {
			startingPosition = "Right";
		}

		if (SmartDashboard.getBoolean("1: Switch", false)) {
			firstChoice = "Switch";
		}

		else if (SmartDashboard.getBoolean("1: Scale", false)) {
			firstChoice = "Scale";
		}

		else if (SmartDashboard.getBoolean("1: Cross Auto Line", false)) {
			firstChoice = "Cross Auto Line";
		}

		else if (SmartDashboard.getBoolean("1: Do Nothing", false)) {
			firstChoice = "Do Nothing";
		}

		if (SmartDashboard.getBoolean("2: Switch", false)) {
			secondChoice = "Switch";
		}

		else if (SmartDashboard.getBoolean("2: Scale", false)) {
			secondChoice = "Scale";
		}

		else if (SmartDashboard.getBoolean("2: Cross Auto Line", false)) {
			secondChoice = "Cross Auto Line";
		}

		else if (SmartDashboard.getBoolean("2: Do Nothing", false)) {
			secondChoice = "Do Nothing";
		}

		else if (SmartDashboard.getBoolean("2: Still Do Switch", false)) {
			secondChoice = "Still Do Switch";
		}

		else if (SmartDashboard.getBoolean("2: Still Do Scale", false)) {
			secondChoice = "Still Do Scale";
		}

		if (SmartDashboard.getBoolean("3: Cross Auto Line", false)) {
			thirdChoice = "Cross Auto Line";
		}

		else if (SmartDashboard.getBoolean("3: Do Nothing", false)) {
			thirdChoice = "Do Nothing";
		}

		else if (SmartDashboard.getBoolean("3: Still Do Switch", false)) {
			thirdChoice = "Still Do Switch";
		}

		else if (SmartDashboard.getBoolean("3: Still Do Scale", false)) {
			thirdChoice = "Still Do Scale";
		}

		// ---------------------------------------------------------------

		if (startingPosition.equalsIgnoreCase("Center")) {
			if (firstChoice.equalsIgnoreCase("Switch")) {
				if (gameData.charAt(0) == 'L') {
					// Go to the left of the switch and then cross the base line/double score cubes
				}

				else if (gameData.charAt(0) == 'R') {
					// Go to the right of the switch and then cross the base line/double score cubes
				}
			}

			else if (firstChoice.equalsIgnoreCase("Scale")) {
				if (gameData.charAt(1) == 'L') {
					// Go to the left of the scale and then double score cubes
				}

				else if (gameData.charAt(1) == 'R') {
					// Go to the right of the scale and then double score cubes
				}
			}

			else if (firstChoice.equalsIgnoreCase("Cross Auto Line")) {
				// Drive to the left and cross the auto line
			}

			else if (firstChoice.equalsIgnoreCase("Do Nothing")) {
				chosenCommand = new DoNothing();
			}
		}

		if (startingPosition.equalsIgnoreCase("Left")) {
			if (firstChoice.equalsIgnoreCase("Cross Auto Line")) {
				// Drive Forward
			}

			else if (firstChoice.equalsIgnoreCase("Do Nothing")) {
				chosenCommand = new DoNothing();
			}

			else if (firstChoice.equalsIgnoreCase("Switch")) {
				if (gameData.charAt(0) == 'L') {
					// Drive forward, score on left side of switch
				}

				else {
					if (secondChoice.equalsIgnoreCase("Do Nothing")) {
						chosenCommand = new DoNothing();
					}

					else if (secondChoice.equalsIgnoreCase("Cross Auto Line")) {
						// Drive forward
					}

					else if (secondChoice.equalsIgnoreCase("Still Do Switch")) {
						// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
					}

					else if (secondChoice.equalsIgnoreCase("Scale")) {
						if (gameData.charAt(1) == 'L') {
							// Drive forward to Scale, score on left side of Scale
						}

						else {
							if (thirdChoice.equalsIgnoreCase("Do Nothing")) {
								chosenCommand = new DoNothing();
							}

							else if (thirdChoice.equalsIgnoreCase("Cross Auto Line")) {
								// Drive forward
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Scale")) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Switch")) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
							}
						}
					}
				}
			}

			else if (firstChoice.equalsIgnoreCase("Scale")) {
				if (gameData.charAt(1) == 'L') {
					// Drive forward to Scale, score on left side of Scale
				}

				else {
					if (secondChoice.equalsIgnoreCase("Do Nothing")) {
						chosenCommand = new DoNothing();
					}

					else if (secondChoice.equalsIgnoreCase("Cross Auto Line")) {
						// Drive forward
					}

					else if (secondChoice.equalsIgnoreCase("Still Do Scale")) {
						// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
					}

					else if (secondChoice.equalsIgnoreCase("Switch")) {
						if (gameData.charAt(0) == 'L') {
							// Drive forward to Switch, score on left side of Switch
						}

						else {
							if (thirdChoice.equalsIgnoreCase("Do Nothing")) {
								chosenCommand = new DoNothing();
							}

							else if (thirdChoice.equalsIgnoreCase("Cross Auto Line")) {
								// Drive forward
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Switch")) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Scale")) {
								// // Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
							}
						}
					}
				}
			}
		}

		if (startingPosition.equalsIgnoreCase("Right")) {
			if (firstChoice.equalsIgnoreCase("Cross Auto Line")) {
				// Drive Forward
			}

			else if (firstChoice.equalsIgnoreCase("Do Nothing")) {
				chosenCommand = new DoNothing();
			}

			else if (firstChoice.equalsIgnoreCase("Switch")) {
				if (gameData.charAt(0) == 'R') {
					// Drive forward, score on right side of switch
				}

				else {
					if (secondChoice.equalsIgnoreCase("Do Nothing")) {
						chosenCommand = new DoNothing();
					}

					else if (secondChoice.equalsIgnoreCase("Cross Auto Line")) {
						// Drive forward
					}

					else if (secondChoice.equalsIgnoreCase("Still Do Switch")) {
						// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
					}

					else if (secondChoice.equalsIgnoreCase("Scale")) {
						if (gameData.charAt(1) == 'R') {
							// Drive forward to Scale, score on right side of Scale
						}

						else {
							if (thirdChoice.equalsIgnoreCase("Do Nothing")) {
								chosenCommand = new DoNothing();
							}

							else if (thirdChoice.equalsIgnoreCase("Cross Auto Line")) {
								// Drive forward
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Scale")) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, turn right, drive to Scale, score on left side of Scale
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Switch")) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
							}
						}
					}
				}
			}

			else if (firstChoice.equalsIgnoreCase("Scale")) {
				if (gameData.charAt(1) == 'R') {
					// Drive forward to Scale, score on right side of Scale
				}

				else {
					if (secondChoice.equalsIgnoreCase("Do Nothing")) {
						chosenCommand = new DoNothing();
					}

					else if (secondChoice.equalsIgnoreCase("Cross Auto Line")) {
						// Drive forward
					}

					else if (secondChoice.equalsIgnoreCase("Still Do Scale")) {
						// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, turn right, drive to Scale, score on left side of Scale
					}

					else if (secondChoice.equalsIgnoreCase("Switch")) {
						if (gameData.charAt(0) == 'R') {
							// Drive forward to Switch, score on right side of Switch
						}

						else {
							if (thirdChoice.equalsIgnoreCase("Do Nothing")) {
								chosenCommand = new DoNothing();
							}

							else if (thirdChoice.equalsIgnoreCase("Cross Auto Line")) {
								// Drive forward
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Switch")) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
							}

							else if (thirdChoice.equalsIgnoreCase("Still Do Scale")) {
								// // Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, turn right, drive to Scale, score on left side of Scale
							}
						}
					}
				}
			}
		}
		return chosenCommand;
	}
}
