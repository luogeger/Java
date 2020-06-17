package com.first.xml;

import com.alibaba.fastjson.JSONObject;
import org.jdom2.JDOMException;
import org.junit.Test;

import java.io.IOException;

/**
 * 
 */
public class MainTest {
    
    @Test
    public void main1 () {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n" +
                "<sitemap>\n" +
                "　　<loc><![CDATA[http://qq.com/xml/_1.xml]]></loc>\n" +
                "　　<lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "<sitemap>\n" +
                "　　<loc><![CDATA[http://qq.com/xml/_2.xml]]></loc>\n" +
                "　　<lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "<sitemaps>\n" +
                "   <sitemap>\n" +
                "　　     <loc><![CDATA[http://qq.com/xml/_46.xml]]></loc>\n" +
                "　　     <lastmod><![CDATA[2018-06-20 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "   <sitemap>\n" +
                "　　     <loc><![CDATA[http://qq.com/xml/_47.xml]]></loc>\n" +
                "　　     <lastmod><![CDATA[2018-06-25 01:28:09]]></lastmod>\n" +
                "　　</sitemap>\n" +
                "</sitemaps>\n" +
                "</sitemapindex>\n";
        JSONObject json = null;
        try {
            json = XmlUtils.xml2Json(xml);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json.toJSONString());        
    }
}
