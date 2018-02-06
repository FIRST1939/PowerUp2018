package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToLeftSwitch extends CommandGroup {

	public CenterWallToLeftSwitch() {
		this.addSequential(new OutputCube());
	}
}
