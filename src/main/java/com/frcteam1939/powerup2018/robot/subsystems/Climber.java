
package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.commands.climber.ClimberGamepadControl;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author jcase
 *
 */
public class Climber extends Subsystem {
	CANTalon talon = new CANTalon(RobotMap.climberTalon);

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
	}
	
	public void rollIn() {
		this.talon.set(ControlMode.PercentOutput, 0.5);
	}
	public void rollOut() {
		this.talon.set(ControlMode.PercentOutput, -0.5);
	}
	
	public void brake() {
		this.talon.setNeutralMode(NeutralMode.Brake);		
	}
	public void disableBrake() {
		this.talon.setNeutralMode(NeutralMode.Coast);
	}
}
