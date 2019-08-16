//package effectivejava.chapter1.item8;
//
///**
// * 使用 cleaner 机制作为安全网的自动关闭类
// * An autocloseable class using a cleaner as a safety net
// *
// * @author yinxing
// * @date 2019/8/15
// */
//
//public class Room implements AutoCloseable {
//
//    private static final Cleaner CLEANER = Cleaner.create();
//
//    /**
//     * Resource that required cleaning.Must not refer to Room.
//     */
//    public static class State implements Runnable {
//        /**
//         * 房间内的垃圾堆数量
//         */
//        int numJunkPiles;
//
//        public State(int numJunkPiles) {
//            this.numJunkPiles = numJunkPiles;
//        }
//
//        /**
//         * 由关闭方法或者 cleaner 调用
//         */
//        @Override
//        public void run() {
//            System.out.println("Cleaning Room");
//            numJunkPiles = 0;
//        }
//    }
//
//    /**
//     * the state of this room,shared with our cleanable
//     */
//    private final State state;
//
//    /**
//     * our cleanable. Cleans the room when it's eligible(合格的) for gc
//     */
//    private final Cleaner.Cleanble cleanble;
//
//    public Room(int numJunkPiles) {
//        state = new State(numJunkPiles);
//        cleanble = CLEANER.register(this, state);
//    }
//
//    @Override
//    public void close() throws Exception {
//        CLEANER.clean();
//    }
//
//    /**
//     * 正例:
//     * Room 的 Cleaner 机制仅仅被用作一个安全网。
//     * 如果客户将所有 Room 的实例放在 try-with-resource 块中，则永远不需要自动清理。
//     * 行为良好的客户端如下所示：
//     */
////    public static void main(String[] args) {
////        try (Room room = new Room(7)) {
////            System.out.println("GoodBye");
////        }
////    }
//
//    /**
//     * 反例:不合规矩的程序，它从来不清理它的房间
//     * 除非调用System.gc(),但是也不会保证会执行，而且显示调用会影响性能
//     */
//    public static void main(String[] args) {
//        Room room = new Room(99);
//        // 不会打印
//        System.out.println("peace out");
//        // 加了之后也不会保证会打印 "peace out"
//        System.gc();
//    }
//
//}
