package com.frcteam1939.powerup2018.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.frcteam1939.powerup2018.robot.RobotMap;
import com.frcteam1939.powerup2018.robot.commands.cubemanipulator.CubeManipulatorGamepadControl;

<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
>>>>>>> master
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeManipulator extends Subsystem {

	public static final double OUT_SPEED = 1.0;
	public static final double IN_SPEED = -1.0;

	private TalonSRX masterTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);
	private TalonSRX slaveTalon = new TalonSRX(RobotMap.cubeManipulatorTalon);

<<<<<<< HEAD
=======
	private DigitalInput banner = new DigitalInput(RobotMap.cubeManipulatorBanner);

	private Solenoid solenoidAngleTop = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleTop);
	private Solenoid solenoidAngleBottom = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorAngleBottom);
	private Solenoid solenoidWheels = new Solenoid(RobotMap.PCM, RobotMap.cubeManipulatorWheelsSolenoid);

>>>>>>> master
	public void CubeIntake() {

	}

	public CubeManipulator() {
		//	slaveTalon.setInverted(true);
		this.slaveTalon.follow(this.masterTalon);
	}

	@Override
	public void initDefaultCommand() {
<<<<<<< HEAD

	}

=======
		this.setDefaultCommand(new CubeManipulatorGamepadControl());
	}

	public void cubeManipulatorWheelsIn() {
		this.solenoidWheels.set(true);
	}

	public void cubeManipulatorWheelsOut() {
		this.solenoidWheels.set(false);
	}

	public void cubeManipulatorRaise() {
		this.solenoidAngleBottom.set(false);
		this.solenoidAngleTop.set(false);
	}

	public void cubeManipulatorLower() {
		this.solenoidAngleBottom.set(true);
		this.solenoidAngleTop.set(true);
	}

	public void cubeManipulatorMiddle() {
		this.solenoidAngleBottom.set(false);
		this.solenoidAngleTop.set(true);
	}

>>>>>>> master
	public void set(double value) {
		this.masterTalon.set(ControlMode.PercentOutput, value);
	}

	public boolean haveCube() {
		return !this.banner.get();
	}
}
