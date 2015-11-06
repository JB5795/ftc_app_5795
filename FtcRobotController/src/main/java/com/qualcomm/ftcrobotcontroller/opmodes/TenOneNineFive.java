package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jboerger on 10/2/2015.
 */
public class TenOneNineFive extends OpMode {

    DcMotor motor_1;
    DcMotor motor_2;
    double sec = System.nanoTime();
    public void init (){
        motor_1 = hardwareMap.dcMotor.get("motor_1");
        motor_2 = hardwareMap.dcMotor.get("motor_2");
    }

    public void loop () {
        motor_1.setPower(gamepad1.right_stick_y);
        motor_2.setPower(-gamepad1.left_stick_y);
        telemetry.addData("right: ",gamepad1.right_stick_y);
        telemetry.addData("left: ",gamepad1.left_stick_y);
    }

    public void stop () {

    }
}