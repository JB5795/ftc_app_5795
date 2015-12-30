package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by hunai on 12/28/2015.
 */

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;


public class Autonomous_Encoders extends LinearOpMode{
    DcMotor fright;
    DcMotor fleft;

    final static int ENCODER_CPR = 1440;    //encoder counts per revolution
    final static double GEAR_RATIO = 2;     //gear ratio
    final static int WHEEL_DIAMETER = 4;    //diameter of wheel in inches
    static double DISTANCE;                    //distance to drive in inches

    final static double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; //calculates circumference
    final static double ROTATIONS = DISTANCE/CIRCUMFERENCE;       //calculates rotations needed
    final static double COUNTS = ENCODER_CPR*ROTATIONS*GEAR_RATIO;//calculates encoder counts needed

    final static int tileLength = 24; //length of floor tile is 24 inches
    final static double tileDiagonal = Math.sqrt(2*(24^2));

    public void drive(double DISTANCE, double power){ //enter distance and power (only drives straight)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);
        fleft.setTargetPosition((int) COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        fleft.setPower(power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

    }
    public void turnR(double DISTANCE, double power){ //enter distance and power (turns right)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) -COUNTS);
        fleft.setTargetPosition((int) COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(-power);
        fleft.setPower(power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public void turnL(double DISTANCE, double power){ //enter distance and power (turns left)
        this.DISTANCE = DISTANCE;
        fright.setTargetPosition((int) COUNTS);
        fleft.setTargetPosition((int) -COUNTS);

        fright.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        fleft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        fright.setPower(power);
        fleft.setPower(-power);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }
    public enum ColorSensorDevice {ADAFRUIT, HITECHNIC_NXT, MODERN_ROBOTICS_I2C};

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    ColorSensor colorSensor;
    DeviceInterfaceModule cdim;

    @Override
    public void runOpMode() throws InterruptedException {

        fright = hardwareMap.dcMotor.get("fright");
        fleft = hardwareMap.dcMotor.get("fleft");

        fright.setDirection(DcMotor.Direction.REVERSE);

        fright.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        fleft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        hardwareMap.logDevices();//establishing color sensor devices

        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        switch (device) {
            case MODERN_ROBOTICS_I2C:
                colorSensor = hardwareMap.colorSensor.get("mr");
                break;
        }
        waitForStart();
        //variables to determine color
        float hsvValues[] = {0,0,0};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);

        while(opModeIsActive()) {                 //While start button is pressed (until stopped)
            final int startDistance = 6;          //Distance from edge of start tile to first motor shaft
            drive(startDistance+tileLength, .5);  //Drives to edge of first tile and then to edge of second tile
            turnL(4.5*Math.PI, .5);                     //Turns 90 degrees in theory (calculations in notebook, titled Estimated Turning Distance)
            drive(tileDiagonal * 1.5, .5);           //Rough estimate of distance to repair zone
            turnL(4.5*Math.PI, .5);   //turns to beacon
            drive(5,.5);   //drives forward into repair zone
            sleep(1000);    //stops for 1 second
            //color stuff here
            switch (device) {
                case MODERN_ROBOTICS_I2C:
                    Color.RGBToHSV(colorSensor.red()*8, colorSensor.green()*8, colorSensor.blue()*8, hsvValues);
                    break;
            }
            telemetry.addData("Clear", colorSensor.alpha());
            telemetry.addData("Red  ", colorSensor.red());
            telemetry.addData("Green", colorSensor.green());
            telemetry.addData("Blue ", colorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });
            waitOneFullHardwareCycle();
        }


        //no color stuff here
        drive(-5,-.5); //backs away from beacon
        turnL(4.5,.5); //turns towards ramp
        drive(24,.5);  //drives to ramp
        turnR(2.25,.5);//turns to face ramp



    }

    private void enableLed(boolean value) {
        switch (device) {
            case MODERN_ROBOTICS_I2C:
                colorSensor.enableLed(value);
                break;
        }
    }
}
