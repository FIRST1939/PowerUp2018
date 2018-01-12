package com.frcteam1939.powerup2018.robot.commands.smartdashboard;

import com.frcteam1939.powerup2018.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater extends Command {

	public SmartDashboardUpdater() {
		this.requires(Robot.smartDashboard);
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		SmartDashboard.putBoolean("Left", false);
		SmartDashboard.putBoolean("Center", false);
		SmartDashboard.putBoolean("Right", false);

		SmartDashboard.putBoolean("1: Switch", false);
		SmartDashboard.putBoolean("1: Scale", false);
		SmartDashboard.putBoolean("1: Cross Auto Line", false);
		SmartDashboard.putBoolean("1: Do Nothing", false);

		SmartDashboard.putBoolean("2: Switch", false);
		SmartDashboard.putBoolean("2: Scale", false);
		SmartDashboard.putBoolean("2: Cross Auto Line", false);
		SmartDashboard.putBoolean("2: Do Nothing", false);
		SmartDashboard.putBoolean("2: Still Do Switch", false);
		SmartDashboard.putBoolean("2: Still Do Scale", false);

		SmartDashboard.putBoolean("3: Cross Auto Line", false);
		SmartDashboard.putBoolean("3: Do Nothing", false);
		SmartDashboard.putBoolean("3: Still Do Switch", false);
		SmartDashboard.putBoolean("3: Still Do Scale", false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}
}
