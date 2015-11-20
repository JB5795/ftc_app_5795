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
    DcMotor bright;
    DcMotor fleft;
    DcMotor bleft;
    Servo left;
    Servo right;
    Servo belt;
    int targetPosition=1120;

    final static int ENCODER_CPR = 1120;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE;                 //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed

    public void init() {

        fright = hardwareMap.dcMotor.get("fright");
        bright = hardwareMap.dcMotor.get("bright");
        fleft = hardwareMap.dcMotor.get("fleft");
        bleft = hardwareMap.dcMotor.get("bleft");
        left=hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");

        fright.setDirection(DcMotor.Direction.REVERSE);
        bright.setDirection(DcMotor.Direction.REVERSE);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

    }
        public void loop(){                 //While start button is pressed (until stopped)
            fright.setTargetPosition(targetPosition);

            if(fright.getCurrentPosition()<targetPosition) {
                bright.setPower(1);
                bleft.setPower(1);
                fright.setPower(1);
                fleft.setPower(1);
            }
            fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);




        }
}
