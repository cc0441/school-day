public class Instructor extends Thread{
    private int numInstructor;


    public Instructor(int i){
        super("Instructor" + i);
        this.numInstructor = i;
    }

    public void run() {

        synchronized (this) {
            while(Project1.c){
                Project1.m.wakeUpInstructorAndStudents(this);
            }
        }
    }
}
