
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
	private DigitalInput Max = new DigitalInput(RobotMap.climbMax);
	private DigitalInput Min = new DigitalInput(RobotMap.climbMin);
	private TalonSRX talonWinch = new TalonSRX(RobotMap.climberWinchTalon);
	private TalonSRX talonArm = new TalonSRX(RobotMap.climberArmTalon);
	private boolean isWinchBrake = true;
	private boolean isArmBrake = true;

	public Climber() {
		int talonWinchLimit = (int) (RPM_TO_UNITS*SmartDashboard.getNumber("Climber RPM", 62));
		int talonArmLimit = (int) (RPM_TO_UNITS*SmartDashboard.getNumber("Climber Arm RPM", 62));
		this.talonWinch.configForwardSoftLimitThreshold(talonWinchLimit, 0);
		this.talonArm.configForwardSoftLimitThreshold(talonArmLimit, 0);
		this.talonArm.configForwardSoftLimitEnable(true, 0);
		this.talonWinch.configForwardSoftLimitEnable(true, 0);
		this.talonWinch.setNeutralMode(NeutralMode.Brake);
		this.talonArm.setNeutralMode(NeutralMode.Brake);
	}
	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
		
		
	}
	

	public void rollInWinch(double speed) {
		this.talonWinch.set(ControlMode.PercentOutput, speed);
	}
	public boolean atMAX() {
		return Max.get();
	}
	public boolean atMIN() {
		return Min.get();
	}
	public void moveArm(double speed) {
		if (this.atMAX() ) {
			if(speed<0) {
				talonArm.set(ControlMode.PercentOutput, speed);
			}	
		}
		else if(this.atMIN()) {
			if(speed>0) {
				talonArm.set(ControlMode.PercentOutput, speed);
			}
		}
	}
	
	public void brakeWinch() {
		this.talonWinch.setNeutralMode(NeutralMode.Brake);	
		isWinchBrake = true;
	}
	public void disableWinchBrake() {
		this.talonWinch.setNeutralMode(NeutralMode.Coast);
		isWinchBrake = false;
	}
	public boolean checkWinchBrake() {
		return isWinchBrake;
	}
	public boolean checkArmBrake() {
		return isArmBrake;
	}
	public void brakeArm() {
		this.talonArm.setNeutralMode(NeutralMode.Brake);
		isArmBrake = true;
	}
	public void disableArmBrake() {
		this.talonArm.setNeutralMode(NeutralMode.Coast);
		isArmBrake = false;
	}
}
