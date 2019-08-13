package javacore;

/**
 * @author yinxing
 * @date 2019/5/30
 */

public class Lecture3 {


    // 需要 jdk 9 及以上版本

//    /**
//     * 实践中，我们可以为自己的模块构建一个Cleaner，然后实现相应的清理逻辑。下面是JDK自身提供的样例程序:
//     */
//    public class CleaningExaple implements AutoCloseable {
//
//        /**
//         * A cleaner, preferably one shared within a library
//         */
//        private static final Cleaner CLEANER = <cleaner>;
//
//        static class State implements Runnable {
//            State() {
//                // initialize State needed for cleaning action
//            }
//
//            @Override
//            public void run() {
//                // cleanup action accessing State, executed at most once
//            }
//        }
//
//        private final State state;
//        private final Cleaner.cleanable cleanable;
//
//        public CleaningExaple() {
//            this.state = new State();
//            this.cleanable = cleaner.register(this, state);
//        }
//
//        @Override
//        public void close() throws Exception {
//
//        }
//    }

}
