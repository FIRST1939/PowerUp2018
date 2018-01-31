
package com.frcteam1939.powerup2018.robot;

<<<<<<<HEAD

import com.frcteam1939.powerup2018.robot.commands.drivetrain.Drive;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.robot.subsystems.Drivetrain;
//import com.frcteam1939.powerup2018.util.Vision;
=======
import com.frcteam1939.powerup2018.robot.commands.auton.CenterCrossAutoLine;
import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToLeftScale;
import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToLeftSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToRightScale;
import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToRightSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.CrossAutoLine;
import com.frcteam1939.powerup2018.robot.commands.auton.DoNothing;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToLeftScale;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToLeftSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToRightScale;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToRightSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.RightWallToLeftScale;
import com.frcteam1939.powerup2018.robot.commands.auton.RightWallToLeftSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.RightWallToRightScale;
import com.frcteam1939.powerup2018.robot.commands.auton.RightWallToRightSwitch;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.FindMaxSpeed;
import com.frcteam1939.powerup2018.robot.subsystems.Climber;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.robot.subsystems.Drivetrain;
import com.frcteam1939.powerup2018.robot.subsystems.Elevator;
import com.frcteam1939.powerup2018.robot.subsystems.SmartDashboardSubsystem;
import com.frcteam1939.powerup2018.robot.subsystems.Vision;
import com.frcteam1939.powerup2018.util.AutonomousOptions;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the IterativeRobot documentation. If you change the name of this class or the package after creating this project, you must also update the manifest file in the resource directory.
 */
public class Robot extends TimedRobot {

	public static Drivetrain drivetrain;<<<<<<<HEAD
	//	public static Vision vision = new Vision();
	public static CubeManipulator cubeManipulator;=======
	public static Elevator elevator;
	public static Climber climber;
	public static CubeManipulator cubeManipulator;

	public static SmartDashboardSubsystem smartDashboard;
	public static Vision vision;

	>>>>>>>master
	{
		try {
			cubeManipulator = new CubeManipulator();
			drivetrain = new Drivetrain();
<<<<<<< HEAD
=======
			elevator = new Elevator();
			climber = new Climber();
			smartDashboard = new SmartDashboardSubsystem();
			vision = new Vision();
>>>>>>> master
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

		SmartDashboard.putData(new Drive(30));
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(new FindMaxSpeed());

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
	}<<<<<<<HEAD

}=======

	private Command getAutonomousCommand(String gameData) {
		Command chosenCommand = new DoNothing();

		if (this.chooserPosition.getSelected() == AutonomousOptions.CENTER) {
			if (this.chooserPosition.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'L') {
					chosenCommand = new CenterWallToLeftSwitch();
				}

				else if (gameData.charAt(0) == 'R') {
					chosenCommand = new CenterWallToRightSwitch();
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'L') {
					chosenCommand = new CenterWallToLeftScale();
				}

				else if (gameData.charAt(1) == 'R') {
					chosenCommand = new CenterWallToRightScale();
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				chosenCommand = new CenterCrossAutoLine();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}
		}

		if (this.chooserPosition.getSelected() == AutonomousOptions.LEFT) {
			if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				chosenCommand = new CrossAutoLine();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'L') {
					chosenCommand = new LeftWallToLeftSwitch();
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						chosenCommand = new CrossAutoLine();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
						chosenCommand = new LeftWallToRightSwitch();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SCALE) {
						if (gameData.charAt(1) == 'L') {
							chosenCommand = new LeftWallToLeftScale();
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								chosenCommand = new CrossAutoLine();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								chosenCommand = new LeftWallToRightScale();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								chosenCommand = new LeftWallToRightSwitch();
							}
						}
					}
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'L') {
					chosenCommand = new LeftWallToLeftScale();
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						chosenCommand = new CrossAutoLine();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
						chosenCommand = new LeftWallToRightScale();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SWITCH) {
						if (gameData.charAt(0) == 'L') {
							chosenCommand = new LeftWallToLeftSwitch();
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								chosenCommand = new CrossAutoLine();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								chosenCommand = new LeftWallToRightSwitch();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								chosenCommand = new LeftWallToRightScale();
							}
						}
					}
				}
			}
		}

		if (this.chooserPosition.getSelected() == AutonomousOptions.RIGHT) {
			if (this.chooserFirstChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
				chosenCommand = new CrossAutoLine();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
				chosenCommand = new DoNothing();
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SWITCH) {
				if (gameData.charAt(0) == 'R') {
					chosenCommand = new RightWallToRightSwitch();
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						chosenCommand = new CrossAutoLine();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
						chosenCommand = new RightWallToLeftSwitch();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SCALE) {
						if (gameData.charAt(1) == 'R') {
							chosenCommand = new RightWallToRightScale();
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								chosenCommand = new CrossAutoLine();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								chosenCommand = new RightWallToLeftScale();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								chosenCommand = new RightWallToLeftSwitch();
							}
						}
					}
				}
			}

			else if (this.chooserFirstChoice.getSelected() == AutonomousOptions.SCALE) {
				if (gameData.charAt(1) == 'R') {
					chosenCommand = new RightWallToRightScale();
				}

				else {
					if (this.chooserSecondChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
						chosenCommand = new DoNothing();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
						chosenCommand = new CrossAutoLine();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
						chosenCommand = new RightWallToLeftScale();
					}

					else if (this.chooserSecondChoice.getSelected() == AutonomousOptions.SWITCH) {
						if (gameData.charAt(0) == 'R') {
							chosenCommand = new RightWallToRightSwitch();
						}

						else {
							if (this.chooserThirdChoice.getSelected() == AutonomousOptions.DO_NOTHING) {
								chosenCommand = new DoNothing();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.CROSS_AUTO_LINE) {
								chosenCommand = new CrossAutoLine();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SWITCH) {
								chosenCommand = new RightWallToLeftSwitch();
							}

							else if (this.chooserThirdChoice.getSelected() == AutonomousOptions.STILL_DO_SCALE) {
								chosenCommand = new RightWallToLeftScale();
							}
						}
					}
				}
			}
		}
		return chosenCommand;
	}
}>>>>>>>master
