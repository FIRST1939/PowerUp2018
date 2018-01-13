/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.frcteam1939.powerup2018.robot.subsystems;


import com.frcteam1939.powerup2018.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */

public class Pixy extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	AnalogInput pixyXIn = new AnalogInput(RobotMap.PIXY_X);
	AnalogInput pixyYIn = new AnalogInput(RobotMap.PIXY_Y);

	@Override
	public void initDefaultCommand() {
		//this.setDefaultCommand();
	}
	public int getXPosition(){
    	return pixyXIn.getAverageValue();
    }
    public int getYPosition(){
    	return pixyYIn.getAverageValue();
    }
}
