package com.cursomvc.springprojeto.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class CaminhoRequestUtil {
	
	// Construtor privado para evitar instanciamento
    private CaminhoRequestUtil() {
        throw new AssertionError("Não é permitido instanciar a classe CaminhoRequestUtil");
    }
    
    public static String caminho() {
    	ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String caminho = attrs.getRequest().getServletPath();
        return caminho;
    }
}
