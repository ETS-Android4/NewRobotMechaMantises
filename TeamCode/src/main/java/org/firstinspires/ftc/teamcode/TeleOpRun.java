package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp!")

public class TeleOpRun extends LinearOpMode {
    public void runOpMode(){
        //all your variables here
        MantisesClass mantisClass = new MantisesClass(this); //this does the init
        while (!opModeIsActive() && !isStopRequested()) {//you can just use waitForStart() here, but this prints stuff out, you guys don't have this so it would be running while you have not hit the start button which is illegal
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }
        while(!isStopRequested()){
            if(gamepad2.y) {
                mantisClass.lineararm.setTargetPosition((384*2)+235);
                mantisClass.lineararm.setPower(0.2);
                mantisClass.lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if(gamepad2.a) {
                mantisClass.lineararm.setTargetPosition(0);
                mantisClass.lineararm.setPower(0.2);
                mantisClass.lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            else if(gamepad2.b){
                mantisClass.intakebox.setPosition(0.6);

            }
            else if(gamepad1.right_bumper){
                mantisClass.carousel_arm.setPower(1);
            }else if(gamepad1.left_bumper){
                mantisClass.carousel_arm.setPower(0);
            }
            else if (gamepad2.right_bumper){
                mantisClass.rotateintake.setTargetPosition((384/4)/2);
                mantisClass.rotateintake.setPower(0.3);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (mantisClass.rotateintake.isBusy()){

                }
                mantisClass.intakebox.setPosition(0.65);
                mantisClass.rotateintake.setTargetPosition(0);
                mantisClass.rotateintake.setPower(0.3);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (mantisClass.rotateintake.isBusy()){

                }
                mantisClass.rotateintake.setPower(0);
                mantisClass.intake.setPower(-1);


            }else if(gamepad2.left_bumper){
                mantisClass.intake.setPower(0);
                mantisClass.rotateintake.setTargetPosition(24);
                mantisClass.rotateintake.setPower(0.3);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                mantisClass.intakebox.setPosition(0.5);
                mantisClass.intakebox.setPosition(0);
                mantisClass.rotateintake.setTargetPosition(0);
                mantisClass.rotateintake.setPower(0.1);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }else if(gamepad2.dpad_left){
                mantisClass.rotatearm.setPosition(0.2);
            }else if(gamepad2.dpad_right){
                mantisClass.rotatearm.setPosition(0.8);
            }else if (gamepad2.dpad_up){
                mantisClass.rotatearm.setPosition(0.525);
            }else if(gamepad2.x){
                mantisClass.intakebox.setPosition(0);
            }else if(gamepad2.dpad_down){
                mantisClass.rotateIntakeUp();
                mantisClass.intakebox.setPosition(0.4);
                mantisClass.rotateIntakeDown();
            }
            mantisClass.mechanum();

        }
        stop();//stops the program
    }
}