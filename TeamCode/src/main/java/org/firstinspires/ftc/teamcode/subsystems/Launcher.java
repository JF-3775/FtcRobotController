package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Launcher {
    public Servo launcherServo;

    public enum LAUNCHER_STATE {
        SAFE,
        ARMED,
        LAUNCHED
    }
    public Launcher () {
        //launcherServo = hardwareMap.get("launcher_servo");
    }
}
