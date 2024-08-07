package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Square")
//@Disabled
public class DriveSquare extends LinearOpMode
{
    DcMotor leftMotor;
    DcMotor rightMotor;

    // called when init button is  pressed.
    @Override
    public void runOpMode() throws InterruptedException
    {
        leftMotor = hardwareMap.dcMotor.get("back_left");
        rightMotor = hardwareMap.dcMotor.get("back_right");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        sleep(500);        // wait so that above telemetry is visible.

        // each iteration of this for loop will drive one side of the square.

        for(int i = 0; i < 4; i++)
        {
            telemetry.addData("Mode", "driving side " + (i + 1));
            telemetry.update();

            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);

            sleep(1500); // drive straight for 1 second.

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            sleep(500);  // wait half second for bot to stop moving.

            // now set motors, one forward one reverse. Should cause the bot to rotate.

            leftMotor.setPower(0.25);
            rightMotor.setPower(-0.25);

            sleep(4000); // adjust this delay to get the bot to rotate 90 degrees.

            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);

            sleep(500); // wait for bot to stop moving.
        }

        // make sure the motors are off.

        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
