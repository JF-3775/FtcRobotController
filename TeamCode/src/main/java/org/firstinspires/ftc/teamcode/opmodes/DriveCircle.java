package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Circle")
//@Disabled
public class DriveCircle extends LinearOpMode
{
    DcMotor frontLeft, backLeft;
    DcMotor frontRight, backRight;

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

        telemetry.addData("Mode", "running");
        telemetry.update();

        sleep(500);              // wait so that above telemetry is visible.

        // set power levels 75% left and 10% right to drive in an arc to the right.

        frontLeft.setPower(0.75);
        backLeft.setPower(0.75);
        frontRight.setPower(0.20);
        backRight.setPower(0.20);

        sleep(5000);            // drive 5 seconds to make a circle.

        // turn the motors off.

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}