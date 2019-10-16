//package xmlparse;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jdom2.Attribute;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//
///**
// * 用JDOM方式读取xml文件
// *
// * @author lune
// */
//public class JdomParse {
//
//	private List<Book> books = null;
//
//	public void createJDOM(File file) {
//		try {
//			// 创建一个根节点
//			Element rootElement = new Element("root");
//			Document doc = new Document(rootElement);
//
//			// 在根节点下创建第一个子节点
//			Element rootOneElement = new Element("person");
//			rootOneElement.setAttribute(new Attribute("attr", "root one"));
//
//			// 在第一个子节点下创建第一个子节点
//			Element childOneElement = new Element("people");
//			childOneElement.setAttribute(new Attribute("attr", "child one"));
//			childOneElement.setText("person child one");
//
//			// 在第一个子节点下创建第二个子节点
//			Element childTwoElement = new Element("people");
//			childTwoElement.setAttribute(new Attribute("attr", "child two"));
//			childTwoElement.setText("person child two");
//
//			// 在根节点下创建第二个子节点
//			Element rootTwoElement = new Element("person");
//			rootTwoElement.setAttribute(new Attribute("attr", "root two"));
//
//			// 在第一个子节点下创建第一个子节点
//			Element oneChildOneElement = new Element("people");
//			oneChildOneElement.setAttribute(new Attribute("attr", "child one"));
//			oneChildOneElement.setText("person child one");
//
//			// 在第一个子节点下创建第二个子节点
//			Element twoChildTwoElement = new Element("people");
//			twoChildTwoElement.setAttribute(new Attribute("attr", "child two"));
//			twoChildTwoElement.setText("person child two");
//
//			rootOneElement.addContent(childOneElement);
//			rootOneElement.addContent(childTwoElement);
//
//			rootTwoElement.addContent(oneChildOneElement);
//			rootTwoElement.addContent(twoChildTwoElement);
//
//			doc.getRootElement().addContent(rootOneElement);
//			doc.getRootElement().addContent(rootTwoElement);
//
//			// 创建xml输出流操作类
//			XMLOutputter xmlOutput = new XMLOutputter();
//
//			// 格式化xml内容
//			xmlOutput.setFormat(Format.getPrettyFormat());
//			// 把xml输出到指定位置
//			xmlOutput.output(doc, new FileOutputStream(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public List<Book> getBooks(String fileName) {
//		SAXBuilder saxBuilder = new SAXBuilder();
//		Book book = null;
//
//		try {
//			Document document = saxBuilder.build(new FileInputStream(fileName));
//			// 获取根节点bookstore
//			Element rootElement = document.getRootElement();
//			// 获取根节点的子节点，返回子节点的数组
//			List<Element> bookList = rootElement.getChildren();
//			books = new ArrayList<Book>();
//			for (Element bookElement : bookList) {
//				book = new Book();
//				// 获取bookElement的属性
//				List<Attribute> bookAttributes = bookElement.getAttributes();
//				for (Attribute attribute : bookAttributes) {
//					if (attribute.getName().equals("id")) {
//						String id = attribute.getValue(); // System.out.println(id);
//						book.setId(Integer.parseInt(id));
//					}
//				}
//				// 获取bookElement的子节点
//				List<Element> children = bookElement.getChildren();
//				for (Element child : children) {
//					if (child.getName().equals("name")) {
//						String name = child.getValue();// System.out.println(name);
//						book.setName(name);
//					} else if (child.getName().equals("author")) {
//						String author = child.getValue();
//						book.setAuthor(author);// System.out.println(author);
//					} else if (child.getName().equals("year")) {
//						String year = child.getValue();
//						book.setYear(Integer.parseInt(year));
//					} else if (child.getName().equals("price")) {
//						String price = child.getValue();
//						book.setPrice(Double.parseDouble(price));
//					}
//				}
//
//				books.add(book);
//				book = null;
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (JDOMException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return books;
//	}
//
//	public static void main(String[] args) {
//		String fileName = "src/main/resources/xml/books.xml";
//		List<Book> books = new JdomParse().getBooks(fileName);
//		for (Book book : books) {
//			System.out.println(book);
//		}
//	}// end main
//}
