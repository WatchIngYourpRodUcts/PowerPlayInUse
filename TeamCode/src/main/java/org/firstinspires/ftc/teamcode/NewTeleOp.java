//gamepad 1 left stick y = forward
//gamepad 1 left stick x = strafe

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by Alyssa on 12-05-19
 */

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "New Tele" , group = "testPrograms")
public class NewTeleOp extends LinearOpMode{
    private DcMotor frontRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor backLeftMotor;

    //arm
    private DcMotor armMotor;


    @Override
    public void runOpMode () throws InterruptedException {
        frontRightMotor = hardwareMap.dcMotor.get("frontRight");
        frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
        backRightMotor = hardwareMap.dcMotor.get("rearRight");
        backLeftMotor = hardwareMap.dcMotor.get("rearRight");

        armMotor = hardwareMap.dcMotor.get("armMotor");

        //directions
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        armMotor.setDirection(DcMotorSimple.Direction.FORWARD);


        //Drive Variables
        double drive;
        double strafe;
        double rotate;

        double front_left_speed;
        double rear_left_speed;
        double front_right_speed;
        double rear_right_speed;

        double arm_speed = 0.75;

        waitForStart();
        while (opModeIsActive()){

            drive = gamepad1.right_stick_y;
            strafe = gamepad1.right_stick_x;
            rotate = -gamepad1.left_stick_x;

            //mightneed clarifying parenthaseise
            front_left_speed = (drive - strafe) + rotate;
            rear_left_speed = (drive + strafe) + rotate;
            front_right_speed = (drive + strafe) - rotate;
            rear_right_speed = (drive - strafe) - rotate;

//-----------------------------------Gamepad 1 Start------------------------------------------------
            //Drive
            frontRightMotor.setPower(limit(front_right_speed));
            frontLeftMotor.setPower(limit(front_left_speed));
            backRightMotor.setPower(limit(rear_right_speed));
            backLeftMotor.setPower(limit(rear_left_speed));
//------------------------------------Gamepad 1 End-------------------------------------------------

            if(gamepad1.right_bumper){
                armMotor.setPower(arm_speed);
            } else if(gamepad1.left_bumper){
                armMotor.setPower(-arm_speed);
            } else{
                armMotor.setPower(0);
            }
            idle();
        }
    }
    public double limit(double number){
        if(number < -1.0){
            return -1.0;
        }
        else if(number > 1){
            return 1;
        }
        else{
            return number;
        }
    }
}