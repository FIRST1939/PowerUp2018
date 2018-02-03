package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCube extends CommandGroup {

	public IntakeCube() {
		this.addSequential(new CubeManipulatorLower());
		this.addSequential(new SetCubeManipulatorSpeed(CubeManipulator.IN_SPEED));
		if(!Robot.cubeManipulator.haveCube()){Robot.cubeManipulator.set(0);}
		this.addSequential(new CubeManipulatorWheelsIn());
		this.addSequential(new CubeManipulatorRaise());
	}
}
