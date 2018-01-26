package com.frcteam1939.powerup2018.commands.climber;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveArmDown extends Command{

	public MoveArmDown() {
		requires(Robot.climber);
	}
	@Override
	protected void initialize() {
		
	}
	
	@Override
	protected void execute() {
		if(DriverStation.getInstance().getMatchTime()<= 30 || SmartDashboard.getBoolean("Climber Override", false)) {
			Robot.climber.moveArm(0.5);		
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.climber.atMIN();
	}
	
	@Override
	protected void end() {}

	
	@Override
	protected void interrupted() {}

}
