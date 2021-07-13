package top.yms.recent.code8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code801 {
    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            // will print name of person (object)
            System.out.println("Person object - " + this.name + " -> successfully garbage collected");
        }
    }


    static class PersonTest {
        static void createMale() {
            //object p1 inside method becomes unreachable after createMale() completes
            Person p1 = new Person("John Doe");
            createFemale();

            // calling garbage collector
            System.out.println("GC Call inside createMale()");
            System.gc(); // p2 will be garbage-collected

            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static void createFemale() {
            //object p2 inside method becomes unreachable after createFemale() completes
            Person p2 = new Person("Jane Doe");
             {
            }
        }

        public static void main(String args[]) {
            createMale();

            // calling garbage collector
            System.out.println("\nGC Call inside main()");
            System.gc(); // p1 will be garbage-collected

            try {
                Thread.sleep(5 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Person p1 = new Person("John Doe");
        // p1 is no longer in use
// make p1 eligible for gc
        p1 = null;

// calling garbage collector
        System.gc(); // p1 will be garbage-collected

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");

        });

    }


}
