package com.frcteam1939.powerup2018.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightWallToLeftScale extends CommandGroup {

	public RightWallToLeftScale() {
		this.addSequential(new CrossAutoLine());
		// this.addSequential(new DriveDistance(DistanceConstants.WALL_TO_MIDDLE_OF_SWITCH_AND_SCALE));
		// this.addSequential(new TurnToAngle(-90));
		// this.addSequential(new DriveDistance(DistanceConstants.CROSS_FIELD));
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new DriveDistance(DistanceConstants.MIDDLE_OF_SWITCH_AND_SCALE_TO_SCALE));
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new SetElevatorHeight(DistanceConstants.ELEVATOR_SCALE));
		// this.addSequential(new DriveDistance(DistanceConstants.SCALE_2));
		// this.addSequential(new OutputCube());
		// this.addSequential(new DriveDistance(-10));
		// this.addSequential(new SetElevatorHeight(10));
	}
}
