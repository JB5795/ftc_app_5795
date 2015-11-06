package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jboerger on 10/2/2015.
 */
public class Prototype extends OpMode {

    DcMotor fright;
    DcMotor bright;
    DcMotor fleft;
    DcMotor bleft;
    public void init (){
        fright = hardwareMap.dcMotor.get("fright");
        bright = hardwareMap.dcMotor.get("bright");
        fleft = hardwareMap.dcMotor.get("fleft");
        bleft = hardwareMap.dcMotor.get("bleft");
    }

    public void loop () {
            fright.setPower(gamepad1.right_stick_y);
            bright.setPower(gamepad1.right_stick_y);
            fleft.setPower(-gamepad1.left_stick_y);
            bleft.setPower(-gamepad1.left_stick_y);
        telemetry.addData("S Right: ",gamepad1.right_stick_y);
        telemetry.addData("S Left: ",gamepad1.left_stick_y);
    }

    public void stop () {

    }
}
