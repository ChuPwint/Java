public class SomePublicClass {
        // ...
        // ...
        int someVar;
        class SomeInnerClass1 {
                // ...
                void methodA() {
                        System.out.println(someVar);
                }
        }

        void method1() {
                int localVar = 10;
                class SomeInnerClass2 {
                        void methodB() {
                                System.out.println(localVar);
                                System.out.println(someVar);
                        }
                }
        }

}

class SomeOtherClass1 {
        // ...
        // ...
}

class SomeOtherClass2 {
        // ...
        // ...
}
