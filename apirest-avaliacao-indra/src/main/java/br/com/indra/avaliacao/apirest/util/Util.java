package br.com.indra.avaliacao.apirest.util;

public class Util  {

    public static boolean isNumber(String texto) {
        if(texto == null)
            return false;
        for (char letra : texto.toCharArray())
            if(letra < '0' || letra > '9')
                return false;
        return true;
         
    }
}
