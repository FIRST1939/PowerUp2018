package com.frcteam1939.powerup2018.robot.commands.elevator;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.CubeManipulatorMiddle;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevatorToScale extends CommandGroup {

	public ElevatorToScale() {
		this.addSequential(new SetElevatorMotorSpeed(1));
		this.addSequential(new Wait(2.1));
		this.addSequential(new SetElevatorMotorSpeed(0));
		this.addSequential(new CubeManipulatorMiddle());
	}
}
