package lv.rvt;

import java.io.StringReader;
import org.xml.sax.InputSource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SvgTreeBuilder {
    
    public static class SvgNode {
        public String name;
        public Map<String, String> attributes = new LinkedHashMap<>();
        public String text = "";
        public List<SvgNode> children = new ArrayList<>();

        public SvgNode(String name) {
            this.name = name;
        }
    }

    public static SvgNode parseSvgToTree(String svgXml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(svgXml)));

        Element root = doc.getDocumentElement(); // should be svg
        return toSvgNode(root);
    }

    private static SvgNode toSvgNode(Node domNode) {
        SvgNode node = new SvgNode(domNode.getNodeName());

        NamedNodeMap attrs = domNode.getAttributes();
        if (attrs != null) {
            for (int i = 0; i < attrs.getLength(); i++) {
                Node a = attrs.item(i);
                node.attributes.put(a.getNodeName(), a.getNodeValue());
            }
        }

        NodeList list = domNode.getChildNodes();
        StringBuilder textBuilder = new StringBuilder();

        for (int i = 0; i < list.getLength(); i++) {
            Node child = list.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                node.children.add(toSvgNode(child));
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String t = child.getTextContent().trim();
                if (!t.isEmpty()) {
                    if (textBuilder.length() > 0) textBuilder.append(" ");
                    textBuilder.append(t);
                }
            }
        }

        node.text = textBuilder.toString();
        return node;
    }

    public static void printTree(SvgNode node, int level) {
        String indent = "  ".repeat(level);
        System.out.println(indent + node.name + " " + node.attributes + (node.text.isEmpty() ? "" : (" text=" + node.text)));
        for (SvgNode c : node.children) {
            printTree(c, level + 1);
        }
    }
}
