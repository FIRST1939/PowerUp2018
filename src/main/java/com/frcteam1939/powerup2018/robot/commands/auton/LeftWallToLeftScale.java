package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveFor;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.ElevatorToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftWallToLeftScale extends CommandGroup {

	public LeftWallToLeftScale() {
		this.addSequential(new DriveFor(-0.5, 4.3));
		this.addSequential(new TurnToAngle(40));
		this.addSequential(new ElevatorToScale());
		this.addSequential(new DriveFor(-0.3, 0.4));
		this.addSequential(new OutputCubeMiddle());
		// this.addSequential(new DriveFor(0.3, 0.25));
		// this.addSequential(new TurnToAngle(90));
		// this.addParallel(new DriveFor(-0.5, 1.5));
		// this.addSequential(new ElevatorToZero());
		// this.addSequential(new IntakeCube());
	}
}
