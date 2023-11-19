package topic;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * <p>FIXME: 简要描述本文件的功能</p>
 *
 * @author yinxing
 * @since 2023/10/16
 **/
public class Tmp {

  // 测试用的私钥
  protected static final String privateKeyForTest =
      "-----BEGIN RSA PRIVATE KEY-----\n"
          + "MIICXAIBAAKBgQCyauGZYYv+PAltncQtG6VFxJiF+RqSii1bXimSu5SLKVOgzCAt\n"
          + "tE3laGZA0hrrG8SY2J/rjBx5e30JzpPXGFVeLzuxFSe1kKL34UmySYJhel/iuZIl\n"
          + "ElozyibcXmMU3mAQnpbCyw5rPBQDgIJ2a1MesZU8fTotY7XnDdKT6SuL/wIDAQAB\n"
          + "AoGBAIYq77mK7F5jwWdIA5U0O+WqQFRHDh9yf0c9GSA5pSu9CSFbPzt/YjCIgJRw\n"
          + "K0Pv3rhd9myIMYV9QnsUeIKAzvhBwHEi84DiBkntX79W/OlF2RriRSg+88FNr4Wg\n"
          + "Xnm9Xu7MpR8RuBPjWgebNSDcWOfKeZC3GtubZ9sPEMmc+fuZAkEA4ZZq5NdAtz91\n"
          + "+K7CUFrCh4LCyhtK74e2gfAscZufRpUU/Y0GqJcgiap+q8zfYdMT8qFU/kK8O6Ti\n"
          + "tUuJ3Y4e0wJBAMp4gjbV0hO+IXmEf6oiiL0BXjsggbGvrgZegwDoQby2OuUaEV8w\n"
          + "VPgqCYiEmF/0CMEjRZc0zlbN2DlnJZEm2qUCQHayAW6FP9zrfDQsJ7vF9YL9r2ZO\n"
          + "NACShNobuBbSzlyCBrheYrmEQy+MUPguZP8A5AcPEjxyer5bRJols9WqeNcCQGYP\n"
          + "0TPrHPauxRkIXYJZ3ivIYMPVYws6z7KOOeNfMKP8CDwso80kA/EE38FddaII1dDm\n"
          + "AtJx4AGJyhFFmX5N47ECQEZAaYImzSDBOatIueftVty4nPntbiw2V9lw8vIXHZ0/\n"
          + "RBjuER4vbms19ZTJ/d1uqJbRgCGDvY+3ivg+57YeRCE=\n"
          + "-----END RSA PRIVATE KEY-----";

  public static void main(String[] args) {
//    List<String> tableHeader = Arrays.asList("aaa", "bbb");
//    String[] array = tableHeader.toArray(new String[0]);
//    System.out.println(Arrays.toString(array));

    System.out.println(Base64.getEncoder().encodeToString(privateKeyForTest.getBytes()));
  }

}
