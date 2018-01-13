package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;
import edu.wpi.first.wpilibj.command.Command;
import com.frcteam1939.powerup2018.robot.Robot;

public class SetCubeManipulatorSpeed extends Command {

	private double speed;
	
	public SetCubeManipulatorSpeed(double speed){
		this.requires(Robot.cubeManipulator);
		this.speed = speed;
		
	}
	
	
	public void initialize(){
		//add initialize commands here
		
	}  
	
	protected void execute(){
		
		Robot.cubeManipulator.set(speed);
	}
	

	protected boolean isFinished() {
		return true;
	}

	protected void end(){}
	
	protected void interrupted(){}
}
