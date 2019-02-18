package CSNote.basis;

import java.util.ArrayList;
import java.util.List;

/**
 * 多态展示
 *
 * @author yinxing
 * @date 2019/2/11
 */

public class Polymorphic {

    public static class Instrument {

        public void play() {
            System.out.println("Instument is playing...");
        }
    }

    public static class Wind extends Instrument {

        @Override
        public void play() {
            System.out.println("Wind is playing...");
        }

    }

    public static class Percussion extends Instrument {

        @Override
        public void play() {
            System.out.println("Percussion is playing...");
        }
    }

    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Wind());
        instruments.add(new Percussion());
        for (Instrument instrument : instruments) {
            instrument.play();
        }
    }

}
