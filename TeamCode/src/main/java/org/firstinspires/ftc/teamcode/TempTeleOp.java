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
@Disabled

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name = "Temp Tele" , group = "testPrograms")
public class TempTeleOp extends LinearOpMode{
    Samantha Samantha = new Samantha();
    @Override
    public void runOpMode () throws InterruptedException {
        //Drive Variables
        double drive;
        double strafe;
        double rotate;

        double front_left_speed;
        double rear_left_speed;
        double front_right_speed;
        double rear_right_speed;

        double arm_speed = 0.5;

        waitForStart();
        while (opModeIsActive()){

            drive = gamepad1.right_stick_y;
            strafe = gamepad1.right_stick_x;
            rotate = gamepad1.left_stick_x;

            //mightneed clarifying parenthaseise
            front_left_speed = (drive - strafe) + rotate;
            rear_left_speed = (drive + strafe) + rotate;
            front_right_speed = (drive + strafe) - rotate;
            rear_right_speed = (drive - strafe) - rotate;

//-----------------------------------Gamepad 1 Start------------------------------------------------
            //Drive
            Samantha.frontLeft.setPower(limit(front_left_speed));
            Samantha.rearLeft.setPower(limit(rear_left_speed));
            Samantha.frontRight.setPower(limit(front_right_speed));
            Samantha.rearRight.setPower(limit(rear_right_speed));
//------------------------------------Gamepad 1 End-------------------------------------------------

            if(gamepad1.right_bumper){
                Samantha.armMotor.setPower(arm_speed);
            } else if(gamepad1.left_bumper){
                Samantha.armMotor.setPower(-arm_speed);
            } else{
                Samantha.armMotor.setPower(0);
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