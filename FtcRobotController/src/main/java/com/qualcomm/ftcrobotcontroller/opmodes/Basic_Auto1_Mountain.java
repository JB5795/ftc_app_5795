package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 10/24/2015.
 */
public class Basic_Auto1_Mountain extends LinearOpMode {
    DcMotor R;
    DcMotor L;

    int ENCODER_CPR = 1120;    //encoder counts per revolution
    double GEAR_RATIO = 2;     //gear ratio
    double WHEEL_DIAMETER = 3.3;    //diameter of wheel in inches
    double DISTANCE;

    public double Counts(double distance){
        //DISTANCE in inches
        this.DISTANCE=distance;
        double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        double ROTATIONS = distance/CIRCUMFERENCE;
        double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;
        return COUNTS;
    }



    @Override
    public void runOpMode() throws InterruptedException {
        R = hardwareMap.dcMotor.get("R");
        L = hardwareMap.dcMotor.get("L");


        L.setDirection(DcMotor.Direction.REVERSE);

        R.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        L.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();

        while(L.getCurrentPosition()<Counts(50)){
            R.setPower(.5);
            L.setPower(.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(38));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(L.getCurrentPosition()>Counts(38)-Counts(2)){
            R.setPower(-.5);
            L.setPower(-.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(45));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(L.getCurrentPosition()>Counts(32)-Counts(15.5)){
            R.setPower(.5);
            L.setPower(-.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(32));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(L.getCurrentPosition()<Counts(19)+Counts(18)){
            R.setPower(.5);
            L.setPower(.5);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(14));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

    }
}
