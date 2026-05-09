package com.example.codigosms_boleta.model;


public enum EstadoCompra {
    PENDIENTE,    // La compra se ha registrado pero no se ha pagado
    PROCESANDO,   // El pago está en validación
    COMPLETADA,   // Pago confirmado y boleta generada
    CANCELADA,    // El usuario canceló o el pago falló
    REEMBOLSADA   // La compra fue devuelta
}

