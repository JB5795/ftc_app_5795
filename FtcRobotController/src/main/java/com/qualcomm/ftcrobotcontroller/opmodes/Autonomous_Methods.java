package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 10/24/2015.
 */
public class Autonomous_Methods extends LinearOpMode {
    DcMotor R;
    DcMotor L;

    int ENCODER_CPR = 1120;    //encoder counts per revolution
    double GEAR_RATIO = 2;     //gear ratio
    double WHEEL_DIAMETER = 3.3;    //diameter of wheel in inches
    double DISTANCE;
    double startPos=0;

    public double Counts(double distance){
        //DISTANCE in inches
        this.DISTANCE=distance;
        double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        double ROTATIONS = distance/CIRCUMFERENCE;
        double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;
        return COUNTS;
    }
    public void driveStraight(double distance, double power){
        while(R.getCurrentPosition() < Counts(startPos)+Counts(distance)){
            R.setPower(power);
            L.setPower(power);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(distance));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
        }
        startPos=startPos+distance;
    }
    public void turnLeft(double distance, double power){
        while(R.getCurrentPosition() < Counts(startPos)+Counts(distance)) {
            R.setPower(power);
            L.setPower(-power);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(distance));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ", L.getCurrentPosition());
        }
        startPos=startPos+distance;
    }
    public void turnRight(double distance, double power){
        while(R.getCurrentPosition() < Counts(startPos)+Counts(distance)){
            R.setPower(-power);
            L.setPower(power);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(distance));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
        }
        startPos=startPos+distance;
    }



    @Override
    public void runOpMode() throws InterruptedException {
        R = hardwareMap.dcMotor.get("R");
        L = hardwareMap.dcMotor.get("L");


        L.setDirection(DcMotor.Direction.REVERSE);

        R.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        L.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();

        driveStraight(38, .5);
        waitOneFullHardwareCycle();
        R.setPower(0);
        L.setPower(0);

        driveStraight(-2, .5);
        waitOneFullHardwareCycle();
        R.setPower(0);
        L.setPower(0);

        turnLeft(-15.5, .5);
        waitOneFullHardwareCycle();
        R.setPower(0);
        L.setPower(0);

        driveStraight(18, .5);
        waitOneFullHardwareCycle();
        R.setPower(0);
        L.setPower(0);


    }
}
