package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "TeleOp!")
public class TeleOpRun extends LinearOpMode {
    public void runOpMode(){
        MantisesClass mantisClass = new MantisesClass(this); //this does the init


        while (!opModeIsActive() && !isStopRequested()) {//you can just use waitForStart() here, but this prints stuff out, you guys don't have this so it would be running while you have not hit the start button which is illegal
            telemetry.addData("status", "waiting for start command...");
            telemetry.update();
        }

        while(!isStopRequested()){
            double y = -gamepad1.right_stick_y;
            double x = gamepad1.right_stick_x *5;
            double rx = gamepad1.right_trigger-gamepad1.left_trigger;

            double denominator = Math.max(Math.abs(y)+Math.abs(x)+Math.abs(rx), 1);
            double frontLeftPower = (y+x+rx)/denominator;
            double backLeftPower = (y-x+rx)/denominator;
            double frontRightPower = (y-x-rx)/denominator;
            double backRightPower = (y+x-rx)/denominator;

            mantisClass.fl_wheel.setPower(frontLeftPower);
            mantisClass.bl_wheel.setPower(backLeftPower);
            mantisClass.fr_wheel.setPower(frontRightPower);
            mantisClass.br_wheel.setPower(backRightPower);

            double target = mantisClass.intakebox.getPosition();
            int targetTick = mantisClass.lineararm.getCurrentPosition();
            if(gamepad2.y) {
                mantisClass.lineararm.setTargetPosition(1440*2+400);
                mantisClass.lineararm.setPower(1);
                mantisClass.lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while(mantisClass.lineararm.isBusy()){

                }
                mantisClass.rotatearm.setPosition(1);
                //mantisClass.intakebox.setPosition(0);

            }
            else if(gamepad2.a) {
                mantisClass.intakebox.setPosition(1);
                sleep(1000);
                mantisClass.rotatearm.setPosition(0.55);
                sleep(1000);
                mantisClass.lineararm.setTargetPosition(0);
                mantisClass.lineararm.setPower(1);
                mantisClass.lineararm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                while (mantisClass.lineararm.isBusy()){

                }

            }
            else if(gamepad2.b){
                mantisClass.intakebox.setPosition(0);

            }
            if(gamepad2.dpad_left){
                mantisClass.intake.setPower(1);
            }else if(gamepad2.dpad_right){
                mantisClass.intake.setPower(-1);
            }else if(gamepad2.back){
                mantisClass.intake.setPower(0);
            }
            if(gamepad1.right_bumper){
                mantisClass.carousel_arm.setPower(-1);
            }else if(gamepad1.left_bumper){
                mantisClass.carousel_arm.setPower(0);
            }
            if (gamepad2.right_bumper){
                mantisClass.rotateintake.setPower(0);
                mantisClass.intake.setPower(-1);
//                mantisClass.rotateintake.setTargetPosition(65);
//                mantisClass.rotateintake.setPower(0.1);
//                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                while (mantisClass.rotateintake.isBusy()){
//
//                }
                mantisClass.intakebox.setPosition(0.35);

            }else if(gamepad2.left_bumper){
                mantisClass.intake.setPower(0);

                mantisClass.rotateintake.setTargetPosition(90);
                mantisClass.rotateintake.setPower(0.2);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                mantisClass.intakebox.setPosition(0.5);
                while (mantisClass.rotateintake.isBusy()){

                }
                mantisClass.intakebox.setPosition(1);
                sleep(1000);
                mantisClass.rotateintake.setTargetPosition(0);
                mantisClass.rotateintake.setPower(0.1);
                mantisClass.rotateintake.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
//            else if (gamepad2.left_bumper){
//                mantisClass.rotateintake.setPower(-0.5);
//                sleep(1000);
//                mantisClass.rotateintake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                mantisClass.rotateintake.setPower(0);
//            }
        }
        stop();//stops the program
    }
}