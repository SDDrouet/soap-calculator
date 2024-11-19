from zeep import Client
from zeep.exceptions import Fault

# URL del WSDL del servicio SOAP
wsdl = "http://www.dneonline.com/calculator.asmx?WSDL"

# Crear un cliente SOAP usando zeep
client = Client(wsdl)

# Definir los números para las operaciones
numA = 12
numB = 8

try:
    # Probar la función Add (Suma)
    add_result = client.service.Add(intA=numA, intB=numB)
    print(f"Suma: {numA} + {numB} = {add_result}")

    # Probar la función Subtract (Resta)
    subtract_result = client.service.Subtract(intA=numA, intB=numB)
    print(f"Resta: {numA} - {numB} = {subtract_result}")

    # Probar la función Multiply (Multiplicación)
    multiply_result = client.service.Multiply(intA=numA, intB=numB)
    print(f"Multiplicación: {numA} * {numB} = {multiply_result}")

    # Probar la función Divide (División)
    if numB != 0:
        divide_result = client.service.Divide(intA=numA, intB=numB)
        print(f"División: {numA} / {numB} = {divide_result}")
    else:
        print("No se puede dividir por cero.")

except Fault as fault:
    # Manejo de errores en caso de que algo falle en la llamada SOAP
    print(f"Error: {fault.faultcode}, {fault.message}")
