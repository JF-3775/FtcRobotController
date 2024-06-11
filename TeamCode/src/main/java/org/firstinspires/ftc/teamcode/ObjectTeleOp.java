package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "ObjectTeleOp", group = "TeleOp")

public class ObjectTeleOp extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    Servo launcher, wrist, claw;
    double y, x, rx;
    String clawServoPos = "CLOSED";
    int prevGetCrossPressed;
    boolean updateCross;
    int getCrossPressed = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        launcher = hardwareMap.servo.get("launcher_servo");
        wrist = hardwareMap.servo.get("wrist_servo");
        claw = hardwareMap.servo.get("claw_servo");

        claw.setPosition(0);
        wrist.setPosition(0.5625);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("All motors and servos initialized");
        telemetry.update();

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;


            prevGetCrossPressed = getCrossPressed;

            telemetry.addLine("y = " + y);
            telemetry.addLine("x = " + x);
            telemetry.addLine("rx = " + rx);
            telemetry.addLine();

            double denominator = Math.abs(y)+Math.abs(x)+Math.abs(rx);

            /*
            double frontLeftPower = (y + x + rx)/denominator;
            double frontRightPower = (y - x - rx)/denominator;
            double backLeftPower = (y - x + rx)/denominator;
            double backRightPower = (y + x - rx)/denominator;
             */

            double frontLeftPower = (y * 0.625 + x + rx * 0.625)/denominator;
            double frontRightPower = (y * 0.625 - x - rx * 0.625)/denominator;
            double backLeftPower = (y * 0.625 - x + rx * 0.625)/denominator;
            double backRightPower = (y * 0.625 + x - rx * 0.625)/denominator;

            telemetry.addLine("frontLeftPower = " + frontLeftPower);
            telemetry.addLine("frontRightPower = " + frontRightPower);
            telemetry.addLine("backLeftPower = " + backLeftPower);
            telemetry.addLine("backRightPower = " + backRightPower);
            telemetry.addLine();

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);


            if (gamepad1.right_bumper) {
                launcher.setPosition(0.25);
                telemetry.addLine("Drone launched");
                telemetry.addLine();
            }

            if (gamepad1.dpad_up) {
                wrist.setPosition(0.65);
            }

            if (gamepad1.dpad_down) {
                wrist.setPosition(0.5625);
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
                        claw.setPosition(0);
                        clawServoPos = "CLOSED";
                    } else if (clawServoPos == "CLOSED") {
                        claw.setPosition(0.4);
                        clawServoPos = "OPEN";
                    }
                }

            } else {
                getCrossPressed = 0;
            }
            telemetry.update();
        }
    }
}
