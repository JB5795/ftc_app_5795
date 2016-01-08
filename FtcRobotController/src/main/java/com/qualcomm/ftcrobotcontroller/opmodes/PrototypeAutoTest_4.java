package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 10/24/2015.
 */
public class PrototypeAutoTest_4 extends LinearOpMode {
    DcMotor fright;
    DcMotor fleft;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static int DISTANCE;         //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;
    double finalCount;

    public void drive(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        while(fright.getCurrentPosition()<COUNTS||fleft.getCurrentPosition()<COUNTS){
            fright.setPower(power);
            fleft.setPower(power);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);
    }
    public void turnR(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        while(fright.getCurrentPosition()<-COUNTS){
            fright.setPower(-power);
            fleft.setPower(power);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);
    }
    public void turnL(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;
        while(fleft.getCurrentPosition()<-COUNTS){
            fright.setPower(power);
            fleft.setPower(-power);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        fright = hardwareMap.dcMotor.get("fright");
        fleft = hardwareMap.dcMotor.get("fleft");

        fright.setDirection(DcMotor.Direction.REVERSE);

        fright.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        fleft.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();

        //Gradle Gradle Gradle
        //Where did all the allen wrenches go

//        drive(20,.5);
//        drive(5,.5);
//        turnL(10,.5);
//        drive(15,.5);
        DISTANCE=20;
        while(fright.getCurrentPosition()<COUNTS||fleft.getCurrentPosition()<COUNTS){
            fright.setPower(.5);
            fleft.setPower(.5);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);
        finalCount=COUNTS;

        DISTANCE=10;
        finalCount=finalCount+COUNTS;
        while(fright.getCurrentPosition()<finalCount){//TURN Right
            fright.setPower(-.5);
            fleft.setPower(.5);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);

        DISTANCE=5;
        finalCount=finalCount+COUNTS;
        while(fright.getCurrentPosition()<finalCount||fleft.getCurrentPosition()<finalCount){
            fright.setPower(.5);
            fleft.setPower(.5);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);

        DISTANCE=10;
        finalCount=finalCount+COUNTS;
        while(fleft.getCurrentPosition()<-COUNTS){
            fright.setPower(.5);
            fleft.setPower(-.5);
            telemetry.addData("Right Enc: ", fright.getCurrentPosition());
            telemetry.addData("Left Enc: ",fleft.getCurrentPosition());
        }
        fright.setPower(0);
        fleft.setPower(0);
    }
}
