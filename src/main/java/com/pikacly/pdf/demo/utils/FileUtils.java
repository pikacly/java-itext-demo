package com.pikacly.pdf.demo.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author ly
 * @version 1.0
 * @date 2020/9/29 11:08 上午
 * @project pdf-demo
 * @package com.pikacly.pdf.demo.utils
 * @description
 */
public final class FileUtils {
    /**
     * 查找resources下的文件
     * @param path
     * @return
     */
    public static File getRelativePathFile(String path) {
        URL url = FileUtils.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new RuntimeException("未找到指定文件: " + path);
        }

        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }
}
