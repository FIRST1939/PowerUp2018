package com.frcteam1939.powerup2018.commands.climber;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClimberGamepadControl extends Command{
	public ClimberGamepadControl() {
		requires(Robot.climber);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
			
			Robot.oi.gamepad.y.whenPressed(new RollInClimber(1));
			Robot.oi.gamepad.b.whenPressed(new RollInClimber(0));
			double move = Robot.oi.right.getY();
			Robot.climber.moveArm(move);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}

}
