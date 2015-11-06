package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 10/24/2015.
 */
public class PrototypeAutoV2 extends LinearOpMode {
    DcMotor fright;
    DcMotor bright;
    DcMotor fleft;
    DcMotor bleft;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static int DISTANCE;         //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;

    public void drive(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);
        bright.setTargetPosition((int) COUNTS);
        fleft.setTargetPosition((int) COUNTS);
        bleft.setTargetPosition((int) COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        bright.setPower(power);
        fleft.setPower(power);
        bleft.setPower(power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

    }
    public void turnR(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) -COUNTS);
        bright.setTargetPosition((int) -COUNTS);
        fleft.setTargetPosition((int) COUNTS);
        bleft.setTargetPosition((int) COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(-power);
        bright.setPower(-power);
        fleft.setPower(power);
        bleft.setPower(power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void turnL(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);
        bright.setTargetPosition((int) COUNTS);
        fleft.setTargetPosition((int) -COUNTS);
        bleft.setTargetPosition((int) -COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        bleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        bright.setPower(power);
        fleft.setPower(-power);
        bleft.setPower(-power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    @Override
    public void runOpMode() throws InterruptedException {
        fright = hardwareMap.dcMotor.get("fright");
        bright = hardwareMap.dcMotor.get("bright");
        fleft = hardwareMap.dcMotor.get("fleft");
        bleft = hardwareMap.dcMotor.get("bleft");

        fright.setDirection(DcMotor.Direction.REVERSE);
        bright.setDirection(DcMotor.Direction.REVERSE);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        bleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        waitForStart();

//        fright.setTargetPosition((int) COUNTS);
//        bright.setTargetPosition((int) COUNTS);
//        fleft.setTargetPosition((int) COUNTS);
//        bleft.setTargetPosition((int) COUNTS);
//
//        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        bright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//        bleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
//
//        fright.setPower(.5);
//        bright.setPower(.5);
//        fleft.setPower(.5);
//        bleft.setPower(.5);

        drive(24,.5);



    }
}
