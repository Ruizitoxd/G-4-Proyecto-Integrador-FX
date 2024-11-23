package controlador;

import modelo.RolUsuario;

public interface Validar {
    RolUsuario validar(String correo, String identificacion);
}
