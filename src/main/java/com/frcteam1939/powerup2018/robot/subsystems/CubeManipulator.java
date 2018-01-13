package com.frcteam1939.powerup2018.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {
	
	private TalonSRX talon1 = new TalonSRX(RobotMap.cubeManipulatorTalon);
	
	public void CubeIntake(){
		
	
	}
	
	public CubeManipulator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	
	public void set(double value){
		this.talon1.set(ControlMode.PercentOutput, value);
	}
}
