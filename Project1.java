import java.util.Random;
/*
Cheng Dai
 */

public class Project1 {

    static int students = 20;
    static int nurse = 1;
    static int principle = 1;
    static int instructor = 2;
    public static long time = System.currentTimeMillis();
    public static Monitor m = new Monitor();
    public static boolean isSubmit = false;
    public static boolean c = true;



    public static void main(String[] args){
        try {

            for(int i = 0; i < students; i++){
                new Students(i).start();
            }


            new Principal(principle).start();

            new Nurse(nurse).start();

            for(int i = 0; i < instructor; i++){
                new Instructor(i).start();
            }


        }catch(Exception e){

        }
    }
}
