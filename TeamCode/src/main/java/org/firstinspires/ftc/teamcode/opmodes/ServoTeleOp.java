package org.firstinspires.ftc.teamcode.opmodes;

import android.widget.Switch;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "servoTeleOp", group = "TeleOp")

public class ServoTeleOp extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    Servo launcher, wrist, claw;
    double y, x, rx, powerFactor;
    String clawServoPos = "CLOSED";
    String wristServoPos = "NEUTRAL";
    String launcherServoPos = "ARMED";
    int prevGetCrossPressed, prevGetDpadUpPressed, prevGetDpadDownPressed;
    boolean updateCross, updateDpadUp, updateDpadDown;
    int getCrossPressed, getDpadUpPressed, getDpadDownPressed;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        launcher = hardwareMap.servo.get("launcher_servo");
        wrist = hardwareMap.servo.get("wrist_servo");
        claw = hardwareMap.servo.get("claw_servo");

        powerFactor = 0.625;
        getDpadDownPressed = 0;
        getDpadUpPressed = 0;
        getCrossPressed = 0;

        claw.setPosition(0);
        wrist.setPosition(0.5625);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("All motors and servos initialized");
        telemetry.update();

        telemetry.setAutoClear(false);

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            telemetry.clearAll();
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;


            prevGetCrossPressed = getCrossPressed;
            prevGetDpadDownPressed = getDpadDownPressed;
            prevGetDpadUpPressed = getDpadUpPressed;

            telemetry.addLine("y = " + y);
            telemetry.addLine("x = " + x);
            telemetry.addLine("rx = " + rx);
            telemetry.addLine();

            double denominator = Math.abs(y)+Math.abs(x)+Math.abs(rx);

            if (gamepad1.right_trigger >= 0.75) {
                powerFactor = 1;
            } else {
                powerFactor = 0.625;
            }

            double frontLeftPower = (y * powerFactor + x + rx * powerFactor)/denominator;
            double frontRightPower = (y * powerFactor - x - rx * powerFactor)/denominator;
            double backLeftPower = (y * powerFactor - x + rx * powerFactor)/denominator;
            double backRightPower = (y * powerFactor + x - rx * powerFactor)/denominator;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

            if (gamepad1.right_bumper) {
                launcherServoPos = "LAUNCHED";
            }
            switch (launcherServoPos) {
                case "ARMED":
                    launcher.setPosition(0);
                    break;
                case "LAUNCHED":
                    launcher.setPosition(0.25);
                    break;
            }

            telemetry.addLine("Launcher State = " + launcherServoPos);

            if (gamepad1.dpad_up) {
                getDpadUpPressed = 1;

                switch (prevGetDpadUpPressed - getDpadUpPressed) {
                    case -1:
                    case 1:
                        updateDpadUp = true;
                        break;
                    case 0:
                        updateDpadUp = false;
                        break;
                }

                if (updateDpadUp == true){
                    switch (wristServoPos) {
                        case "NEUTRAL":
                            wristServoPos = "UP";
                            break;
                        case "DOWN":
                            wristServoPos = "NEUTRAL";
                            break;
                    }
                }
            } else {
                getDpadUpPressed = 0;
            }

            if (gamepad1.dpad_down) {
                getDpadDownPressed = 1;

                switch (prevGetDpadDownPressed - getDpadDownPressed) {
                    case -1:
                    case 1:
                        updateDpadDown = true;
                        break;
                    case 0:
                        updateDpadDown = false;
                        break;
                }

                if (updateDpadDown == true) {
                    switch (wristServoPos) {
                        case "UP":
                            wristServoPos = "NEUTRAL";
                            break;
                        case "NEUTRAL":
                            wristServoPos = "DOWN";
                            break;
                    }
                }
            } else {
                getDpadDownPressed = 0;
            }

            telemetry.addLine("Wrist State = " + wristServoPos);

            switch (wristServoPos) {
                case "UP":
                    wrist.setPosition(0.65);
                    break;
                case "NEUTRAL":
                    wrist.setPosition(0.5625);
                    break;
                case "DOWN":
                    wrist.setPosition(0.533); //0.3125
                    break;
            }

            if (gamepad1.cross) {
               getCrossPressed = 1;

                switch (prevGetCrossPressed - getCrossPressed) {
                    case -1:
                    case 1:
                        updateCross = true;
                        break;
                    case 0:
                        updateCross = false;
                        break;
                }

                if (updateCross == true) {
                    if (clawServoPos == "OPEN") {
                        clawServoPos = "CLOSED";
                    } else if (clawServoPos == "CLOSED") {
                        clawServoPos = "OPEN";
                    }
                }
            } else {
                getCrossPressed = 0;
            }

            telemetry.addLine("Claw State = " + clawServoPos);

            switch (clawServoPos) {
                case "OPEN":
                    claw.setPosition(0.4);
                    break;
                case "CLOSED":
                    claw.setPosition(0);
                    break;
            }
            telemetry.update();
        }
    }
}
