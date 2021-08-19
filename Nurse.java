public class Nurse extends Thread{


    public Nurse(int i){
        super("Nurse");
    }

    public void run(){
        synchronized (this){
//

                try{
                    sleep(600);
                    while(!Project1.m.waitingStudents2.isEmpty() && Project1.m.max != 3) {

                        Project1.m.wakeUPStudent(this);
                    }
                }catch (Exception e){

                }
            //}
        }

    }
}
