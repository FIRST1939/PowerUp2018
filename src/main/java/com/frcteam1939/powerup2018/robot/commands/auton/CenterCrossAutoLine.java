package com.frcteam1939.powerup2018.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterCrossAutoLine extends CommandGroup {

	public CenterCrossAutoLine() {
		this.addSequential(new DoNothing());
		// this.addSequential(new DriveDistance(10));
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new DriveDistance(10));
		// this.addSequential(new TurnToAngle(-90));
		// this.addSequential(new DriveDistance(110));
	}
}
