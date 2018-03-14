
package com.frcteam1939.powerup2018.robot;

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
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveDistance;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorHeight;
import com.frcteam1939.powerup2018.robot.subsystems.Climber;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.robot.subsystems.Drivetrain;
import com.frcteam1939.powerup2018.robot.subsystems.Elevator;
import com.frcteam1939.powerup2018.robot.subsystems.Lights;
import com.frcteam1939.powerup2018.robot.subsystems.SmartDashboardSubsystem;
import com.frcteam1939.powerup2018.util.AutonomousOptions;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

	public static Drivetrain drivetrain;
	public static Elevator elevator;
	public static Climber climber;
	public static CubeManipulator cubeManipulator;
	public static SmartDashboardSubsystem smartDashboard;
	//public static Vision vision;
	public static Lights lights;
	// CONSTRUCTOR FOR LIGHTS
	// public static Lights lights;

	static {
		try {
			cubeManipulator = new CubeManipulator();
			drivetrain = new Drivetrain();
			elevator = new Elevator();
			climber = new Climber();
			smartDashboard = new SmartDashboardSubsystem();
			// vision = new Vision();
			 lights = new Lights();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public static OI oi = new OI();
	private static AnalogInput pressureSensor = new AnalogInput(RobotMap.pressureSensor);

	private Command autonomousCommand;

	private SendableChooser<AutonomousOptions> chooserPosition = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserFirstChoice = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserSecondChoice = new SendableChooser<>();
	private SendableChooser<AutonomousOptions> chooserThirdChoice = new SendableChooser<>();

	@Override
	public void robotInit() {
		System.out.println("\n==========================================");
		System.out.println("         PowerUp 2018 Intializing");

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
		this.chooserSecondChoice.addObject("Still Switch", AutonomousOptions.STILL_DO_SWITCH);
		this.chooserSecondChoice.addObject("Still Scale", AutonomousOptions.STILL_DO_SCALE);
		SmartDashboard.putData("Second Choice Chooser", this.chooserSecondChoice);

		this.chooserThirdChoice.addObject("Do Nothing", AutonomousOptions.DO_NOTHING);
		this.chooserThirdChoice.addObject("Cross Auto Line", AutonomousOptions.CROSS_AUTO_LINE);
		this.chooserThirdChoice.addObject("Switch", AutonomousOptions.STILL_DO_SWITCH);
		this.chooserThirdChoice.addObject("Scale", AutonomousOptions.STILL_DO_SCALE);
		this.chooserThirdChoice.addObject("Still Switch", AutonomousOptions.STILL_DO_SWITCH);
		this.chooserThirdChoice.addObject("Still Scale", AutonomousOptions.STILL_DO_SCALE);
		SmartDashboard.putData("Third Choice Chooser", this.chooserThirdChoice);

		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(new DriveDistance(10));
		SmartDashboard.putData(new TurnToAngle(45));
		SmartDashboard.putData(new SetElevatorHeight(50));

		Robot.drivetrain.zeroEncoders();
		Robot.drivetrain.resetGyro();
		Robot.elevator.setEncoder(7);
		Robot.climber.zeroEncoder();

		CameraServer.getInstance().startAutomaticCapture();

		System.out.println("           Finished Intializing");
		System.out.println("==========================================/n");
	}

	@Override
	public void disabledInit() {
		Robot.drivetrain.disableBrakeMode();
		Robot.climber.disableBrakeMode();
		Robot.elevator.disableBrakeMode();
		Robot.cubeManipulator.cubeManipulatorWheelsOut();
		Robot.cubeManipulator.cubeManipulatorRaise();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// int retries = 100;
		// String gameData = DriverStation.getInstance().getGameSpecificMessage();
		// while (gameData.length() < 2 && retries > 0) {
		// 	retries--;
		// 	try {
		// 		Thread.sleep(5);
		// 	} catch (InterruptedException ie) {}
		// 	gameData = DriverStation.getInstance().getGameSpecificMessage();
		//
		// }

		// if (gameData.length() > 0) {
		// 	this.autonomousCommand = this.getAutonomousCommand(gameData);
		// }
		this.autonomousCommand = new CrossAutoLine();
		SmartDashboard.putString("Autonomous Command", this.autonomousCommand.getName());

		if (this.autonomousCommand != null) {
			this.autonomousCommand.start();
		}

		Robot.drivetrain.enableBrakeMode();
		Robot.climber.enableBrakeMode();
		Robot.elevator.enableBrakeMode();

		Robot.drivetrain.zeroEncoders();
		Robot.drivetrain.resetGyro();
		Robot.elevator.setEncoder(7);
		Robot.climber.zeroEncoder();

		// Robot.cubeManipulator.set(CubeManipulator.IN_SPEED);
		// Robot.cubeManipulator.cubeManipulatorMiddle();
		// if (Robot.cubeManipulator.haveCube()) {
		//	Robot.cubeManipulator.set(0);
		// 	Robot.cubeManipulator.cubeManipulatorWheelsIn();
		//}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.drivetrain.enableBrakeMode();
		Robot.climber.enableBrakeMode();
		Robot.elevator.enableBrakeMode();

		if (this.autonomousCommand != null) {
			this.autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
		// this.vision.testPixy1();
	}

	public static double getPressure() {
		return 250.0 * (pressureSensor.getVoltage() / 5.0) - 25.0;
	}

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
}
