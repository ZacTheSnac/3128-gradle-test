package org.team3128.gradletest.main;

import java.util.List;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.esotericsoftware.minlog.Log;

import org.team3128.common.NarwhalRobot;
import org.team3128.common.drive.SRXTankDrive;
import org.team3128.common.drive.pathfinder.Pathfinder;
import org.team3128.common.drive.pathfinder.ProfilePoint;
import org.team3128.common.drive.pathfinder.Trajectory;
import org.team3128.common.drive.pathfinder.Waypoint;
import org.team3128.common.util.units.Length;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MainGradleTest extends NarwhalRobot {
	List<ProfilePoint> points;

	public TalonSRX leftDriveMotor, rightDriveMotor;

    @Override
	protected void constructHardware() {
		leftDriveMotor = new TalonSRX(10);
		rightDriveMotor = new TalonSRX(11);
		SRXTankDrive.initialize(leftDriveMotor, rightDriveMotor, 12.6 * Length.in, 1.0, 25 * Length.in, 30 * Length.in, 3800);

		Trajectory t = Pathfinder.generate(50.0, 
			new Waypoint(0, 0, 90),
			new Waypoint(6 * Length.ft, 6 * Length.ft, 0)
		);

		System.out.println(t);

		long startTime = System.currentTimeMillis();
		points = Pathfinder.getProfilePoints(t);
		Log.info("Elapsed Time: " + (System.currentTimeMillis() - startTime) + " ms");

		for (ProfilePoint p : points) {
			System.out.println(p.leftPoint.position + "," + p.rightPoint.position);
		}
	}
	
	@Override
	protected void setupListeners() {

	}
    
    @Override
	protected void constructAutoPrograms(SendableChooser<CommandGroup> programChooser) {

	}
	
	@Override
	protected void teleopInit() {

    }
	
	@Override
	protected void teleopPeriodic() {

    }
	
	@Override
	protected void autonomousInit() {

	}

	@Override
	protected void autonomousPeriodic() {

    }
	
	@Override
	protected void disabledInit() {

	}
	
	@Override
	protected void disabledPeriodic() {

    }
	
	@Override
	protected void testInit() {

    }
	
	@Override
	protected void testPeriodic() {

    }
	
	@Override
	protected void updateDashboard() {

    }
}