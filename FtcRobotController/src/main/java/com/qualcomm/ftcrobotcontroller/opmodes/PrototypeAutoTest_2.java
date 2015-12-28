package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jboerger on 11/5/2015.
 */
public class PrototypeAutoTest_2 extends OpMode {
    DcMotor fright;
    DcMotor fleft;
    Servo left;
    Servo right;
    Servo belt;
    Servo camright;
    Servo camleft;

    final static int ENCODER_CPR = 1120;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE=52;                 //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed
    final static int finalCount=(int)Math.round(COUNTS);
    public void init() {

        fright = hardwareMap.dcMotor.get("fright");
        fleft = hardwareMap.dcMotor.get("fleft");
        left=hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");
        camright=hardwareMap.servo.get("camright");
        camleft=hardwareMap.servo.get("camleft");

        fright.setDirection(DcMotor.Direction.REVERSE);
        fleft.setDirection(DcMotor.Direction.REVERSE);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
        public void loop(){                 //While start button is pressed (until stopped)
            telemetry.addData("Encdright:",fright.getCurrentPosition());
            telemetry.addData("Encdleft:",fleft.getCurrentPosition());
            fright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            fright.setTargetPosition(finalCount);
            fleft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
            fleft.setTargetPosition(finalCount);
        }
}
