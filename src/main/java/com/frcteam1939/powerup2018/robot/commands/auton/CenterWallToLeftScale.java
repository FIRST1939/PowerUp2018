package com.frcteam1939.powerup2018.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToLeftScale extends CommandGroup {

	public CenterWallToLeftScale() {
		this.addSequential(new CenterCrossAutoLine());
		// this.addSequential(new DriveDistance(10));
		// this.addSequential(new TurnToAngle(-50));
		// this.addSequential(new DriveDistance(150));
		// this.addSequential(new TurnToAngle(50));
		// this.addSequential(new DriveDistance(160));
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new SetElevatorHeight(DistanceConstants.ELEVATOR_SCALE));
		// this.addSequential(new DriveDistance(DistanceConstants.SCALE_2));
		// this.addSequential(new OutputCube());
		// this.addSequential(new DriveDistance(-10));
		// this.addSequential(new SetElevatorHeight(10));
	}
}
