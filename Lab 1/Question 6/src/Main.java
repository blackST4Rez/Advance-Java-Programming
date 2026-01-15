//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
        public class Main {
            public static void main(String[] args) {
                Result student1 = new Result();
                student1.setRollNo(67);
                student1.setMathMarks(98);
                student1.setScienceMarks(58);
                student1.setTotal();
                System.out.println("The Roll no of the student is  :"+student1.getRollNo());
                System.out.println("The marks in Science is "+student1.getScienceMarks());
                System.out.println("The marks in Maths is "+student1.getMathMarks());
                System.out.println("The total marks of student is "+ student1.getTotal());

                System.out.println();
                System.out.println("Lab No : 1");
                System.out.println("Name : Raka Maharjan");
                System.out.println("ID : 2308-1002");
            }
        }

