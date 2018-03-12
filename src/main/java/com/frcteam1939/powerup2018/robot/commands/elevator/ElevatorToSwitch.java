package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevatorToSwitch extends CommandGroup {

	public ElevatorToSwitch() {
		this.addSequential(new SetElevatorMotorSpeed(1));
		this.addSequential(new Wait(1.3));
		this.addSequential(new SetElevatorMotorSpeed(0));
	}
}
