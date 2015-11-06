package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jboerger on 10/2/2015.
 */
public class Jiraffe extends OpMode {

    DcMotor right;
    DcMotor left;
    DcMotor lift;
    DcMotor sweep;

    public void init (){
        right = hardwareMap.dcMotor.get("right");
        left = hardwareMap.dcMotor.get("left");
        lift = hardwareMap.dcMotor.get("lift");
        sweep = hardwareMap.dcMotor.get("sweep");
    }

    public void loop () {
        right.setPower(gamepad1.right_stick_y);
        left.setPower(-gamepad1.left_stick_y);
        if(gamepad1.a=true){
            sweep.setPower(1);
        }
        else if(gamepad1.a=false){
            sweep.setPower(0);
        }
        telemetry.addData("right: ",gamepad1.right_stick_y);
        telemetry.addData("left: ",gamepad1.left_stick_y);
    }

    public void stop () {

    }
}
