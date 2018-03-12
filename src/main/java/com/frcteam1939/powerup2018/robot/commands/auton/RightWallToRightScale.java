package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorMotorSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightWallToRightScale extends CommandGroup {

	public RightWallToRightScale() {
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.5));
		this.addSequential(new Wait(5.5));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new TurnToAngle(-90));
		this.addSequential(new SetDrivetrainMotorsSpeed(0.3));
		this.addSequential(new Wait(0.3));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new SetElevatorMotorSpeed(1));
		this.addSequential(new Wait(2.2));
		this.addSequential(new SetElevatorMotorSpeed(0));
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(.25));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new OutputCubeMiddle());
	}
}
