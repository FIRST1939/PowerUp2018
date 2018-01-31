package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToRightScale extends CommandGroup {

	public CenterWallToRightScale() {
		this.addSequential(new OutputCube());
	}
}
