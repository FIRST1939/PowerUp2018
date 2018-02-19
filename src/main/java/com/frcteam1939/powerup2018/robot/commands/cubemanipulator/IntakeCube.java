package com.frcteam1939.powerup2018.robot.commands.cubemanipulator;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeCube extends CommandGroup {

	public IntakeCube() {
		this.addSequential(new CubeManipulatorLower());
		this.addSequential(new CubeManipulatorWheelsOut());
		this.addSequential(new SetCubeManipulatorSpeed(CubeManipulator.IN_SPEED));
		this.addSequential(new Wait(1));
		if (Robot.cubeManipulator.haveCube()) {
			this.addSequential(new CubeManipulatorWheelsIn());
			this.addSequential(new SetCubeManipulatorSpeed(0));
			this.addSequential(new CubeManipulatorMiddle());
		}
	}
}
