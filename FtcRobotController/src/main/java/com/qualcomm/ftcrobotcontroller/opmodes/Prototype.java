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
    double BELTPosition;
    double leftChange=.1;
    double rightChange=.1;
    double BELTChange=.1;
    final static double LEFT_MIN_RANGE  = .5;
    final static double LEFT_MAX_RANGE  = 0.90;
    final static double RIGHT_MIN_RANGE  = 0.20;
    final static double RIGHT_MAX_RANGE  = 0.5;
    final static double BELT_MIN_RANGE  = 0.20;
    final static double BELT_MAX_RANGE  = 0.7;
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
        //BELTPosition=.2;
    }

    public void loop () {
            fright.setPower(-gamepad1.right_stick_y);
            bright.setPower(-gamepad1.right_stick_y);
            fleft.setPower(gamepad1.left_stick_y);
            bleft.setPower(gamepad1.left_stick_y);
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
        else{}
        if (gamepad1.left_bumper){
            BELTChange=.1;
            BELTPosition += BELTChange;
        }
        else if (gamepad1.right_bumper){
            BELTChange=.1;
            BELTPosition-=BELTChange;
        }
        else{
            BELTChange = 0;
        }
        leftPosition = Range.clip(leftPosition, LEFT_MIN_RANGE, LEFT_MAX_RANGE);
        rightPosition = Range.clip(rightPosition, RIGHT_MIN_RANGE, RIGHT_MAX_RANGE);
        BELTPosition = Range.clip(BELTPosition, BELT_MIN_RANGE, BELT_MAX_RANGE);

        left.setPosition(leftPosition);
        right.setPosition(rightPosition);
        belt.setPosition(BELTPosition);

        telemetry.addData("S Right: ", gamepad1.right_stick_y);
        telemetry.addData("S Left: ", gamepad1.left_stick_y);
    }

    public void stop () {

    }
}
