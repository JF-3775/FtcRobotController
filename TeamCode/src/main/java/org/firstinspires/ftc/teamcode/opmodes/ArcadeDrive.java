package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Arcade Drive", group="Exercises")
//@Disabled
public class ArcadeDrive extends LinearOpMode
{
    DcMotor frontLeft, backLeft, frontRight, backRight;
    float   leftPower, rightPower, xValue, yValue;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("front_left");
        backLeft = hardwareMap.dcMotor.get("back_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backRight = hardwareMap.dcMotor.get("back_right");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        while (opModeIsActive())
        {
            yValue = gamepad1.right_stick_y * -1;
            xValue = gamepad1.right_stick_x * -1;

            leftPower =  yValue - xValue;
            rightPower = yValue + xValue;

            frontLeft.setPower(Range.clip(leftPower, -1.0, 1.0));
            backLeft.setPower(Range.clip(leftPower, -1.0, 1.0));
            frontRight.setPower(Range.clip(rightPower, -1.0, 1.0));
            backRight.setPower(Range.clip(rightPower, -1.0, 1.0));

            telemetry.addData("Mode", "running");
            telemetry.addData("stick", "  y=" + yValue + "  x=" + xValue);
            telemetry.addData("power", "  left=" + leftPower + "  right=" + rightPower);
            telemetry.update();

            idle();
        }
    }
}