
package com.frcteam1939.powerup2018.robot.subsystems;

import com.frcteam1939.powerup2018.commands.climber.ClimberGamepadControl;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {


	@Override
	public void initDefaultCommand() {
		this.setDefaultCommand(new ClimberGamepadControl());
	}
}
