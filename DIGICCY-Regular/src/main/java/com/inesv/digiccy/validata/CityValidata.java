package com.inesv.digiccy.validata;

import java.util.ArrayList;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.inesv.digiccy.dto.CityDto;

@Component
public class CityValidata {
	private static final String cityXmlName = "Cities.xml";
	private static final String districtsName = "Districts.xml";
	private static final String provincesName = "Provinces.xml";

	public ArrayList<CityDto> getCityByCode(String code, int type) throws DocumentException {
		String fileName = "";
		String parentIdAttr = "";
		switch (type) {
		case 0:
			fileName = provincesName;
			parentIdAttr = null;
			break;
		case 1:
			fileName = cityXmlName;
			parentIdAttr = "PID";
			break;
		case 2:
			fileName = districtsName;
			parentIdAttr = "CID";
			break;
		default:
			parentIdAttr = null;
			break;
		}
		return readXml(code, fileName, parentIdAttr);
	}

	// 解析xml
	private ArrayList<CityDto> readXml(String code, String fileName, String parentIdAttr) throws DocumentException {
		ArrayList<CityDto> citys = new ArrayList<>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		Element rootElement = document.getRootElement();
		int count = rootElement.nodeCount();
		for (int i = 0; i < count; i++) {
			Node node = rootElement.node(i);
			short nodeType = node.getNodeType();
			if (nodeType == Node.ELEMENT_NODE) {

				CityDto city = new CityDto();
				Element e = (Element) node;
				String text = e.getText();
				Attribute idAttr = e.attribute("ID");
				String id = idAttr.getText();
				city.setId(id);
				city.setCityName(text);
				if (parentIdAttr != null && !"".equals(parentIdAttr)) {
					Attribute pidAttr = e.attribute(parentIdAttr);
					String pid = pidAttr.getText();
					if (pid.equals(code)) {
						city.setPid(pid);
						citys.add(city);
					}
				} else {
					citys.add(city);
				}
			}
		}
		return citys;
	}
}
