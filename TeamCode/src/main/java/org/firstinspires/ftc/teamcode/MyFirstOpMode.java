package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "myFirstOpMode", group = "TeleOp")

public class MyFirstOpMode extends OpMode {
    DcMotor frontLeft, frontRight, backLeft, backRight;

    //declaration and initialisation
    double speed = 0.0;





    public void init() {

        //init
        frontLeft = hardwareMap.dcMotor.get("front_left");
        frontRight = hardwareMap.dcMotor.get("front_right");
        backLeft = hardwareMap.dcMotor.get("back_left");
        backRight = hardwareMap.dcMotor.get("back_right");
    }
    public void loop() {
        //assignment
        speed = gamepad1.left_stick_y;

        //loop
        frontRight.setPower(speed);
        backRight.setPower(speed);
        frontLeft.setPower(-speed);
        backLeft.setPower(-speed);
    }
}
