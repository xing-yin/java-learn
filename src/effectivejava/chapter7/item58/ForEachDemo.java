package effectivejava.chapter7.item58;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author yinxing
 * @date 2019/9/12
 */

public class ForEachDemo {

    enum Suit {
        /**
         * 花色
         */
        CLUB, DIAMOND, HEART, SPADE
    }

    enum Rank {
        /**
         * 点数
         */
        ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
        NINE, TEN, JACK, QUEEN, KING
    }

    private static final Collection<Suit> SUITS = Arrays.asList(Suit.values());
    private static final Collection<Rank> RANKS = Arrays.asList(Rank.values());

    /**
     * 反例:
     * 问题是，对于外部集合（suit），next 方法在迭代器上调用了太多次。
     * 它应该从外部循环调用，因此每花色调用一次，但它是从内部循环调用的，因此每一张牌调用一次。
     * 在 suit 用完之后，循环抛出 NoSuchElementException 异常。
     */
    public static void foreach1() {
        for (Iterator<Suit> i = SUITS.iterator(); i.hasNext(); ) {
            for (Iterator<Rank> j = RANKS.iterator(); j.hasNext(); ) {
                // i.next() 会将 suit 用光
                System.out.println("i.next():" + i.next() + " j.next():" + j.next());
            }
        }
    }

    /**
     * 可以解决问题，但不美观
     */
    public static void foreach2() {
        for (Iterator<Suit> i = SUITS.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<Rank> j = RANKS.iterator(); j.hasNext(); ) {
                System.out.println("i.next():" + suit + " j.next():" + j.next());
            }
        }
    }

    /**
     * 正例:使用 foreach
     */
    public static void foreach3() {
        for (Suit suit : SUITS) {
            for (Rank rank : RANKS) {
                System.out.println("suit:" + suit + " rank:" + rank);
            }
        }
    }

    public static void main(String[] args) {
        // NoSuchElementException
        //foreach1();
        foreach2();
        foreach3();
    }
}
