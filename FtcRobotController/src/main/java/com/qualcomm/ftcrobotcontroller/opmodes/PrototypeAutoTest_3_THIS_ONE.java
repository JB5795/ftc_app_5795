package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jboerger on 11/5/2015.
 */
public class PrototypeAutoTest_3_THIS_ONE extends OpMode {
    DcMotor fright;
    DcMotor fleft;
    Servo left;
    Servo right;
    Servo belt;
    Servo camright;
    Servo camleft;
    int STATE=0;
    int case1 = 0;
    int stateStarted = 0;
    int RmotorState;
    int LmotorState;

    final static int ENCODER_CPR = 1120;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE=52;                 //distance to drive in inches

    double R1=COUNTS;
    double R2=COUNTS;
    double R3=COUNTS;
    double L1=COUNTS;
    double L2=COUNTS;
    double L3=COUNTS;



    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed

    final static int finalCount=(int)Math.round(COUNTS);

    public void drive(int DISTANCE, double power){
        this.DISTANCE = DISTANCE;

        fright.setTargetPosition((int) COUNTS+RmotorState);
        fleft.setTargetPosition((int) COUNTS+LmotorState);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        fleft.setPower(power);

    }
    public void turnR(int DISTANCE, double power){

        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS+RmotorState);
        fleft.setTargetPosition((int) COUNTS+LmotorState);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(-power);
        fleft.setPower(power);

    }
    public void turnL(int DISTANCE, double power){

        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS+RmotorState);
        fleft.setTargetPosition((int) COUNTS+LmotorState);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        fleft.setPower(-power);
        if(stateStarted==0){

            stateStarted++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        fright = hardwareMap.dcMotor.get("fright");
        fleft = hardwareMap.dcMotor.get("fleft");
        left=hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");
        camright=hardwareMap.servo.get("camright");
        camleft=hardwareMap.servo.get("camleft");

        fright.setDirection(DcMotor.Direction.REVERSE);

        fright.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void loop(){                 //While start button is pressed (until stopped)
        telemetry.addData("Encdright: ", fright.getCurrentPosition()+","+"Encdleft: "+fleft.getCurrentPosition());
        telemetry.addData("State: ",STATE+","+"Case1?: "+case1);
        telemetry.addData("Rmotor start:", RmotorState+","+"Lmotor start:"+ LmotorState);
        telemetry.addData("State started: ",stateStarted);
        switch(STATE){
            case 0 : //Drive straight
                drive(20,.5);
                break;
            case 1 :
                turnL(10, .5);
                case1=1;
                break;
            case 2 :
                turnR(10, .5);
                break;
        }
        if(Math.abs(fright.getTargetPosition()-fright.getCurrentPosition())<10||Math.abs(fleft.getTargetPosition()-fleft.getCurrentPosition())<10){
            if(STATE<2)STATE++;
            RmotorState = fright.getCurrentPosition();
            LmotorState = fleft.getCurrentPosition();
        }
    }
}
