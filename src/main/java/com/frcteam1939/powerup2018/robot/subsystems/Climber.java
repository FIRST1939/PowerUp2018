
package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.commands.climber.ClimberGamepadControl;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem { 
	DigitalInput Max = new DigitalInput(RobotMap.climbMax);
	DigitalInput Min = new DigitalInput(RobotMap.climbMin);
	TalonSRX talonWinch = new TalonSRX(RobotMap.climberWinchTalon);
	TalonSRX talonArm = new TalonSRX(RobotMap.climberArmTalon);
	Solenoid sole = new Solenoid(RobotMap.climberSolenoid);
	
	public Climber() {
	}
	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
	}
	
	public void extendBar() {
		this.sole.set(false);
	}
	public void rollInWinch(double set) {
		this.talonWinch.set(ControlMode.PercentOutput, set);
	}
	public boolean atMAX() {
		if(Max.get()) {
			return true;
		}
		else {
			return false;
		}		
	}
	public boolean atMIN() {
		if(Min.get()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void moveArm(double output) {
		if (!this.atMAX() && !this.atMIN()) {
				talonArm.set(ControlMode.PercentOutput, output);
		}
	}
	
	public void brakeWinch() {
		this.talonWinch.setNeutralMode(NeutralMode.Brake);		
	}
	public void disableWinchBrake() {
		this.talonWinch.setNeutralMode(NeutralMode.Coast);
	}
	public void brakeArm() {
		this.talonArm.setNeutralMode(NeutralMode.Brake);		
	}
	public void disableArmBrake() {
		this.talonArm.setNeutralMode(NeutralMode.Coast);
	}
}
