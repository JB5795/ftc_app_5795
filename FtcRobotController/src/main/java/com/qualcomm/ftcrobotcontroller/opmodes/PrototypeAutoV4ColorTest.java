package com.qualcomm.ftcrobotcontroller.opmodes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;

//imports added for color sensor


/**
 * Created by jboerger on 10/24/2015.
 */
public class PrototypeAutoV4ColorTest extends LinearOpMode {


    public enum ColorSensorDevice {ADAFRUIT, HITECHNIC_NXT, MODERN_ROBOTICS_I2C};

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    ColorSensor colorSensor;
    DeviceInterfaceModule cdim;

    @Override
    public void runOpMode() throws InterruptedException {

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

        while(opModeIsActive()) {
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


        }

    private void enableLed(boolean value) {
        switch (device) {
            case MODERN_ROBOTICS_I2C:
                colorSensor.enableLed(value);
                break;
        }
    }
}
