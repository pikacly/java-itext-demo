package com.pikacly.pdf.demo.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pikacly.pdf.demo.font.AsianFontProvider;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ly
 * @version 1.0
 * @date 2020/9/29 10:54 上午
 * @project pdf-demo
 * @package com.pikacly.pdf.demo.utils
 * @description
 */
public final class PdfUtils {

    /**
     * 生成pdf文件
     * @param template 源文件
     * @param desFile 目的文件
     */
    public static void generatePdf(String template, String desFile) {
        Document document = null;
        PdfWriter pdfWriter = null;
        FileOutputStream fos = null;
        try {
            document = new Document(PageSize.LETTER);
            fos = new FileOutputStream(desFile);
            pdfWriter = PdfWriter.getInstance(document, fos);

//            pdfWriter.open();
            document.open();
            document.newPage();
            System.out.println("待生成的template");
            System.out.println(template);

            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
//            worker.parseXHtml(pdfWriter, document, new StringReader(template));
            worker.parseXHtml(pdfWriter, document,
                    new ByteArrayInputStream(template.getBytes()), StandardCharsets.UTF_8, new AsianFontProvider());

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            // 注释掉 否则可能会遇到
            // The page 1 was requested but the document has only 0 pages.错误
//            if (pdfWriter != null) {
//                pdfWriter.close();
//            }
            document.close();
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param template 模板
     * @param p 正则表达式
     * @param replacement 替换后的字符
     * @param replaceAll 是否全替换
     * @return String 格式化后的字符串
     */
    public static String replaceByPattern(String template, Pattern p, String replacement, Boolean replaceAll) {
        Matcher m = p.matcher(template);
        if (!m.find()) {
            // 未找到指定id的元素节点
            return template;
        }
        if (replaceAll) {
            return m.replaceAll(replacement);
        } else {
            return m.replaceFirst(replacement);
        }
    }

    public static String replaceByPattern(String template, Pattern p, String replacement) {
        return replaceByPattern(template, p, replacement, false);
    }

    /**
     * 根据标签的id获取不同的正则匹配
     * @param id 标签id
     * @return Pattern
     */
    public static Pattern getPatternForId(String id) {
        // java正则表达式中前置条件中不支持*等不确定长度的匹配
        // final String s = "(?<=<span id='%s'.*?>).*?(?=</span>)";
        String s = "(?<=<span id='%s'.{0,10000}>).*?(?=</span>)";
        s = String.format(s, id);

        return Pattern.compile(s);
    }

}
