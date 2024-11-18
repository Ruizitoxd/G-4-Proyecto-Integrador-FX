package datos;

import java.util.ArrayList;

public interface CRUD <T> {
    public ArrayList<T> Mostar(int id);
    public boolean Agregar(T objecto, Object... params);
    public boolean Editar(T objecto, Object... params);
    public boolean Eliminar(int id);
    T ObtenerPorId(String referencia);
}
