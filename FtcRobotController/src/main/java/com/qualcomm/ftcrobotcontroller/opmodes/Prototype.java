package com.qualcomm.ftcrobotcontroller.opmodes;

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by jboerger on 10/2/2015.
 */
public class Prototype extends OpMode {

    DcMotor fright;
//    DcMotor bright;
    DcMotor fleft;
//    DcMotor bleft;


    DcMotor sponge;
    Servo left;
    Servo right;
    Servo belt;
    Servo camleft;
    Servo camright;

    double leftPosition;
    double rightPosition;
    double beltPosition;
    double camleftPosition;
    double camrightPosition;
    double leftChange=.1;
    double rightChange=.1;
    double camleftChange=.1;
    double camrightChange=.1;
    double BELTChange=.1;
    final static double LEFT_MIN_RANGE  = .30;
    final static double LEFT_MAX_RANGE  = 0.85;
    final static double RIGHT_MIN_RANGE  = 0.22;
    final static double RIGHT_MAX_RANGE  = 0.85;
    final static double BELT_MIN_RANGE  = 0.20;
    final static double BELT_MAX_RANGE  = 0.7;
    final static double CAMLEFT_MAX_RANGE = .5;
    final static double CAMLEFT_MIN_RANGE = 0;
    final static double CAMRIGHT_MAX_RANGE = .5;
    final static double CAMRIGHT_MIN_RANGE = 0;

    public void init (){
        fright = hardwareMap.dcMotor.get("fright");
//        bright = hardwareMap.dcMotor.get("bright");
        fleft = hardwareMap.dcMotor.get("fleft");

//        bleft = hardwareMap.dcMotor.get("bleft");


        sponge=hardwareMap.dcMotor.get("sponge");
        left= hardwareMap.servo.get("left");
        right=hardwareMap.servo.get("right");
        belt=hardwareMap.servo.get("belt");
        camleft=hardwareMap.servo.get("camleft");
        camright=hardwareMap.servo.get("camright");
        leftPosition=.9;
        rightPosition=.1;
        beltPosition=.5;
        camleftPosition= .5;
        camrightPosition=.5;
        camleftPosition= 0;
        //camrightPosition=.1
        //camrightPosition=.9;
    }


    public void loop () {
        fright.setPower(-gamepad1.right_stick_y);
//        bright.setPower(-gamepad1.right_stick_y);
        fleft.setPower(gamepad1.left_stick_y);

//        bleft.setPower(gamepad1.left_stick_y);
         sponge.setPower(gamepad2.left_stick_y);


        sponge.setPower(gamepad2.left_stick_y);

        if (gamepad2.dpad_left) {
            leftPosition += leftChange;
        }

        if (gamepad2.dpad_right) {
            leftPosition -= leftChange;
        }

        if (gamepad2.x) {
            rightPosition += rightChange;
        }

        if (gamepad2.b) {
            rightPosition -= rightChange;
        }
        else{}
        if (gamepad2.left_bumper){
            BELTChange=.1;
            beltPosition += BELTChange;
        }
        else if (gamepad2.right_bumper){
            BELTChange=.1;
            beltPosition-=BELTChange;
        }
        else{
            BELTChange = 0;
            beltPosition=.5;
        }
        if (gamepad2.y){
            camleftPosition= camleftPosition - .9;
            camrightPosition=camrightPosition + .9;
        }
        if (gamepad2.a){
            camleftPosition = camleftPosition + camleftChange;
            camrightPosition= camrightPosition -camrightChange;
        }
        leftPosition = Range.clip(leftPosition, LEFT_MIN_RANGE, LEFT_MAX_RANGE);
        rightPosition = Range.clip(rightPosition, RIGHT_MIN_RANGE, RIGHT_MAX_RANGE);
        beltPosition = Range.clip(beltPosition, BELT_MIN_RANGE, BELT_MAX_RANGE);
        camleftPosition = Range.clip (camleftPosition, CAMLEFT_MIN_RANGE, CAMLEFT_MAX_RANGE);
        camrightPosition = Range.clip (camrightPosition, CAMRIGHT_MIN_RANGE, CAMRIGHT_MAX_RANGE);

        left.setPosition(leftPosition);
        right.setPosition(rightPosition);
        belt.setPosition(beltPosition);
        camleft.setPosition (camleftPosition);
        camright.setPosition(camrightPosition);

        telemetry.addData("S Right: ", gamepad1.right_stick_y);
        telemetry.addData("S Left: ", gamepad1.left_stick_y);
    }

    public void stop () {

    }
}

