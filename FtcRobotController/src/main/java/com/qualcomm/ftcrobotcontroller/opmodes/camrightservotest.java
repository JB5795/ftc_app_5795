package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by hunai on 11/21/2015.
 */
public class camrightservotest extends OpMode{
    Servo camright;
    Servo left;
    Servo right;
    Servo belt;
    double camrightPosition;
    double leftPosition;
    double rightPosition;
    double beltPosition;
    double camrightChange=.1;
    double leftChange=.1;
    double rightChange=.1;
    double BELTChange=.1;
    final static double CAMRIGHT_MAX_RANGE = .9;
    final static double CAMRIGHT_MIN_RANGE = 0;
    final static double LEFT_MIN_RANGE  = .30;
    final static double LEFT_MAX_RANGE  = 0.95;
    final static double RIGHT_MIN_RANGE  = 0.22;
    final static double RIGHT_MAX_RANGE  = 0.85;
    final static double BELT_MIN_RANGE  = 0.20;
    final static double BELT_MAX_RANGE  = 0.7;
    public void init(){
        camright=hardwareMap.servo.get("camright");
        left= hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");
        camrightPosition=0;
        leftPosition=.9;
        rightPosition=.1;
        beltPosition=.5;
    }
    public void loop(){
        left.setPosition(leftPosition);
        right.setPosition(rightPosition);
        belt.setPosition(beltPosition);
        if(gamepad2.y){
            camrightPosition=camrightPosition+camrightChange;
        }
        else if(gamepad2.a){
            camrightPosition=camrightPosition-camrightChange;
        }
        camrightPosition = Range.clip(camrightPosition, CAMRIGHT_MIN_RANGE, CAMRIGHT_MAX_RANGE);
        camright.setPosition(camrightPosition);
    }
    public void stop(){

    }
}
