package effectivejava.chapter5.item35;

/**
 * 反例:Abuse of ordinal to derive an associated value - DON'T DO THIS
 * @author yinxing
 * @date 2019/9/4
 */

public enum EnsembleEnum {

    /**
     * 四重奏
     */
    SOLO,   DUET,   TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET,  DECTET;

    /**
     * Ensemble
     */
    public int numberOfMusicans(){
        return ordinal() + 1;
    }
}
