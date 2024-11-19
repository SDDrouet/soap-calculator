package org.example;

import javax.xml.soap.*;
import java.net.URL;

public class Main {
    private static final String ENDPOINT = "http://www.dneonline.com/calculator.asmx";
    private static final String NAMESPACE = "http://tempuri.org/";

    public static void main(String[] args) {
        try {
            int num1 = 10;
            int num2 = 5;

            // Probar todas las operaciones
            int suma = add(num1, num2);
            int resta = subtract(num1, num2);
            int multiplicacion = multiply(num1, num2);
            int division = divide(num1, num2);

            // Mostrar resultados
            System.out.println("Suma: " + num1 + " + " + num2 + " = " + suma);
            System.out.println("Resta: " + num1 + " - " + num2 + " = " + resta);
            System.out.println("Multiplicación: " + num1 + " × " + num2 + " = " + multiplicacion);
            System.out.println("División: " + num1 + " ÷ " + num2 + " = " + division);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSoapMessage(String operation, int num1, int num2) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", NAMESPACE);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement operationElement = soapBody.addChildElement(operation, "tem");

        SOAPElement intAElement = operationElement.addChildElement("intA", "tem");
        intAElement.addTextNode(String.valueOf(num1));

        SOAPElement intBElement = operationElement.addChildElement("intB", "tem");
        intBElement.addTextNode(String.valueOf(num2));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", NAMESPACE + operation);

        soapMessage.saveChanges();
        return soapMessage;
    }

    private static int executeOperation(String operation, int num1, int num2) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = soapConnectionFactory.createConnection();

        SOAPMessage soapMessage = createSoapMessage(operation, num1, num2);
        URL endpoint = new URL(ENDPOINT);
        SOAPMessage response = connection.call(soapMessage, endpoint);

        SOAPBody responseBody = response.getSOAPBody();
        SOAPElement responseElement = (SOAPElement) responseBody.getElementsByTagName(operation + "Response").item(0);
        SOAPElement resultElement = (SOAPElement) responseElement.getElementsByTagName(operation + "Result").item(0);

        connection.close();
        return Integer.parseInt(resultElement.getTextContent());
    }

    // Operaciones de la calculadora
    public static int add(int num1, int num2) throws Exception {
        return executeOperation("Add", num1, num2);
    }

    public static int subtract(int num1, int num2) throws Exception {
        return executeOperation("Subtract", num1, num2);
    }

    public static int multiply(int num1, int num2) throws Exception {
        return executeOperation("Multiply", num1, num2);
    }

    public static int divide(int num1, int num2) throws Exception {
        if (num2 == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero");
        }
        return executeOperation("Divide", num1, num2);
    }
}