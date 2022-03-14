package com.portafolio.portafoliobackend.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonsUtil {

	public static boolean isNullOrZeroLong(Long valor) {

		boolean rpta = false;

		if (valor != null && valor != 0L) {
			rpta = true;
		}

		return rpta;
	}

	/**
	 * Permite convertir una fecha a cada de acuerdo con el formato especificado.
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String convertirFechaACadena(Date fecha, String formato) {
		if (fecha == null) {
			return "";
		}

		SimpleDateFormat format = new SimpleDateFormat(formato);
		return format.format(fecha);
	}

	/**
	 * Permite obetner los valores de un XML.
	 * 
	 * @param fecha
	 * @param formato
	 * @return
	 */
	public static String getTagValue(String tag, Element element) {
		if (element.getElementsByTagName(tag).item(0) == null) {
			return "";
		}
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public static String getTagValuePrefijo(String prefijo, String tag, Element element) {
		if (element.getElementsByTagName(prefijo + tag).item(0) == null) {
			return "";
		}
		NodeList nodeList = element.getElementsByTagName(prefijo + tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		if (node != null) {
			return node.getNodeValue().trim();
		} else
			return "";
	}

	public static String getNodeValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public static String getLimpiarString(String tag) {
		tag = tag.replace("&lt;p&gt;", "");
		tag = tag.replace("&lt;br /&gt;", "");
		tag = tag.replace("&#xD;", "");
		tag = tag.replace("&lt;/p&gt;", "");
		return tag;
	}

	public static Document convertStringToXMLDocument(StringBuilder xmlString) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		builder = factory.newDocumentBuilder();
		xmlString.trimToSize();
		List<String> xmlSecString = new ArrayList<String>();
		int xmlLenthg = xmlString.length();
		int xmlSec = (int) (xmlLenthg / 5000);
		int xmlLenthgAdd = 0;
		if (xmlSec == 0) {
			xmlSecString.add(xmlString.substring(0, xmlLenthg));
		} else {
			for (int i = 0; i < xmlSec; i++) {
				if (i == 0) {
					xmlSecString.add(xmlString.substring(i * 5000, 5000 * (i + 1)));
				} else {
					xmlSecString.add(xmlString.substring((i * 5000), 5000 * (i + 1)));
				}
				xmlLenthgAdd += 5000;
			}
		}

		if ((xmlLenthg - xmlLenthgAdd) > 0) {
			xmlSecString.add(xmlString.substring((xmlLenthgAdd), xmlLenthg));
		}

		Document doc = null;

		// Modificado por GDC - 10/06/2021 - INICIO
		String cadena = "";
		for (int i = 0; i <= xmlSec; i++) {
			cadena = cadena + xmlSecString.get(i);
		}

		if (cadena.length() > 0) {
			doc = builder.parse(new InputSource(new StringReader(cadena)));
		}
		// Modificado por GDC - 10/06/2021 - FIN

		/*
		 * BOQUE DE CÓDIGO COMENTADO POR GDC - 10/06/2021
		 * 
		 * switch (xmlSec) {
		 * case 0:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0))));
		 * break;
		 * case 1:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1))));
		 * break;
		 * case 2:
		 * doc = builder.parse(new InputSource(
		 * new StringReader(xmlSecString.get(0) + xmlSecString.get(1) +
		 * xmlSecString.get(2))));
		 * break;
		 * case 3:
		 * doc = builder.parse(new InputSource(new StringReader(
		 * xmlSecString.get(0) + xmlSecString.get(1) + xmlSecString.get(2) +
		 * xmlSecString.get(3))));
		 * break;
		 * case 4:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4))));
		 * break;
		 * case 5:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5))));
		 * break;
		 * case 6:
		 * doc = builder.parse(new InputSource(new StringReader(
		 * xmlSecString.get(0) + xmlSecString.get(1) + xmlSecString.get(2) +
		 * xmlSecString.get(3)
		 * + xmlSecString.get(4) + xmlSecString.get(5) + xmlSecString.get(6))));
		 * break;
		 * case 7:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7))));
		 * break;
		 * case 8:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8))));
		 * break;
		 * case 9:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9))));
		 * break;
		 * case 10:
		 * doc = builder.parse(new InputSource(new StringReader(
		 * xmlSecString.get(0) + xmlSecString.get(1) + xmlSecString.get(2) +
		 * xmlSecString.get(3)
		 * + xmlSecString.get(4) + xmlSecString.get(5) + xmlSecString.get(6) +
		 * xmlSecString.get(7)
		 * + xmlSecString.get(8) + xmlSecString.get(9) + xmlSecString.get(10))));
		 * break;
		 * case 11:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11))));
		 * break;
		 * case 12:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12))));
		 * break;
		 * case 13:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13))));
		 * break;
		 * case 14:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14))));
		 * break;
		 * case 15:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15))));
		 * break;
		 * case 16:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15) + xmlSecString.get(16))));
		 * break;
		 * case 17:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15) + xmlSecString.get(16) +
		 * xmlSecString.get(17))));
		 * break;
		 * case 18:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15) + xmlSecString.get(16) +
		 * xmlSecString.get(17)
		 * + xmlSecString.get(18))));
		 * break;
		 * case 19:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15) + xmlSecString.get(16) +
		 * xmlSecString.get(17)
		 * + xmlSecString.get(18) + xmlSecString.get(19))));
		 * break;
		 * case 20:
		 * doc = builder.parse(new InputSource(new StringReader(xmlSecString.get(0) +
		 * xmlSecString.get(1)
		 * + xmlSecString.get(2) + xmlSecString.get(3) + xmlSecString.get(4) +
		 * xmlSecString.get(5)
		 * + xmlSecString.get(6) + xmlSecString.get(7) + xmlSecString.get(8) +
		 * xmlSecString.get(9)
		 * + xmlSecString.get(10) + xmlSecString.get(11) + xmlSecString.get(12) +
		 * xmlSecString.get(13)
		 * + xmlSecString.get(14) + xmlSecString.get(15) + xmlSecString.get(16) +
		 * xmlSecString.get(17)
		 * + xmlSecString.get(18) + xmlSecString.get(19) + xmlSecString.get(20))));
		 * break;
		 * default:
		 * break;
		 * }
		 */

		// Document doc = builder.parse(new InputSource(new
		// StringReader(xmlString.toString())));

		return doc;
	}

	public static String getResultCode(Document document, String tagParent, String tagResult) {
		String resultCode = null;
		NodeList nodeList = document.getElementsByTagName(tagParent);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(0);
				resultCode = CommonsUtil.getTagValue("resultCode", element);
			}
		}

		return resultCode;
	}

	public static String getValue2Tag(Document document, String tagParent, String tagResult) {
		String resultCode = null;
		NodeList nodeList = document.getElementsByTagName(tagParent);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodeList.item(0);
				resultCode = CommonsUtil.getTagValue(tagResult, element);
			}
		}

		return resultCode;
	}

	public static Object getXML2Object(Node node, String claseXMLPadre, String claseXMLHijo, String nodoHijo,
			String methodsOff, Element element, List<Object> lista) throws Exception, IllegalAccessException {
		Class aClass;
		Object abean = null;
		Method method;
		Method methodAdd = null;
		aClass = Class.forName(claseXMLPadre);
		abean = aClass.newInstance();

		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			if (method.getName().substring(0, 3).contains("set") && !method.getName().equals(methodsOff)) {
				method.invoke(abean, CommonsUtil.getTagValue(nameMethod, element));
			}

			if (method.getName().equals(methodsOff)) {
				methodAdd = method;
			}

		}
		for (int i = 0; i < element.getChildNodes().getLength(); ++i) {
			Node nodeHijo = element.getChildNodes().item(i);
			if (nodeHijo.getNodeName().equals(nodoHijo)) {
				for (int j = 0; j < nodeHijo.getChildNodes().getLength(); ++j) {
					Node nodeHijoItem = nodeHijo.getChildNodes().item(j);
					if (nodeHijoItem.getNodeType() == Node.ELEMENT_NODE) {
						Element elementHijo = (Element) nodeHijoItem;
						lista.add(
								CommonsUtil.getXML2Object(nodeHijoItem, claseXMLHijo, "", "", "", elementHijo, lista));
					}
				}
				try {
					methodAdd.invoke(abean, lista);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}

		return abean;
	}

	public static Object getAtributos2Object(String claseResultado, String methodsOff, Object element)
			throws Exception {
		Class aClass;
		Object abean = null;
		Method method;
		aClass = Class.forName(claseResultado);
		abean = aClass.newInstance();

		String[] methods = methodsOff.split("-");
		String methodsOff0 = "";
		String methodsOff1 = "";

		if (methods.length == 1) {
			methodsOff0 = methods[0];
		} else {
			methodsOff0 = methods[0];
			methodsOff1 = methods[1];
		}

		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			if (method.getName().substring(0, 3).contains("set")
					&& (!method.getName().equals(methodsOff0) && !method.getName().equals(methodsOff1))) {
				method.invoke(abean, getValue2Object(method, element).toString());
			}
		}
		return abean;
	}

	public static Object getValue2Object(Method method, Object element) throws Exception {
		Object value = "";
		String methodName = method.getName();
		String methodNameFin = methodName.replaceFirst("s", "g");
		Class aClass = null;
		aClass = element.getClass();
		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			if (method.getName().equals(methodNameFin)) {
				value = method.invoke(element);
			}
		}
		return value;
	}

	public static Object getXML2ObjectPrefijo(String prefijo, Node node, String claseXMLPadre, String claseXMLHijo,
			String nodoHijo,
			String methodsOff, Element element, List<Object> lista) throws Exception {
		Class aClass;
		Object abean = null;
		Method method;
		Method methodAdd = null;
		aClass = Class.forName(claseXMLPadre);
		abean = aClass.newInstance();

		Method[] userMethods = aClass.getMethods();
		for (int i = 0; i < userMethods.length; i++) {
			method = userMethods[i];
			String nameMethod = method.getName();
			int lengthNameMethod = method.getName().length();
			nameMethod = nameMethod.substring(3, 4).toLowerCase() + nameMethod.substring(4, lengthNameMethod);
			if (method.getName().substring(0, 3).contains("set") && !method.getName().equals(methodsOff)) {
				method.invoke(abean, CommonsUtil.getTagValuePrefijo(prefijo, nameMethod, element));
			}

			if (method.getName().equals(methodsOff)) {
				methodAdd = method;
			}

		}

		for (int i = 0; i < element.getChildNodes().getLength(); ++i) {
			Node nodeHijo = element.getChildNodes().item(i);
			if (nodeHijo.getNodeName().equals(nodoHijo)) {
				for (int j = 0; j < nodeHijo.getChildNodes().getLength(); ++j) {
					Node nodeHijoItem = nodeHijo.getChildNodes().item(j);
					if (nodeHijoItem.getNodeType() == Node.ELEMENT_NODE) {
						Element elementHijo = (Element) nodeHijoItem;
						lista.add(
								CommonsUtil.getXML2Object(nodeHijoItem, claseXMLHijo, "", "", "", elementHijo, lista));
					}
				}
				methodAdd.invoke(abean, lista);
			}
		}
		return abean;
	}

	public String stringToDom(String xmlSource, String name, String carpetaTmp) throws Exception {
		String pathFile = "";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		pathFile = carpetaTmp + name + ".xml";

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(pathFile));
		transformer.transform(source, result);

		return pathFile;
	}

	public static String generadorNombreUnico() {
		Calendar cal = Calendar.getInstance();
		String nombre = String.valueOf(cal.get(Calendar.YEAR))
				+ String.valueOf(cal.get(Calendar.MONTH) + 1) + String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
				+ "-" + String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + String.valueOf(cal.get(Calendar.MINUTE))
				+ String.valueOf(cal.get(Calendar.SECOND)) + String.valueOf(cal.get(Calendar.MILLISECOND));
		return nombre;
	}

	public static PrintWriter addFileToService(String fieldName, File uploadFile, PrintWriter writer,
			OutputStream outputStream, String boundary) throws IOException {
		String LINE_FEED = "\r\n";
		String fileName = uploadFile.getName();
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED);
		writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.flush();

		FileInputStream inputStream = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}
		outputStream.flush();
		inputStream.close();

		writer.append(LINE_FEED);
		writer.flush();

		return writer;
	}

	/**
	 * Permite obtener la extensión del nombre del archivo.
	 * 
	 * @param nombreArchivo
	 * @return
	 */
	public static String obtenerExtensionNombreArchivo(String nombreArchivo) {
		String extension = "";

		int i = nombreArchivo.lastIndexOf('.');
		if (i > 0) {
			extension = nombreArchivo.substring(i + 1);
		}

		return extension;
	}

	/**
	 * Permite obtener los días calendario entre dos fechas.
	 * 
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int numeroDiasEntreDosFechas(Date fecha1, Date fecha2) {
		long startTime = fecha1.getTime();
		long endTime = fecha2.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return (int) diffDays;
	}

	/**
	 * Permite obtener los días calendario entre dos fechas, por ello cuenta
	 * inclusive el último día.
	 * 
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int plazoEnDiasEntreDosFechas(Date fecha1, Date fecha2) {
		long startTime = fecha1.getTime();
		long endTime = fecha2.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
		return (int) diffDays + 1;
	}

	/**
	 * Permite obtener los meses entre dos fechas.
	 * 
	 * @param fechas1
	 * @param fechas2
	 * @return
	 */
	public static int numeroMesesEntreDosFechas(Date fecha1, Date fecha2) {
		long startTime = fecha1.getTime();
		long endTime = fecha2.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24 * 30);
		return (int) diffDays;
	}

	/**
	 * Permite obtener la fecgha a partir de una cadena de formato dd/MM/yyyy.
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date Strig2Date(String strDate) throws Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(strDate);
		return date;
	}

	public static String setListToString(Set<String> lista) {
		String salidaStr = "";
		for (String str : lista) {
			if (salidaStr.equals("")) {
				salidaStr = str;
			} else {
				salidaStr = salidaStr + ", " + str;
			}
		}
		return salidaStr;
	}

	/**
	 * Permite reemplazar las tildes y otros caracteres especiales en textos
	 * para evitar nombre inadecuados en, por ejemplo, los documentos enviados
	 * al Siges.
	 * 
	 * @param cadena
	 * @return
	 */
	public static String removerCaracteresEspeciales(String cadena) {
		// Cadena de caracteres original a sustituir.
		String orgnl = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ'";
		// Cadena de caracteres ASCII que reemplazarán los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC_";
		String output = cadena;
		for (int i = 0; i < orgnl.length(); i++) {
			// Reemplazamos los caracteres especiales.
			output = output.replace(orgnl.charAt(i), ascii.charAt(i));
		} // for i
		return output;
	}

	/**
	 * Permite validar si una cadena de texto es un número
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean esUnNumero(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			log.error(nfe.getMessage());
			return false;
		}

	}

	public static String convertStandardJSONString(String data_json) {
		if (data_json.contains("'")) {
			data_json = data_json.replaceAll("'", "\'");
		}

		return data_json;
	}

	public static String validarNull(String valor) {
		String cadenaSalida = "";
		if (valor != null) {
			cadenaSalida = valor;
		}

		return cadenaSalida;
	}
}
