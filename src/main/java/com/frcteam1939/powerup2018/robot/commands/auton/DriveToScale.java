package com.frcteam1939.powerup2018.robot.commands.auton;

import com.frcteam1939.powerup2018.robot.commands.drivetrain.SetDrivetrainMotorsSpeed;
import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveToScale extends CommandGroup {

	public DriveToScale() {
		this.addSequential(new DrivetrainMoveFor(-0.3,8.5));
	}
}
