package com.frcteam1939.powerup2018.robot.commands.drivetrain;

import com.frcteam1939.powerup2018.util.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveFor extends CommandGroup {

	public DriveFor(double voltage, double time) {
		this.addSequential(new SetDrivetrainMotorsSpeed(voltage));
		this.addSequential(new Wait(time));
		this.addSequential(new SetDrivetrainMotorsSpeed(0));
	}
}
