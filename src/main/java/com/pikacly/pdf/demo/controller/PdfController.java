package com.pikacly.pdf.demo.controller;

import com.pikacly.pdf.demo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ly
 * @version 1.0
 * @date 2020/9/29 11:17 上午
 * @project pdf-demo
 * @package com.pikacly.pdf.demo.controller
 * @description
 */
@RestController
@RequestMapping("pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @RequestMapping("/test")
    public String test() {
        // 模拟前端传参
        final String templateName = "template.html";
        Map<String, String> map = new HashMap<>();
        map.put("name", "pikacly");
        map.put("address", "pikacly.com");

        pdfService.createPdf(templateName, map);
        return "success";
    }
}
