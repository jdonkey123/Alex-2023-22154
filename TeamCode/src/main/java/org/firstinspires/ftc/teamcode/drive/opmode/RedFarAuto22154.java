package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class RedFarAuto22154 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return 0;

        // Red Far position, 1st step, any Spike position
        Trajectory trajRF1 = drive.trajectoryBuilder(new Pose2d(-72,36))
                .splineTo(new Vector2d(-50, 36), 0)
                .build();

        // Red Far position, Center Spike, Trajectories
        Trajectory trajRFC2 = drive.trajectoryBuilder(new Pose2d(-50,36))
                .splineToConstantHeading(new Vector2d(-30,33),0)
                .build();

        Trajectory trajRFC3 = drive.trajectoryBuilder(new Pose2d(-30,33),Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-38,60),Math.toRadians(45))
                .splineToSplineHeading(new Pose2d(-24, 60, Math.toRadians(270)),Math.toRadians(330))
                .splineToConstantHeading(new Vector2d(-12,-10),Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(-39,-53),Math.toRadians(270))
                .build();

        // red far left trajectories
        Trajectory trajRFL2 = drive.trajectoryBuilder(new Pose2d(-50,36))
                .splineToSplineHeading(new Pose2d(-51,36,Math.toRadians(45)),0)
                .build();

            // Drive to left spike position, so we can deliver purple pixel
        Trajectory trajRFL3 = drive.trajectoryBuilder(new Pose2d(-51,36,Math.toRadians(45)))
                .splineToLinearHeading(new Pose2d(-41,48,Math.toRadians(45)),Math.toRadians(45))
                .build();

            // Back away from left spike, drive toward center line, through stage door, then to backstage (left tag)
        Trajectory trajRFL4 = drive.trajectoryBuilder(new Pose2d(-41,48,Math.toRadians(45)))
                .splineToSplineHeading(new Pose2d(-51, 36, Math.toRadians(0)),Math.toRadians(45))
                .splineToConstantHeading(new Vector2d(-24,36),Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(-12, -10, Math.toRadians(270)),Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(-33,-52),Math.toRadians(270))
                .build();

        // ~~~~~~~~~~~~Red Far Right Trajectories ~~~~~~~~~~
        // Drive to right spike position to deliver purple pixel
        // RFR is not yet updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Trajectory trajRFR3 = drive.trajectoryBuilder(new Pose2d(-49,36,Math.toRadians(45)))
                .splineToLinearHeading(new Pose2d(-41,48,Math.toRadians(90)),Math.toRadians(90))
                .build();

        // RFR is not yet updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Back away from right spike, drive toward center line, through stage door, then to backstage (right tag)
        Trajectory trajRFR4 = drive.trajectoryBuilder(new Pose2d(-41,48,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(-49, 36, Math.toRadians(0)),Math.toRadians(45))
                .splineToConstantHeading(new Vector2d(-24,36),Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(-12, -10, Math.toRadians(270)),Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(-31,-55),Math.toRadians(270))
                .build();


        // ~~~~~~~~~~~~~~Trajectories done~~~~~~~~~~~~~~~~~


        // ~~~~~~~~~~Start of Autonomous execution~~~~~~~~~~~
        drive.followTrajectory(trajRF1);
        // read block position
    if (DetectSpike(1)){
            drive.followTrajectory(trajRFC2);
            drive.followTrajectory(trajRFC3);
        } else {
            drive.followTrajectory(trajRFL2);
            if (DetectSpike(2)){
                drive.followTrajectory(trajRFL3);
                drive.followTrajectory(trajRFL4);
            } else {
                drive.followTrajectory(trajRFR3);
                drive.followTrajectory(trajRFR4);
            }
        }

        //deliver purple pixel

        drive.followTrajectory(trajRFC3);
        //deliver yellow pixel

        drive.followTrajectory(trajRFL2);

        drive.followTrajectory(trajRFL3);
        return 0;
    }

    static boolean detectSpike = false;
    public static boolean DetectSpike(int spikePos) {
        if (spikePos==2){
            detectSpike = true;
        }
        return detectSpike;
    }
}
