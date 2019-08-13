package thinkinjava.test;

import sun.tools.asm.CatchData;

import javax.transaction.xa.XAException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * 临时类
 *
 * @author yinxing
 * @date 2019/6/2
 */

public class TempClass {

    try((BufferedReader br = new BufferedReader());
        BufferedWriter bw = new BufferedWriter()) {
        // do something
        catch (IOException | XAException e){
            // handle it
        }
    }
}
