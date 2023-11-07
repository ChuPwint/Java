import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tree a = new Tree("apple", "winter", 2.5, 2.5, 3);


        ArrayList<Tree> arrLst = new ArrayList<Tree>();
        arrLst.add(a);


        /*
        arrLst.add(new GregorianCalendar(2022, 0, 1));
        arrLst.add(new GregorianCalendar(2023, 0, 1));

        System.out.println(arrLst.size());
        boolean b = arrLst.remove(new GregorianCalendar(2021, 0, 1));
        System.out.println(b);
        System.out.println(arrLst.size());
        System.out.println("---------------------------");


        arrLst.add(0, new GregorianCalendar(2020, 0, 1));
        arrLst.add(1, new GregorianCalendar(2021, 0, 1));

        for (int i = 0; i < arrLst.size(); i++) {
            GregorianCalendar cal = arrLst.get(i);
            System.out.println(cal.get(Calendar.YEAR));
        }
        System.out.println("---------------------------");

        GregorianCalendar cal1 = arrLst.set(3, new GregorianCalendar(2025, 0, 1));
        System.out.println("Old date: " + cal1.get(Calendar.YEAR));

        for (int i = 0; i < arrLst.size(); i++) {
            GregorianCalendar cal = arrLst.get(i);
            System.out.println(cal.get(Calendar.YEAR));
        }
        System.out.println("---------------------------");

        arrLst.remove(2);
        for (int i = 0; i < arrLst.size(); i++) {
            GregorianCalendar cal = arrLst.get(i);
            System.out.println(cal.get(Calendar.YEAR));
        }
*/
    }
}