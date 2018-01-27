
package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.commands.climber.ClimberGamepadControl;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem { 
	private final double RPM_TO_UNITS = 600;
	DigitalInput Max = new DigitalInput(RobotMap.climbMax);
	DigitalInput Min = new DigitalInput(RobotMap.climbMin);
	TalonSRX talonWinch = new TalonSRX(RobotMap.climberWinchTalon);
	TalonSRX talonArm = new TalonSRX(RobotMap.climberArmTalon);

	public Climber() {
		int talonWinchLimit = (int) (RPM_TO_UNITS*SmartDashboard.getNumber("Climber RPM", 62));
		int talonArmLimit = (int) (RPM_TO_UNITS*SmartDashboard.getNumber("Climber Arm RPM", 62));
		this.talonWinch.configForwardSoftLimitThreshold(talonWinchLimit, 0);
		this.talonArm.configForwardSoftLimitThreshold(talonArmLimit, 0);
		this.talonArm.configForwardSoftLimitEnable(true, 0);
		this.talonWinch.configForwardSoftLimitEnable(true, 0);
	}
	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
		
		
	}
	

	public void rollInWinch(double RPM) {
		this.talonWinch.set(ControlMode.Velocity, RPM*600);
	}
	public boolean atMAX() {
		return Max.get();
	}
	public boolean atMIN() {
		return Min.get();
	}
	public void moveArm(double RPM) {
		if (!this.atMAX() && !this.atMIN()) {
				talonArm.set(ControlMode.Velocity, RPM*600);
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
