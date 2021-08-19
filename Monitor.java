import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Monitor {

    public Vector waitingStudents1 = new Vector();
    public Vector waitingStudents2 = new Vector();
    public Vector waitingStudents3 = new Vector();
    public Vector waitingPrincipal = new Vector();
    public Vector waitingNurse = new Vector();
    public static Random rand = new Random();
    HashMap<Object, String> H = new HashMap<>();
    HashMap<Object, String> H2 = new HashMap<>();
    public Vector waitingInstructor = new Vector();
    private int picked = 1;
    public boolean found = false;
    public boolean isPostive = false;
    public int max = 0;
    public Vector numELA = new Vector();
    public Vector numMath = new Vector();
    public Vector numPHY = new Vector();

    public void getInLineQ(Students student) {
        Object convey = new Object();
        synchronized (convey) {
            if (cannotGoIn(convey, student.getName())) {
                while (true) //wait to be notified
                    try {
                        msg(student.getName() + " waiting for principle to check if test for COVID");
                        convey.wait();
                        found = pickedOneForTest();
                        break;
                    } catch (InterruptedException e) {
                        continue;

                    }
            } else {
                msg(student.getName() + " is sent to go home by principle.");
            }

        }
    }

    private boolean cannotGoIn(Object convey, String name) {
        boolean status;
        if (Project1.isSubmit) {
            waitingStudents1.addElement(convey);
            H.put(convey, name);
            //System.out.println(H.get(convey));
            status = true;
        } else {
            status = false;
        }
        return status;
    }

    public void PrincipleGetIn(Principal principal) {
        Object convey = new Object();
        synchronized (convey) {
            if (noPrincipleAvaliable(convey)) {
                while (true)
                    try {
                        convey.wait(); //principal is waiting
                        break;
                    } catch (InterruptedException e) {
                        continue;
                    }
            }
        }
    }

    private boolean noPrincipleAvaliable(Object convey) {
        boolean status;
        if (waitingStudents1.size() >= 10) {
            status = false;

        } else {
            status = true;

            waitingPrincipal.addElement(convey);
        }
        return status;
    }


    public void getInLineC(Principal principal) {  //go to nurse room or go to classroom
        Object convey = new Object();
        int num = rand.nextInt(3);

        if (!waitingStudents1.isEmpty()) {
            Object Next_objectA = waitingStudents1.elementAt(0);
            waitingStudents1.remove(0);
            synchronized (Next_objectA) {
                Next_objectA.notify();//waiting notify
            }
        }

    }


    private synchronized boolean pickedOneForTest() {
        boolean status = false;
        if (picked == 1) {
            status = true;
            picked++;

        } else if (picked == 2 || picked == 3) {
            status = false;
            picked++;
            if (picked == 4) {
                picked = 1;
            }
        }
        return status;
    }



    private void msg(String s) {
        System.out.println("[" + (System.currentTimeMillis() - Project1.time) + "]: " + s);
    }

    public void studentGoToNurseRoom(Students students) {

        Object convey = new Object();
        synchronized (convey) {
            if (cannotGoIn2(convey)) {
                while (true) //waitingStudents2 wait to be notified
                    try {
                        convey.wait();
                        isPostive = cannotGoIn3();
                        break;
                    } catch (InterruptedException e) {
                        continue;

                    }
            }
        }
    }


    private boolean cannotGoIn2(Object convey) {
        boolean status;

        status = true;
        waitingStudents2.addElement(convey);


        return status;
    }


    public synchronized void studentGoToClassroom(Students students) {
        if (numELA.size() < 4) {

            int roomLeft = 3 - numELA.size();
            msg(students.getName() + " go to ELA and there are " + roomLeft + " room left.");
            checkWhichClass(students, "ELA");

        } else if (numMath.size() < 4) {
            int roomLeft2 = 3 - numMath.size();
            msg(students.getName() + " go to Math and there are " + roomLeft2 + " room left.");
            checkWhichClass(students, "MATH");

        }else{
            msg(students.getName() + " go to Phy.");
            checkWhichClass(students, "PHY");
        }
    }

    public void checkWhichClass(Students students, String className) {
        Object convey = new Object();
        synchronized (convey) {
            if (cannotGoIn4(convey, className)) {
                while (true) //
                    try {

                        convey.wait();   //
                        break;
                    } catch (InterruptedException e) {
                        continue;

                    }
            }
        }
    }


    private synchronized boolean cannotGoIn4(Object convey, String className) {
        boolean status;

        if(className == "ELA"){

            numELA.addElement(convey);
            status = true;
        }else if(className == "MATH"){
            numMath.addElement(convey);
            status = true;
        }else{
            numPHY.addElement(convey);
            status = true;
        }
        return status;
    }

    public void wakeUPStudent(Nurse nurse) {

        if(!waitingStudents2.isEmpty()){
            Object Next_objectA = waitingStudents2.elementAt(0);
            waitingStudents2.remove(0);
            synchronized (Next_objectA) {
                Next_objectA.notify();//waiting notify
            }
        }

    }

    private boolean cannotGoIn3() {
        boolean status;
        double num = rand.nextDouble();
        if (num < 0.03) {
            status = true;
            max++;


        } else {
            status = false;
        }

        return status;
    }


    public void wakeUpInstructorAndStudents(Instructor instructor) {
    }
}

