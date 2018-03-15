package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorMotorSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivetrainMoveFor extends CommandGroup {
	public DrivetrainMoveFor(double speed, double wait){
		this.addSequential(new SetDrivetrainMotorsSpeed(speed));
		this.addSequential(new Wait(wait));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));	
	}
}