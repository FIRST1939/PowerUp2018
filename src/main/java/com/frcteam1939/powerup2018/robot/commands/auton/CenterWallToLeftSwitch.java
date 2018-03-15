package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorMotorSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToLeftSwitch extends CommandGroup {

	public CenterWallToLeftSwitch() {
		
		this.addSequential(new DrivetrainMoveFor(-0.3,0.5));
		this.addSequential(new TurnToAngle(-26.25));
		this.addSequential(new ElevatorMoveFor(1.0,1.75));
		this.addSequential(new DrivetrainMoveFor(-0.3,3.3));
		this.addSequential(new OutputCubeMiddle());

	}
}
