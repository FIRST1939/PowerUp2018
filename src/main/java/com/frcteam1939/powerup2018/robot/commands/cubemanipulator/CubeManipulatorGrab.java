package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CubeManipulatorGrab extends Command {

	
	
	public CubeManipulatorGrab(){
		
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.cubeManipulator.CubeManipulatorGrab();
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
