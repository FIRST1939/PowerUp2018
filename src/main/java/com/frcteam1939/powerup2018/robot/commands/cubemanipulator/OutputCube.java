package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OutputCube extends CommandGroup {

	public OutputCube() {
		this.addSequential(new CubeManipulatorMiddle());
		this.addSequential(new SetCubeManipulatorSpeed(CubeManipulator.OUT_SPEED));
		if(Robot.cubeManipulator.haveCube()){Robot.cubeManipulator.set(0);}
		this.addSequential(new CubeManipulatorRaise());
	}
}
