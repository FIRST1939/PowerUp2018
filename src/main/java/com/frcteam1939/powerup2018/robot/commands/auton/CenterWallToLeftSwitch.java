package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCube;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveDistance;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToLeftSwitch extends CommandGroup {

	public CenterWallToLeftSwitch() {
		this.addSequential(new DriveDistance(10));
		this.addSequential(new TurnToAngle(-24));
		this.addSequential(new DriveDistance(132));
		this.addSequential(new TurnToAngle(24));
		this.addSequential(new DriveDistance(10));
		this.addSequential(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		this.addSequential(new OutputCube());
	}
}
