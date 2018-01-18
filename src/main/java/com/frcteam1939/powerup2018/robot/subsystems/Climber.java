
package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.commands.climber.ClimberGamepadControl;
import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author jcase
 *
 */
public class Climber extends Subsystem {
	TalonSRX talon = new TalonSRX(RobotMap.climberTalon);

	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
	}
	public void rollIn() {
		
	}
	public void brake() {
		
	}
	public void disableBrake() {
		this.talon.
	}
}
