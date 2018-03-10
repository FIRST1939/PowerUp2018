
package com.frcteam1939.powerup2018.robot.subsystems;

import java.util.ArrayList;
import java.util.HashMap;

import com.frcteam1939.powerup2018.robot.Robot;
import com.frcteam1939.powerup2018.util.PixyException;
import com.frcteam1939.powerup2018.util.PixyPacket;
import com.frcteam1939.powerup2018.util.PixySPI;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * code from 3840
 */
public class Vision extends Subsystem {

	// These values are the default if you instantiate a PixySPI without arguments.
	// To create multiple PixySPI objects and thus use multiple Pixy cameras via SPI
	// Copy the items below, change variable names as needed and especially change
	// the SPI port used eg; Port.kOnboardCS[0-3] or Port.kMXP
	public PixySPI pixy1;
	private double kP = 0.002; //need to testx
	private double center = 160;
	Port port = Port.kOnboardCS0;
	String print;
	public HashMap<Integer, ArrayList<PixyPacket>> packets = new HashMap<Integer, ArrayList<PixyPacket>>();

	public Vision() {
		// Open a pipeline to a Pixy camera.
		this.pixy1 = new PixySPI(new SPI(this.port), this.packets, new PixyException(this.print));
	}

	@Override
	public void initDefaultCommand() {}

	public int getX() {
		int pack = -1;
		try {
			pack = this.pixy1.readPackets();
		} catch (PixyException e) {
			e.printStackTrace();
		}
		return this.packets.get(0).get(0).X;
	}

	public void center() {
		double error = this.center - this.getX();
		while (error <-2 && error >2) {
			double move = Robot.oi.left.getY();
			if (move <= 0.1) {
				move = 0;
			}

			Robot.drivetrain.drive(move, this.kP * error);
		}
	}

	public void testPixy1() {
		int ret = -1;
		// Get the packets from the pixy.
		try {
			ret = this.pixy1.readPackets();
		} catch (PixyException e) {
			e.printStackTrace();
		}

		SmartDashboard.putNumber("Pixy Vision: packets size: ", this.packets.size());

		for (int i = 1; i <= PixySPI.PIXY_SIG_COUNT; i++) {
			SmartDashboard.putString("Pixy Vision: Signature: ", Integer.toString(i));

			SmartDashboard.putNumber("Pixy Vision: packet: " + Integer.toString(i) + ": size: ", this.packets.get(i).size());

			// Loop through the packets for this signature.
			for (int j = 0; j < this.packets.get(i).size(); j++) {
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": X: ", this.packets.get(i).get(j).X);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Y: ", this.packets.get(i).get(j).Y);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Width: ", this.packets.get(i).get(j).Width);
				SmartDashboard.putNumber("Pixy Vision: " + Integer.toString(i) + ": Height: ", this.packets.get(i).get(j).Height);
			}
		}
	}
}