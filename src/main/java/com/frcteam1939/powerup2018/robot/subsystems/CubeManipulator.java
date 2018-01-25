package com.frcteam1939.powerup2018.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {
	
	public static final double OUT_SPEED = .6;
	public static final double IN_SPEED = -.6;
	
	private TalonSRX masterTalon = new TalonSRX(RobotMap.leftCubeManipulatorTalon);
	private TalonSRX slaveTalon = new TalonSRX(RobotMap.rightCubeManipulatorTalon);
	
	private Solenoid cubeSolenoidRaise = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorRetractSolenoid);
	private Solenoid cubeSolenoidGrab = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorGrabSolenoid);

	public CubeManipulator() {
		slaveTalon.setInverted(true);
		slaveTalon.follow(this.masterTalon);
	} 

	@Override
	public void initDefaultCommand() {}
	
	public void CubeManipulatorGrab(){
		this.cubeSolenoidGrab.set(true);
	}
	
	public void CubeManipulatorDrop(){
		this.cubeSolenoidGrab.set(false);
	}
	
	public void raiseCubeManipulator(){
		this.cubeSolenoidRaise.set(true);
	}
	
	public void lowerCubeManipulator(){
		this.cubeSolenoidRaise.set(false);
	}
	
	public void set(double value){
		this.masterTalon.set(ControlMode.PercentOutput, value);
	}
}
