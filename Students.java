import java.sql.SQLOutput;
import java.util.Random;
import java.util.Vector;

public class Students extends Thread {

    public static Random rand = new Random();
    double number1,number2;
    private int numStudents;

    public Students(int i){
        super("Student-" + i);
        this.numStudents = i;
    }

    public void run(){

            synchronized (this) {
                number1 = rand.nextDouble();
                if (number1 > 0.15) {  //85% student submit the questionnaire
                    msg(this.getName() + " has submitted the health questionnaire.");
                    Project1.isSubmit = true;
                    Project1.m.getInLineQ(this);
                    if(Project1.m.found){
                        msg(this.getName() + " go to nurse room.");
                       Project1.m.studentGoToNurseRoom(this);
                       if(Project1.m.isPostive){

                           msg(this.getName() + " test is positive and send to go home");
                           if(Project1.m.max == 3){
                               msg("everybody go home.");
                           }
                       }else {
                           // student test negative and go to class room
                           msg(this.getName() + " test is negative and go to classroom.");
                           Project1.m.studentGoToClassroom(this);
                       }
                    }
                    else{
                        msg(this.getName() + " go to classroom");
                        Project1.m.studentGoToClassroom(this);
                    }

                } else {  //15% student forgot to submit the questionnaire.
                    msg(this.getName() + " has not submitted the health questionnaire.");
                    Project1.isSubmit = false;
                    Project1.m.getInLineQ(this);
                }


            }



    }

    void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - Project1.time) + "]: " + s);
    }
}
