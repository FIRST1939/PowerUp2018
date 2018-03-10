package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.DistanceConstants;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.CubeManipulatorMiddle;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.OutputCubeMiddle;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.SetCubeManipulatorSpeed;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.DriveDistance;
import com.frcteam1939.powerup2018.robot.commands.drivetrain.TurnToAngle;
import com.frcteam1939.powerup2018.robot.commands.elevator.SetElevatorHeight;
import com.frcteam1939.powerup2018.robot.subsystems.CubeManipulator;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterWallToRightSwitch extends CommandGroup {

	public CenterWallToRightSwitch() {
		this.addSequential(new CubeManipulatorMiddle());
		this.addSequential(new SetCubeManipulatorSpeed(CubeManipulator.IN_SPEED));
		this.addSequential(new Wait(1));
		this.addSequential(new SetCubeManipulatorSpeed(0));
		this.addSequential(new DriveDistance(10));
		this.addSequential(new TurnToAngle(24));
		this.addSequential(new DriveDistance(132));
		this.addSequential(new TurnToAngle(-24));
		this.addSequential(new DriveDistance(10));
		this.addSequential(new SetElevatorHeight(DistanceConstants.ELEVATOR_SWITCH));
		this.addSequential(new OutputCubeMiddle());
	}
}
