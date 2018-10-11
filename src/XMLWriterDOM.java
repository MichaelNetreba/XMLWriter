


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class XMLWriterDOM {

        public static void main(String[] args) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();
                //add elements to Document
                Element rootElement = doc.createElement("Staff");
                //append root element to document
                doc.appendChild(rootElement);

                //append first child element to root element
                rootElement.appendChild(getEmployee(doc, "1", "Snow", "Jon", "35", "89051232323", "Ricardo"));

                //append second child
                rootElement.appendChild(getEmployee(doc, "2", "Ivanov", "Aleksander", "25", "89081231232", "Ricardo"));

                //append first manager
                rootElement.appendChild(getManager(doc,"1","Ricardo","Luka","45","89091234567","R&D"));
                //append second manager
                rootElement.appendChild(getManager(doc,"2","Croft","Malcolm","33","89101234567","DevOPS"));

                //for output to file, console
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                //
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                //write to console or file
                StreamResult console = new StreamResult(System.out);
                StreamResult file = new StreamResult(new File("c:/Stream/emps4.xml"));

                //write data
                transformer.transform(source, console);
                transformer.transform(source, file);
                System.out.println("DONE");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

                            // method create employees
        private static Node getEmployee(Document doc, String id, String lastName, String name, String age, String telephoneNumber,
                                        String manager) {
            Element employee = doc.createElement("Employee");

            //set id attribute
            employee.setAttribute("id", id);

            //create lastname element
            employee.appendChild(getEmployeeElements(doc, employee, "lastName", lastName));

            employee.appendChild(getEmployeeElements(doc, employee, "name", name));

            //create age element
            employee.appendChild(getEmployeeElements(doc, employee, "age", age));

            //create telephoneNumber element
            employee.appendChild(getEmployeeElements(doc, employee, "telephoneNumber", telephoneNumber));

            //create manager element
            employee.appendChild(getEmployeeElements(doc, employee, "manager", manager));

            return employee;
        }

private static Node getManager(Document doc, String id, String lastName, String name, String age, String telephoneNumber,
                               String departmentName) {
    Element manager = doc.createElement("Manager");
    //set id attribute
    manager.setAttribute("id",id);

    manager.appendChild(getEmployeeElements(doc,manager,"lastName",lastName));
    manager.appendChild(getEmployeeElements(doc,manager, "name", name));
    manager.appendChild(getEmployeeElements(doc,manager, "age",age));
    manager.appendChild(getEmployeeElements(doc,manager,"telephoneNumber",telephoneNumber));
    manager.appendChild(getEmployeeElements(doc,manager, "departmentName",departmentName));


    return manager;

        }


        //utility method to create text node
        private static Node getEmployeeElements(Document doc, Element element, String name, String value) {
            Element node = doc.createElement(name);
            node.appendChild(doc.createTextNode(value));
            return node;
        }


}


