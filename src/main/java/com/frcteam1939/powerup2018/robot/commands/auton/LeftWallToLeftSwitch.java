package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorMotorSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftWallToLeftSwitch extends CommandGroup {

	public LeftWallToLeftSwitch() {
		this.addSequential(new DrivetrainMoveFor(-.3,5.0));
		this.addSequential(new TurnToAngle(90));
		this.addSequential(new ElevatorMoveFor(1.0,2.0));
		this.addSequential(new DrivetrainMoveFor(-.3,.05));
		this.addSequential(new OutputCubeMiddle());
	}
}
