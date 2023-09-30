package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MyFirstTeleOp", group = "TeleOp")
public class MyFirstTeleOp extends LinearOpMode {
    //parameter declaration
    DcMotor frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    double motorPower = 0.5;
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeftMotor = hardwareMap.get(DcMotor.class,"front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class,"front_right_motor");
        backLeftMotor = hardwareMap.get(DcMotor.class,"back_left_motor");
        backRightMotor = hardwareMap.get(DcMotor.class,"back_right_motor");
        while(!isStopRequested()) {
            frontLeftMotor.setPower(motorPower);

        }
    }
}
