<?php
/*
$client = new SoapClient("http://www.dneonline.com/calculator.asmx?WSDL");

// Obtener y mostrar las funciones del servicio web
$functions = $client->__getFunctions();
echo "<pre>";
print_r($functions);
echo "</pre>";

// Obtener y mostrar los tipos de datos del servicio web
$types = $client->__getTypes();
echo "<pre>";
print_r($types);
echo "</pre>";

try {
    $client = new SoapClient("http://www.dneonline.com/calculator.asmx?WSDL");
    
    // Realizar la suma
    $result = $client->Add(['intA' => 6, 'intB' => 8]);
    echo "Resultado de la suma: " . $result->AddResult;
} catch (SoapFault $fault) {
    echo "Error: " . $fault->faultcode . ", " . $fault->faultstring;
}
*/

// URL del WSDL del servicio SOAP
$wsdl = "http://www.dneonline.com/calculator.asmx?WSDL";

// Crear un cliente SOAP
$client = new SoapClient($wsdl);

try {
    // Definir los números para las operaciones
    $numA = 12;
    $numB = 8;

    // Probar la función Add (Suma)
    $addResult = $client->Add(['intA' => $numA, 'intB' => $numB]);
    echo "Suma: $numA + $numB = " . $addResult->AddResult . "<br>";

    // Probar la función Subtract (Resta)
    $subtractResult = $client->Subtract(['intA' => $numA, 'intB' => $numB]);
    echo "Resta: $numA - $numB = " . $subtractResult->SubtractResult . "<br>";

    // Probar la función Multiply (Multiplicación)
    $multiplyResult = $client->Multiply(['intA' => $numA, 'intB' => $numB]);
    echo "Multiplicación: $numA * $numB = " . $multiplyResult->MultiplyResult . "<br>";

    // Probar la función Divide (División)
    if ($numB != 0) {
        $divideResult = $client->Divide(['intA' => $numA, 'intB' => $numB]);
        echo "División: $numA / $numB = " . $divideResult->DivideResult . "<br>";
    } else {
        echo "No se puede dividir por cero.<br>";
    }

} catch (SoapFault $fault) {
    // Manejo de errores en caso de que algo falle en la llamada SOAP
    echo "Error: " . $fault->faultcode . ", " . $fault->faultstring . "<br>";
}

?>

