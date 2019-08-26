package effectivejava.chapter2.item13;

/**
 * @author yinxing
 * @date 2019/8/20
 */


/**
 * 假设在为哈希表编写一个 clone 方法，其内部包含一个哈希桶数组，每个哈希桶都指向「键-值」对链表的第一项。
 * 为了提高性能，该类实现了自己的轻量级单链表，而没有使用 java 内部提供的 java.util.LinkedList：
 */
public class HashTable implements Cloneable {

    private Entry[] bucketes;


    private static class Entry {
        final Object key;
        Object value;
        Entry next;

        public Entry(Object key, Object value, Entry entry) {
            this.key = key;
            this.value = value;
            this.next = entry;
        }

        /**
         * 递归实现深度拷贝:
         * 克隆链表不是一个好方法，因为它为列表中的每个元素消耗一个栈帧（stack frame）。
         * 如果列表很长，这很容易导致堆栈溢出。 为了防止这种情况发生，可以用迭代来替换 deepCopy 中的递归。见下面
         * @return
         */
//        Entry deepCopy() {
//            return new Entry(key, value, next == null ? null : next.deepCopy());
//        }

        Entry deepCopy() {
            Entry entry = new Entry(key,value,next);
            for (Entry p = entry;p.next!=null;p = p.next){
                p.next = new Entry(p.next.key,p.next.value,p.next);
            }
            return entry;
        }

    }

    /**
     * Broken clone method - results in shared mutable state!(导致共享可变状态)
     * 反例: 递归地克隆哈希桶数组，虽然被克隆的对象有自己的哈希桶数组，但是这个数组引用与原始数组相同的链表，这很容易导致克隆对象和原始对象中的不确定性行为。
     * 要解决这个问题，你必须复制包含每个桶的链表。
     * <p>
     * 备注:虽然被克隆的对象有自己的哈希桶数组，但是 Entry 又是一个新类，内部由新的元素，这样只是浅拷贝。
     */
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        HashTable hashTable = (HashTable) super.clone();
//        hashTable.bucketes = bucketes.clone();
//        return hashTable;
//    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            HashTable hashTable = (HashTable) super.clone();
            hashTable.bucketes = new Entry[bucketes.length];
            for (int i = 0; i < bucketes.length; i++) {
                if (bucketes[i] != null) {
                    hashTable.bucketes[i] = bucketes[i].deepCopy();
                }
            }
            return hashTable;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {

    }
}
