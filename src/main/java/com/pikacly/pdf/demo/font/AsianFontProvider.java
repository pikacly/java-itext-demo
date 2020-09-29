package com.pikacly.pdf.demo.font;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;

import java.io.IOException;

/**
 * @author ly
 * @version 1.0
 * @date 2020/9/29 1:17 下午
 * @project pdf-demo
 * @package com.pikacly.pdf.demo.font
 * @description
 */
public class AsianFontProvider extends XMLWorkerFontProvider {
    @Override
    public Font getFont(String fontname, String encoding, float size, int style) {
        try {
            BaseFont chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//            BaseFont chinese = BaseFont.createFont("/Song.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            return new Font(chinese, size, style);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return super.getFont(fontname, encoding);
    }
}
