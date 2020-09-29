package com.pikacly.pdf.demo.service;

import com.pikacly.pdf.demo.utils.FileUtils;
import com.pikacly.pdf.demo.utils.PdfUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author ly
 * @version 1.0
 * @date 2020/9/29 11:17 上午
 * @project pdf-demo
 * @package com.pikacly.pdf.demo.service
 * @description
 */
@Service
public class PdfService {
    public void createPdf(String templateName, Map<String, String> map) {
        File file = FileUtils.getRelativePathFile("static/templates/" + templateName);
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String template = sb.toString();
        for(String key : map.keySet()) {
            Pattern p = PdfUtils.getPatternForId(key);
            System.out.println("pattern: " + p);
            template = PdfUtils.replaceByPattern(template, p, map.get(key));
        }

        PdfUtils.generatePdf(template, "test.pdf");
    }
}
