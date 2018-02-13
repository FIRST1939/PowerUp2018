package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCube;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveDistance;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightWallToRightSwitch extends CommandGroup {

	public RightWallToRightSwitch() {
		this.addSequential(new DriveDistance(DistanceConstants.SWITCH));
		this.addSequential(new TurnToAngle(-90));
		this.addSequential(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		this.addSequential(new DriveDistance(DistanceConstants.SWITCH_2));
		this.addSequential(new OutputCube());
		this.addSequential(new DriveDistance(-10));
		this.addSequential(new SetElevatorHeight(10));
	}
}
