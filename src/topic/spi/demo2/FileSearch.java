package topic.spi.demo2;

import java.util.List;

/**
 * @author Alan Yin
 * @date 2021/10/29
 */

public class FileSearch implements Search {

    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索：" + keyword);
        return null;
    }
}
