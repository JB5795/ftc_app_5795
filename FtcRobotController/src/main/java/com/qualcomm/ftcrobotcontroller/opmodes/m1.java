package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by hunai on 12/28/2015.
 */
public class m1 extends OpMode{
    DcMotor fright;
    int targetPosition=1120;

    final static int ENCODER_CPR = 1120;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE;                 //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed

    public void init() {
        fright = hardwareMap.dcMotor.get("m1");
        fright.setDirection(DcMotor.Direction.REVERSE);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    public void loop(){                 //While start button is pressed (until stopped)
        telemetry.addData("Encdright:",fright.getCurrentPosition());
        if(fright.getCurrentPosition()<targetPosition) {
            fright.setPower(1);

        }
        fright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fright.setTargetPosition(targetPosition);





    }
}
