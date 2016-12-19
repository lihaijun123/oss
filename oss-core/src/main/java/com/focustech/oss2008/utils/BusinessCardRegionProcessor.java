package com.focustech.oss2008.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BusinessCardRegionProcessor {
    public void readXML(String filePath, HashMap<String, HashMap<String, String>> STORE_REGION_MAP) {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
            String configDir = filePath + "/temp.xml";
            InputStream is = new FileInputStream(new File(configDir));
            Document doc = domBuilder.parse(is);
            Element element = doc.getDocumentElement();
            NodeList books = element.getChildNodes();
            if (books != null) {
                for (int i = 0; i < books.getLength(); i++) {
                    Node book = books.item(i);
                    if (book.getNodeType() == Node.ELEMENT_NODE) {
                        String region = book.getAttributes().getNamedItem("region").getNodeValue();
                        String divide_method = book.getAttributes().getNamedItem("divide-method").getNodeValue();
                        for (Node node = book.getFirstChild(); node != null; node = node.getNextSibling()) {
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                if (node.getNodeName().equals("company")) {
                                    // 設置地區
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put("region", region);
                                    map.put("divide-method", divide_method);
                                    String name = node.getAttributes().getNamedItem("name").getNodeValue();
                                    // 設置公司名
                                    map.put("name", name);
                                    //
                                    for (Node subNode = node.getFirstChild(); subNode != null; subNode =
                                            subNode.getNextSibling()) {
                                        if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                                            if (subNode.getNodeName().equals("org_no")) {
                                                // 設置公司機構號
                                                String subValue = subNode.getFirstChild().getNodeValue();
                                                map.put("org_no", subValue);
                                            }
                                            if (subNode.getNodeName().equals("devide_fix")) {
                                                if ("fix".equals(divide_method)) {
                                                    int subValue =
                                                            Integer.parseInt(subNode.getFirstChild().getNodeValue());
                                                    map.put("devide_fix", String.valueOf(subValue));
                                                    // 便于重新分配
                                                    map.put("save_devide_fix", String.valueOf(subValue));
                                                }
                                            }
                                            if (subNode.getNodeName().equals("devide_average")) {
                                                if ("percentage".equals(divide_method)) {
                                                    int subValue =
                                                            Integer.parseInt(subNode.getFirstChild().getNodeValue()
                                                                    .substring(
                                                                            0,
                                                                            subNode.getFirstChild().getNodeValue()
                                                                                    .length() - 1));
                                                    map.put("devide_average", String.valueOf(subValue));
                                                }
                                            }
                                        }
                                    }
                                    STORE_REGION_MAP.put((String.valueOf(map.get("region")) + String.valueOf(map
                                            .get("org_no"))), map);
                                }
                            }
                        }
                    }
                }
            }
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
