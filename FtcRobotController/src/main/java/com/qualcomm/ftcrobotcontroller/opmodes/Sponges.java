package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by jboerger on 10/2/2015.
 */
public class Sponges extends OpMode {

    DcMotor sponge;

    public void init (){
        sponge = hardwareMap.dcMotor.get("sponge");

    }

    public void loop () {
        sponge.setPower(gamepad1.left_stick_y);

        telemetry.addData("S Right: ", gamepad1.right_stick_y);
    }

    public void stop () {

    }
}
