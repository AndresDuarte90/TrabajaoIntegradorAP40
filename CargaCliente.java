package Supermercado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CargaCliente extends CargaGral<Cliente> {

	
	
    public CargaCliente(Connection connection) {
        super(Cliente.class, connection);
    }

    @Override
    protected Cliente mapToObject(ResultSet resultSet) throws SQLException {
        int DNI = resultSet.getInt("clienteDNI");
        String nombre = resultSet.getString("clienteNombre");
        String apellido = resultSet.getString("clienteApellido");
        String domicilio = resultSet.getString("clienteDomicilio");
        int telefono = resultSet.getInt("clienteTelefono");
        Cliente cliente = new Cliente(DNI, nombre, apellido, domicilio, telefono);
        return cliente;
    }

    public void insert(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (clienteNombre, clienteApellido, clienteDomicilio, clienteTelefono) VALUES (?, ?, ?, ?)";
        Object[] parameters = {cliente.getDNI(), cliente.getNombre(), cliente.getApellido(), cliente.getDomicilio(), cliente.getTelefono()};
        insert(query, parameters);
    }

    public void delete(int DNI) throws SQLException {
        String query = "DELETE FROM cliente WHERE clienteDNI = ?";
        Object[] parameters = {DNI};
        delete(query, parameters);
    }
}