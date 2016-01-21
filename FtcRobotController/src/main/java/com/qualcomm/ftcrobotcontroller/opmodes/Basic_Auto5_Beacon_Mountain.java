package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by jboerger on 10/24/2015.
 */
public class Basic_Auto5_Beacon_Mountain extends LinearOpMode {
    DcMotor R;
    DcMotor L;

    int ENCODER_CPR = 1120;    //encoder counts per revolution
    double GEAR_RATIO = 2;     //gear ratio
    double WHEEL_DIAMETER = 3.3;    //diameter of wheel in inches
    double DISTANCE;

    public double Counts(double distance){
        //DISTANCE in inches
        this.DISTANCE=distance;
        double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        double ROTATIONS = distance/CIRCUMFERENCE;
        double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;
        return COUNTS;
    }

    //13 inches should make right angle turn
    //Should make a method for driving

    @Override
    public void runOpMode() throws InterruptedException {
        R = hardwareMap.dcMotor.get("R");
        L = hardwareMap.dcMotor.get("L");

        L.setDirection(DcMotor.Direction.REVERSE);

        R.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        L.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        waitForStart();

        while(R.getCurrentPosition() < Counts(91)){
            R.setPower(.5);
            L.setPower(.5);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(50));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(R.getCurrentPosition() < Counts(10)+ Counts(71)){
            R.setPower(.5);
            L.setPower(-.5);
            telemetry.addData("Inches: ", DISTANCE + "Counts: " + Counts(60));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(R.getCurrentPosition() < Counts(20)+ Counts(81)){
            R.setPower(.5);
            L.setPower(.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(70));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);
//At Beacon
        while(R.getCurrentPosition() > Counts(101)-Counts(30)){
            R.setPower(-.5);
            L.setPower(-.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(40));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);
//Should it turn before reversing
        while(R.getCurrentPosition() < Counts(71)+Counts(13)){
            R.setPower(.5);
            L.setPower(-.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(63));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);

        while(R.getCurrentPosition() < Counts(84)+Counts(47)){
            R.setPower(.5);
            L.setPower(.5);
            telemetry.addData("Inches: ", DISTANCE +"Counts: "+Counts(120));
            telemetry.addData("Right Enc: ", R.getCurrentPosition());
            telemetry.addData("Left Enc: ",L.getCurrentPosition());
            waitOneFullHardwareCycle();
        }
        R.setPower(0);
        L.setPower(0);
    }
}
