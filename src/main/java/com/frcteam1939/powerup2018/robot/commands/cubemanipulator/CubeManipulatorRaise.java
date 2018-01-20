package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CubeManipulatorRaise extends Command {

	
	
	public CubeManipulatorRaise(){
		
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.cubeManipulator.raiseCubeManipulator();
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
