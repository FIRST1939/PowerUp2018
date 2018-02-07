package com.frcteam1939.powerup2018.commands.climber;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveArm extends Command {
	public double speedInput;
	public MoveArm(double speed) {
		requires(Robot.climber);
		speedInput = speed;
	}
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
			Robot.climber.moveArm(speedInput);
						
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.climber.atMAX();
	}
	
	@Override
	protected void end() {}

	
	@Override
	protected void interrupted() {}
}
