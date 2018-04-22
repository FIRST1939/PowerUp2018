
package com.frcteam1939.powerup2018.robot;

import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToLeftSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.CenterWallToRightSwitch;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToLeftScale;
import com.frcteam1939.powerup2018.robot.commands.auton.LeftWallToLeftSwitch;
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
import edu.wpi.first.wpilibj.DriverStation;
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
	// public static Vision vision;
	public static Lights lights;

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

	private SendableChooser<AutonomousOptions> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		System.out.println("\n==========================================");
		System.out.println("         PowerUp 2018 Intializing");

		this.chooser.addObject("Center", AutonomousOptions.CENTER);
		this.chooser.addObject("Left - Prioritize Switch", AutonomousOptions.LEFT_PRIORITIZE_SWITCH);
		this.chooser.addObject("Right - Prioritize Switch", AutonomousOptions.RIGHT_PRIORITIZE_SWITCH);
		this.chooser.addObject("Left - Prioritize Scale", AutonomousOptions.LEFT_PRIORITIZE_SCALE);
		this.chooser.addObject("Right - Prioritize Scale", AutonomousOptions.RIGHT_PRIORITIZE_SCALE);
		SmartDashboard.putData("Autonomous Chooser", this.chooser);

		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(new DriveDistance(10));
		SmartDashboard.putData(new TurnToAngle(45));
		SmartDashboard.putData(new SetElevatorHeight(50));
		SmartDashboard.putData(new LeftWallToLeftSwitch());
		SmartDashboard.putData(new RightWallToRightSwitch());
		SmartDashboard.putData(new CenterWallToRightSwitch());
		SmartDashboard.putData(new CenterWallToLeftSwitch());
		SmartDashboard.putData(new LeftWallToLeftScale());
		SmartDashboard.putData(new RightWallToRightScale());

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
		int retries = 100;
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		while (gameData.length() < 2 && retries > 0) {
			retries--;
			try {
				Thread.sleep(5);
			} catch (InterruptedException ie) {}
			gameData = DriverStation.getInstance().getGameSpecificMessage();
		}

		// DEFAULT
		// if (gameData.length() > 0) {
		// 	this.autonomousCommand = this.getAutonomousCommand(gameData);
		// }

		// CENTER
		if (gameData.charAt(0) == 'L') {
			this.autonomousCommand = new CenterWallToLeftSwitch();
		} else if (gameData.charAt(0) == 'R') {
			this.autonomousCommand = new CenterWallToRightSwitch();
		}

		// CHOOSE BETWEEN LEFT SIDE - PRIORITIZE SWITCH
		// if (gameData.charAt(0) == 'L') {
		// 	this.autonomousCommand = new LeftWallToLeftSwitch();
		// } else if (gameData.charAt(1) == 'L') {
		// 	this.autonomousCommand = new LeftWallToLeftScale();
		// } else {
		// 	this.autonomousCommand = new CrossAutoLine();
		// }

		// CHOOSE BETWEEN RIGHT SIDE - PRIORITIZE SWITCH
		// if (gameData.charAt(0) == 'R') {
		// 	this.autonomousCommand = new RightWallToRightSwitch();
		// } else if (gameData.charAt(1) == 'R') {
		// 	this.autonomousCommand = new RightWallToRightScale();
		// } else {
		// 	this.autonomousCommand = new CrossAutoLine();
		// }

		// CHOOSE BETWEEN LEFT SIDE - PRIORITIZE SCALE
		// if (gameData.charAt(1) == 'L') {
		// 	this.autonomousCommand = new LeftWallToLeftScale();
		// } else if (gameData.charAt(0) == 'L') {
		// 	this.autonomousCommand = new LeftWallToLeftSwitch();
		// } else {
		// 	this.autonomousCommand = new CrossAutoLine();
		// }

		// CHOOSE BETWEEN RIGHT SIDE - PRIORITIZE SCALE
		// if (gameData.charAt(1) == 'R') {
		// 	this.autonomousCommand = new RightWallToRightScale();
		// } else if (gameData.charAt(0) == 'R') {
		// 	this.autonomousCommand = new RightWallToRightSwitch();
		// } else {
		// 	this.autonomousCommand = new CrossAutoLine();
		// }

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
	}

	public static double getPressure() {
		return 250.0 * (pressureSensor.getVoltage() / 5.0) - 25.0;
	}
}
