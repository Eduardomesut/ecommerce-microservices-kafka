package com.hiberus.enviadorclientes.application;

import com.hiberus.enviadorclientes.domain.service.ClienteService;

public final class EliminarCliente {
    private EliminarCliente(){}
    public static void eliminar(String referencia, ClienteService clienteService){
        clienteService.eliminar(referencia);
    }
}
