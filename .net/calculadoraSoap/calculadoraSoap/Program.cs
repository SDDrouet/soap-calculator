using ServiceReference1;
using System;

class Program
{
    static void Main(string[] args)
    {
        // Crear el cliente SOAP para la calculadora
        var calculatorService = new CalculatorSoapClient(CalculatorSoapClient.EndpointConfiguration.CalculatorSoap);

        // Definir los números para las operaciones
        int numA = 12;
        int numB = 8;

        try
        {
            // Realizar la operación de Suma
            int addResult = calculatorService.AddAsync(numA, numB).Result;
            Console.WriteLine($"Suma: {numA} + {numB} = {addResult}");

            // Realizar la operación de Resta
            int subtractResult = calculatorService.SubtractAsync(numA, numB).Result;
            Console.WriteLine($"Resta: {numA} - {numB} = {subtractResult}");

            // Realizar la operación de Multiplicación
            int multiplyResult = calculatorService.MultiplyAsync(numA, numB).Result;
            Console.WriteLine($"Multiplicación: {numA} * {numB} = {multiplyResult}");

            // Realizar la operación de División
            if (numB != 0)
            {
                int divideResult = calculatorService.DivideAsync(numA, numB).Result;
                Console.WriteLine($"División: {numA} / {numB} = {divideResult}");
            }
            else
            {
                Console.WriteLine("No se puede dividir por cero.");
            }
        }
        catch (SoapException soapEx)
        {
            // Manejo de errores en caso de que algo falle en la llamada SOAP
            Console.WriteLine($"Error SOAP: {soapEx.Message}");
        }
        catch (Exception ex)
        {
            // Manejo de errores generales
            Console.WriteLine($"Error: {ex.Message}");
        }

        Console.ReadLine();
    }
}

[Serializable]
internal class SoapException : Exception
{
    public SoapException()
    {
    }

    public SoapException(string? message) : base(message)
    {
    }

    public SoapException(string? message, Exception? innerException) : base(message, innerException)
    {
    }
}