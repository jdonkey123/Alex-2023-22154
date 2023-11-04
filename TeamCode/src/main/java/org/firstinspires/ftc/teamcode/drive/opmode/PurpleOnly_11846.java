package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.armsubsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.sensors.SensorSubsystem;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class PurpleOnly_11846 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmSubsystem armSubsystem = new ArmSubsystem(hardwareMap);
        SensorSubsystem sensorSubsystem = new SensorSubsystem(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        // 1st step, any Spike position
        Trajectory trajPOA1 = drive.trajectoryBuilder(new Pose2d(-63,36))
                .splineTo(new Vector2d(-42, 36), 0)
                .build();

        // Red Far position, Center Spike, Trajectories
        Trajectory trajPOC2 = drive.trajectoryBuilder(new Pose2d(-42,36))
                .splineToConstantHeading(new Vector2d(-33,33),0)
                .build();

        // Turn 45 to look for Left Spike
        Trajectory trajPOL2 = drive.trajectoryBuilder(new Pose2d(-42,36))
                .splineToSplineHeading(new Pose2d(-41,36,Math.toRadians(45)),0)
                .build();

            // Drive to left spike position, so we can deliver purple pixel
        Trajectory trajPOL3 = drive.trajectoryBuilder(new Pose2d(-41,36,Math.toRadians(45)),Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(-38,48,Math.toRadians(0)),Math.toRadians(0))
                .build();

        // Drive to right spike position to deliver purple pixel
        Trajectory trajPOR3 = drive.trajectoryBuilder(new Pose2d(-41,36,Math.toRadians(45)),Math.toRadians(225))
                .splineToSplineHeading(new Pose2d(-41,35,Math.toRadians(315)),Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(-40,28,Math.toRadians(315)),Math.toRadians(315))
                .build();

        // ~~~~~~~~~~~~~~Trajectories done~~~~~~~~~~~~~~~~~


        // ~~~~~~~~~~Start of Autonomous execution~~~~~~~~~~~
        drive.followTrajectory(trajPOA1);
        if (sensorSubsystem.getFrontColor()==1){
            drive.followTrajectory(trajPOC2);
            //deliver purple pixel
        } else {
            drive.followTrajectory(trajPOL2);
            if (sensorSubsystem.getFrontColor()==1){
                drive.followTrajectory(trajPOL3);
                //deliver purple pixel
            } else {
                drive.followTrajectory(trajPOR3);
                //deliver purple pixel
            }
        }
        //deliver purple pixel

    }

}
