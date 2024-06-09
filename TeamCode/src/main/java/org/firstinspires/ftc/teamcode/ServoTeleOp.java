package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "servoTeleOp", group = "TeleOp")

public class ServoTeleOp extends LinearOpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    Servo Launcher;
    double y, x, rx;

    @Override
    public void runOpMode() throws InterruptedException {

        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
        Launcher = hardwareMap.servo.get("launcher_servo");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addLine("All motors and servos initialized");
        telemetry.update();

        waitForStart();

        if(isStopRequested()) return;

        while(opModeIsActive()) {
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;

            telemetry.addLine("y = " + y);
            telemetry.addLine("x = " + x);
            telemetry.addLine("rx = " + rx);

            double denominator = Math.abs(y)+Math.abs(x)+Math.abs(rx);

            /*
            double frontLeftPower = (y + x + rx)/denominator;
            double frontRightPower = (y - x - rx)/denominator;
            double backLeftPower = (y - x + rx)/denominator;
            double backRightPower = (y + x - rx)/denominator;
             */

            double frontLeftPower = (y * 0.75 + x + rx * 0.75)/denominator;
            double frontRightPower = (y * 0.75 - x - rx * 0.75)/denominator;
            double backLeftPower = (y * 0.75 - x + rx * 0.75)/denominator;
            double backRightPower = (y * 0.75 + x - rx * 0.75)/denominator;

            telemetry.addLine();
            telemetry.addLine("frontLeftPower = " + frontLeftPower);
            telemetry.addLine("frontRightPower = " + frontRightPower);
            telemetry.addLine("backLeftPower = " + backLeftPower);
            telemetry.addLine("backRightPower = " + backRightPower);
            telemetry.update();

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            backLeft.setPower(backLeftPower);
            backRight.setPower(backRightPower);

            if (gamepad1.right_bumper) {
                Launcher.setPosition(1);
            }
        }
    }
}
