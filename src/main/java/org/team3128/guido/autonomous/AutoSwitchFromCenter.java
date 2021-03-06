package org.team3128.guido.autonomous;

import org.team3128.guido.autonomous.util.PowerUpAutoValues;
import org.team3128.common.autonomous.primitives.CmdDelay;
import org.team3128.common.autonomous.primitives.CmdRunInParallel;
import org.team3128.common.util.enums.Direction;
import org.team3128.common.util.units.Length;

import org.team3128.guido.mechanisms.Forklift.ForkliftState;
import org.team3128.guido.mechanisms.Intake.IntakeState;

import org.team3128.guido.util.PlateAllocation;

public class AutoSwitchFromCenter extends AutoGuidoBase {
	protected double turn_radius;
	
	public AutoSwitchFromCenter(double delay) {
		super(delay);
		
		// if (PlateAllocation.getScale().equals(Direction.RIGHT)) {
		// 	addSequential(new CmdDelay(5));
		// }
		
		final double robot_center_offset = PowerUpAutoValues.ROBOT_WIDTH / 2 - PowerUpAutoValues.CENTER_OFFSET;

		double horizontal_distance;
		
		if (PlateAllocation.getNearSwitch() == Direction.RIGHT) {
			horizontal_distance = PowerUpAutoValues.SWITCH_PLATE_CENTER - robot_center_offset;
		} else {
			horizontal_distance = PowerUpAutoValues.SWITCH_PLATE_CENTER + robot_center_offset;
		}

		turn_radius = horizontal_distance / 2;
		final double vertical_travel = PowerUpAutoValues.SWITCH_FRONT_DISTANCE - PowerUpAutoValues.ROBOT_LENGTH
				- horizontal_distance - PowerUpAutoValues.CUBE_EXTENSION;
		
		
		addSequential(drive.new CmdFancyArcTurn(turn_radius, 85, 5000, PlateAllocation.getNearSwitch(), 1.0, true));
		
		float second_turn_angle;
		if (PlateAllocation.getNearSwitch().opposite() == Direction.RIGHT) {
			//second_turn_angle = 85;
			second_turn_angle = 85;
		}
		else {
			second_turn_angle = 90;
		}
		addSequential(drive.new CmdFancyArcTurn(turn_radius, second_turn_angle, 5000, PlateAllocation.getNearSwitch().opposite(), 1.0, true));


		addSequential(new CmdRunInParallel(
				drive.new CmdMoveForward(vertical_travel, 5000, 1.0),
				forklift.new CmdSetForkliftPosition(ForkliftState.SWITCH)
		));
		
		addSequential(drive.new CmdMoveForward(2 * Length.ft, 500, 1.0));
		addSequential(forklift.new CmdRunIntake(IntakeState.OUTTAKE, 500));
	}
}
