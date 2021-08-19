import java.util.Random;

public class Principal extends Thread{

    public Principal(int i){
        super("Principal");
    }

    public void run(){


            try {
            sleep(100);
            Project1.m.PrincipleGetIn(this);

            while(!Project1.m.waitingStudents1.isEmpty() && Project1.m.max != 3) {
                //sleep(100);
              Project1.m.getInLineC(this); //decide to go to nurse room or classroom
            }
                } catch (Exception e) {

                }
    }

    private void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - Project1.time) + "]: " + s);
    }
}
