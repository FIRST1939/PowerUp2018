
package com.frcteam1939.powerup2018.robot;


import com.frcteam1939.powerup2018.robot.subsystems.Climber;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.robot.subsystems.Drivetrain;
import com.frcteam1939.powerup2018.robot.subsystems.Elevator;
import com.frcteam1939.powerup2018.robot.subsystems.SmartDashboardSubsystem;
import com.frcteam1939.powerup2018.robot.subsystems.Vision;
import com.frcteam1939.powerup2018.util.AutonomousOptions;
import com.frcteam1939.powerup2018.util.DoNothing;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {

	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Climber climber;
	public static CubeManipulator cubeManipulator;

	public static SmartDashboardSubsystem smartDashboard;
	public static Vision vision;

	{
		try {
			cubeManipulator = new CubeManipulator();
			drivetrain = new Drivetrain();
			elevator = new Elevator();
			climber = new Climber();
			smartDashboard = new SmartDashboardSubsystem();
			vision = new Vision();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public static OI oi;
	private Command autonomousCommand;
	private SendableChooser<AutonomousOptions> chooserPosition = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserFirstChoice = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserSecondChoice = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserThirdChoice = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("\n==========================================");
		System.out.println("         PowerUp 2018 Intializing");

		oi = new OI();

		this.chooserPosition.addObject("Left", AutonomousOptions.LEFT);
		this.chooserPosition.addObject("Center", AutonomousOptions.CENTER);
		this.chooserPosition.addObject("Right", AutonomousOptions.RIGHT);
		SmartDashboard.putData("Position Chooser", this.chooserPosition);

		this.chooserFirstChoice.addObject("Do Nothing", AutonomousOptions.DO_NOTHING);
		this.chooserFirstChoice.addObject("Cross Auto Line", AutonomousOptions.CROSS_AUTO_LINE);
		this.chooserFirstChoice.addObject("Switch", AutonomousOptions.SWITCH);
		this.chooserFirstChoice.addObject("Scale", AutonomousOptions.SCALE);
		SmartDashboard.putData("First Choice Chooser", this.chooserFirstChoice);

		this.chooserSecondChoice.addObject("Do Nothing", AutonomousOptions.DO_NOTHING);
		this.chooserSecondChoice.addObject("Cross Auto Line", AutonomousOptions.CROSS_AUTO_LINE);
		this.chooserSecondChoice.addObject("Switch", AutonomousOptions.SWITCH);
		this.chooserSecondChoice.addObject("Scale", AutonomousOptions.SCALE);
		this.chooserSecondChoice.addObject("Switch", AutonomousOptions.STILL_DO_SWITCH);
		this.chooserSecondChoice.addObject("Scale", AutonomousOptions.STILL_DO_SCALE);
		SmartDashboard.putData("Second Choice Chooser", this.chooserSecondChoice);

		this.chooserThirdChoice.addObject("Do Nothing", AutonomousOptions.DO_NOTHING);
		this.chooserThirdChoice.addObject("Cross Auto Line", AutonomousOptions.CROSS_AUTO_LINE);
		this.chooserThirdChoice.addObject("Switch", AutonomousOptions.STILL_DO_SWITCH);
		this.chooserThirdChoice.addObject("Scale", AutonomousOptions.STILL_DO_SCALE);
		SmartDashboard.putData("Third Choice Chooser", this.chooserThirdChoice);

		SmartDashboard.putData(Scheduler.getInstance());

		System.out.println("           Finished Intializing");
		System.out.println("==========================================/n");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem information you want to clear when the robot is disabled.
	 */
	@Override
	public void disabledInit() {

		Robot.drivetrain.disableBrakeMode();
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
		Robot.drivetrain.enableBrakeMode();

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
		Robot.drivetrain.enableBrakeMode();

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

		if (this.chooserPosition.getSelected() == AutonomousOptions.CENTER) {
			if (this.chooserPosition.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'L') {
					// Go to the left of the switch and then cross the base line/double score cubes
				}

				else if (gameData.charAt(0) == 'R') {
					// Go to the right of the switch and then cross the base line/double score cubes
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'L') {
					// Go to the left of the scale and then double score cubes
				}

				else if (gameData.charAt(1) == 'R') {
					// Go to the right of the scale and then double score cubes
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				// Drive to the left and cross the auto line
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}
		}

		if (this.chooserPosition.getSelected() == AutonomousOptions.LEFT) {
			if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				// Drive Forward
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'L') {
					// Drive forward, score on left side of switch
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						// Drive forward
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
						// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SCALE) {
						if (gameData.charAt(1) == 'L') {
							// Drive forward to Scale, score on left side of Scale
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								// Drive forward
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
							}
						}
					}
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'L') {
					// Drive forward to Scale, score on left side of Scale
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						// Drive forward
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
						// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							// Drive forward to Switch, score on left side of Switch
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								// Drive forward
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								// Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, score on right side of Switch
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								// // Drive forward to area between Scale and Switch, turn right, drive between Scale and Switch, turn left, drive to Scale, score on right side of Scale
							}
						}
					}
				}
			}
		}

		if (this.chooserPosition.getSelected() == AutonomousOptions.RIGHT) {
			if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				// Drive Forward
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'R') {
					// Drive forward, score on right side of switch
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						// Drive forward
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
						// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SCALE) {
						if (gameData.charAt(1) == 'R') {
							// Drive forward to Scale, score on right side of Scale
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								// Drive forward
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, turn right, drive to Scale, score on left side of Scale
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
							}
						}
					}
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'R') {
					// Drive forward to Scale, score on right side of Scale
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						// Drive forward
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
						// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, turn right, drive to Scale, score on left side of Scale
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SWITCH) {
						if (gameData.charAt(0) == 'R') {
							// Drive forward to Switch, score on right side of Switch
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								// Drive forward
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								// Drive forward to area between Scale and Switch, turn left, drive between Scale and Switch, score on left side of Switch
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
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
