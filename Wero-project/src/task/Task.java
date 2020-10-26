package task;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * main
 */
 class Task {

    public static void main(String[] args) {
        // Setting Teacher
        Teacher teacher = new Teacher("Vladislav", "Vladov");
        ArrayList<Student> students = new ArrayList<>();

        // Adding students to teacher
        for (int i = 0; i < 20; i++) {
            Student student = new Student("Student"+i, "Surname"+i, i+15);
            for (int j = 0; j < 30; j++) {
                // Randomly setting marks for students for 30 days
                float mark = (float) (Math.random() * 61) + 39;
                System.out.println(mark);
               student.addGrade(mark); 
            }
            students.add(student);
        }
        teacher.setStudents(students);

        // This part will be executed every 1 second
        Timer timer = new Timer();
        timer.schedule(new PrintAll(teacher), 0, 1000);
    }


    public static void printEverthing(Teacher teacher) {
        System.out.println(teacher.getStudents().get(1).getAge());
    }
}

class PrintAll extends TimerTask {
    // This is task Runner
    public Teacher teacher;
    public int counter = 0;
    PrintAll(Teacher t) {
        teacher = t;
    }
    public void run() {
        if (counter> 29) {
            cancel();
            System.out.println("# FINISHED #" + " \n");

            return;
        }
        ArrayList<PrintableStudents> pS = new ArrayList<>();
        float average = 0;
       for (Student stud : teacher.Students) {
           PrintableStudents student = new PrintableStudents(stud.getFirstname()+" "+ stud.getLastname().toUpperCase(), stud.getGrades().get(counter));
           average = average +  stud.getGrades().get(counter);
           pS.add(student);
        }

        // This is for sorting new list (pS) according to grade.
        Collections.sort(pS, new Comparator<PrintableStudents>() {
            @Override
            public int compare(PrintableStudents u1, PrintableStudents u2) {
              return u1.getGrade().compareTo(u2.getGrade());
            }
          });
        
        System.out.println("Day #" + (counter+1) +" \n");

    //    for (int i = 0; i < pS.size(); i++) {
    //     System.out.println("#"+ (i+1) + " " + pS.get(i).getNameSurname() + "- Grade: " + pS.get(i).getGrade()); 
    //    }
        System.out.printf("#1-" + pS.get(19).getNameSurname() + " - %.2f \n", pS.get(19).getGrade());
        System.out.printf("#2-" + pS.get(18).getNameSurname() + " - %.2f \n", pS.get(18).getGrade());
        System.out.printf("#3-" + pS.get(17).getNameSurname() + " - %.2f \n", pS.get(17).getGrade());

        System.out.format("Students average: %.2f \n", average/20);

        System.out.printf("#18-" + pS.get(2).getNameSurname() + " - %.2f \n", pS.get(2).getGrade());
        System.out.printf("#19-" + pS.get(1).getNameSurname() + " - %.2f \n", pS.get(1).getGrade());
        System.out.printf("#20-" + pS.get(0).getNameSurname() + " - %.2f \n", pS.get(0).getGrade());
        System.out.println("\n\n");
       counter++;
    }
}


// Classes Section -----------------------------------///

class Student {
    public String Firstname;
    public String Lastname;
    public int Age;
    public ArrayList<Float> Grades;

    public Student(String fn, String ln, int age){
        setFirstname(fn);
        setLastname(ln);
        setAge(age);
        ArrayList<Float> grades = new ArrayList<>();
        setGrades(grades); 
    }
    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public ArrayList<Float> getGrades() {
        return Grades;
    }

    public void setGrades(ArrayList<Float> grades) {
        Grades = grades;
    }

    public void addGrade(Float grade) {
        Grades.add(grade);
    }
}

class Teacher {
    public String Firstname;
    public String Lastname;
    public ArrayList<Student> Students;

    public Teacher(String fn, String ln){
        setFirstname(fn);
        setLastname(ln);
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public ArrayList<Student> getStudents() {
        return Students;
    }

    public void setStudents(ArrayList<Student> students) {
        Students = students;
    }
}


class PrintableStudents {
    public String NameSurname;
    public Float grade;
    
    public PrintableStudents(String nameSurname, Float grade) {
        NameSurname = nameSurname;
        this.grade = grade;
    }
    
    public String getNameSurname() {
        return NameSurname;
    }

    public void setNameSurname(String nameSurname) {
        NameSurname = nameSurname;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }
    
}


