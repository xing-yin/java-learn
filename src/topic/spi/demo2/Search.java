package topic.spi.demo2;

import java.util.List;

/**
 * 1.先定义好接口
 *
 * @author Alan Yin
 * @date 2021/10/29
 */

public interface Search {

    List<String> searchDoc(String keyword);
}
