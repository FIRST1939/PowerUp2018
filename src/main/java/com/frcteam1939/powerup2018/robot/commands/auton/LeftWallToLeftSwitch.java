package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftWallToLeftSwitch extends CommandGroup {

	public LeftWallToLeftSwitch() {
		this.addSequential(new DriveDistance(DistanceConstants.SWITCH));
		// Turn
		this.addSequential(new DriveDistance(DistanceConstants.SWITCH_2));
	}
}
