package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jboerger on 10/2/2015.
 */
public class Prototype extends OpMode {

    DcMotor fright;
    DcMotor bright;
    DcMotor fleft;
    DcMotor bleft;
    Servo left;
    Servo right;
    Servo belt;

    double leftPosition;
    double rightPosition;
    double beltPosition;
    double leftChange=.1;
    double rightChange=.1;
    double beltChange=.1;
    final static double ARM_MIN_RANGE  = .5;
    final static double ARM_MAX_RANGE  = 0.90;
    final static double CLAW_MIN_RANGE  = 0.20;
    final static double CLAW_MAX_RANGE  = 0.5;
    final static double Belt_MIN_RANGE  = 0.20;
    final static double Belt_MAX_RANGE  = 0.7;
    public void init (){
        fright = hardwareMap.dcMotor.get("fright");
        bright = hardwareMap.dcMotor.get("bright");
        fleft = hardwareMap.dcMotor.get("fleft");
        bleft = hardwareMap.dcMotor.get("bleft");
        left= hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");
        leftPosition=.9;
        rightPosition=.1;
        beltPosition=.2;
    }

    public void loop () {
            fright.setPower(gamepad1.right_stick_y);
            bright.setPower(gamepad1.right_stick_y);
            fleft.setPower(-gamepad1.left_stick_y);
            bleft.setPower(-gamepad1.left_stick_y);
        if (gamepad1.dpad_left) {
            leftPosition += leftChange;
        }

        if (gamepad1.dpad_right) {
            leftPosition -= leftChange;
        }

        if (gamepad1.x) {
            rightPosition += rightChange;
        }

        if (gamepad1.b) {
            rightPosition -= rightChange;
        }
        if (gamepad1.left_bumper){
            beltPosition += beltChange;
        }
        if (gamepad1.right_bumper){
            beltPosition-=beltChange;
        }
        leftPosition = Range.clip(leftPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        rightPosition = Range.clip(rightPosition, CLAW_MIN_RANGE, CLAW_MAX_RANGE);
        beltPosition = Range.clip(beltPosition, Belt_MIN_RANGE, Belt_MAX_RANGE);

        left.setPosition(leftPosition);
        right.setPosition(rightPosition);
        belt.setPosition(beltPosition);

        telemetry.addData("S Right: ", gamepad1.right_stick_y);
        telemetry.addData("S Left: ", gamepad1.left_stick_y);
    }

    public void stop () {

    }
}
