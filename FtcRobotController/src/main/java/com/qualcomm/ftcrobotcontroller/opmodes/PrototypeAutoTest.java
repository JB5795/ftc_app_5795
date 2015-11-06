package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 11/5/2015.
 */
public class PrototypeAutoTest extends OpMode {
    DcMotor fright;
    DcMotor bright;
    DcMotor fleft;
    DcMotor bleft;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE;                 //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed

    //final static int tileLength = 24; //length of floor tile is 24 inches
    //final static double tileDiagonal = Math.sqrt(2*(24^2));

    public void drive(double DISTANCE, double power){ //enter distance and power (only drives straight)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);

        fleft.setTargetPosition((int) COUNTS);


        fright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fleft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);


        fright.setPower(power);
        bright.setPower(power);
        fleft.setPower(power);
        bleft.setPower(power);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);


    }
    public void turnR(double DISTANCE, double power){ //enter distance and power (turns right)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) -COUNTS);

        fleft.setTargetPosition((int) COUNTS);


        fright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fleft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);


        fright.setPower(-power);
        bright.setPower(-power);
        fleft.setPower(power);
        bleft.setPower(power);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);

        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);

    }
    public void turnL(double DISTANCE, double power){ //enter distance and power (turns left)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);
        fleft.setTargetPosition((int) -COUNTS);

        fright.setMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setMode(DcMotorController.RunMode.RUN_TO_POSITION);


        fright.setPower(power);
        bright.setPower(power);
        fleft.setPower(-power);
        bleft.setPower(-power);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void init() {

        fright = hardwareMap.dcMotor.get("fright");
        fleft = hardwareMap.dcMotor.get("fleft");

//        fright.setDirection(DcMotor.Direction.REVERSE);
//        bright.setDirection(DcMotor.Direction.REVERSE);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
        public void loop(){                 //While start button is pressed (until stopped)
            final int startDistance = 6;          //Distance from edge of start tile to first motor shaft
            drive(startDistance, .5);  //Drives to edge of first tile and then to edge of second tile
            turnL(4.5 * Math.PI, .5);               //Turns 90 degrees in theory (calculations in notebook, titled Estimated Turning Distance)
            //drive(tileDiagonal * 1.5, .5);          //Rough estimate of distance to repair zone
            turnL(4.5 * Math.PI, .5);   //turns to beacon
            drive(5, .5);   //drives forward into repair zone

            drive(-5,-.5); //backs away from beacon
            turnL(4.5,.5); //turns towards ramp
            drive(24,.5);  //drives to ramp
            turnR(2.25,.5);//turns to face  ramp



        }
}
