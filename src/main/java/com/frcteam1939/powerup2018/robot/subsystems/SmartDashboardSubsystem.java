package com.frcteam1939.powerup2018.robot.subsystems;

import com.frcteam1939.powerup2018.robot.commands.smartdashboard.SmartDashboardUpdater;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SmartDashboardSubsystem extends Subsystem {

	@Override
	public void initDefaultCommand() {
		Command command = new SmartDashboardUpdater();
		command.setRunWhenDisabled(true);

		this.setDefaultCommand(command);
	}
}
