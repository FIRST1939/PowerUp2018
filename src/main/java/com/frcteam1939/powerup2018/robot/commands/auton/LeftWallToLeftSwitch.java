package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.ElevatorToSwitch;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftWallToLeftSwitch extends CommandGroup {

	public LeftWallToLeftSwitch() {
		// this.addParallel(new DriveFor(-0.5, 3.0));
		// this.addSequential(new ElevatorToSwitch());
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new DriveFor(-0.3, 0.5));
		// this.addSequential(new OutputCubeMiddle());
		// this.addSequential(new DriveFor(0.3, 0.5));
		// this.addSequential(new TurnToAngle(-90));
		// this.addParallel(new DriveFor(-0.3, 1.0));
		// this.addSequential(new ElevatorToZero());
		// this.addSequential(new TurnToAngle(90));
		// this.addParallel(new DriveFor(-0.3, 1));
		// this.addSequential(new IntakeCube());
		// this.addSequential(new ElevatorToSwitch());
		// this.addSequential(new TurnToAngle(90));
		// this.addSequential(new OutputCubeMiddle());

		// OLD
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(5.0));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new TurnToAngle(90));
		this.addSequential(new ElevatorToSwitch());
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(0.5));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new OutputCubeMiddle());
	}
}
