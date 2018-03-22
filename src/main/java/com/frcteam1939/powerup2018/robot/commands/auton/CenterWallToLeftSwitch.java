package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.CubeManipulatorLower;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.IntakeCube;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorMotorSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToLeftSwitch extends CommandGroup {

	public CenterWallToLeftSwitch() {
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(0.5));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new TurnToAngle(-26.25));
		this.addSequential(new SetElevatorMotorSpeed(1.0));
		this.addSequential(new Wait(1.75));
		this.addSequential(new SetElevatorMotorSpeed(0));
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(3.3));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new OutputCubeMiddle());
		this.addSequential(new SetDrivetrainMotorsSpeed(0.3));
		this.addSequential(new Wait(0.5));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new TurnToAngle(80));
		this.addSequential(new CubeManipulatorLower());
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(1));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new IntakeCube());
		this.addSequential(new SetDrivetrainMotorsSpeed(0.3));
		this.addSequential(new Wait(1));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new TurnToAngle(-80));
		this.addSequential(new SetElevatorMotorSpeed(1.0));
		this.addSequential(new Wait(1.25));
		this.addSequential(new SetElevatorMotorSpeed(0));
		this.addSequential(new SetDrivetrainMotorsSpeed(-0.3));
		this.addSequential(new Wait(0.5));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
		this.addSequential(new OutputCubeMiddle());
	}
}
