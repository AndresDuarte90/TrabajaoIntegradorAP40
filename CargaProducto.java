package Supermercado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CargaProducto extends CargaGral<Producto> {

	
	
    public CargaProducto(Connection connection) {
        super(Producto.class, connection);
    }

    @Override
    protected Producto mapToObject(ResultSet resultSet) throws SQLException {
        int productoID = resultSet.getInt("productoID");
        String productoNombre = resultSet.getString("productoNombre");
        String productoDescripcion = resultSet.getString("productoDescripcion");
        String productoFechaVenc = resultSet.getString("productoFechaVenc");
        double productoPrecio = resultSet.getDouble("productoPrecio");
        Producto producto = new Producto(productoID, productoNombre, productoDescripcion, productoFechaVenc, productoPrecio);
        return producto;
    }

    public void insert(Producto producto) throws SQLException {
        String query = "INSERT INTO producto (productoID, productoNombre, productoDescripcion, productoFechaVenc, productoPrecio) VALUES (?, ?, ?, ?, ?)";
        Object[] parameters = {producto.getproductoID(), producto.getproductoNombre(), producto.getproductoDescripcion(), producto.getproductoFechaVenc(), producto.getproductoPrecio()};
        insert(query, parameters);
    }

    public void delete(int productoID) throws SQLException {
        String query = "DELETE FROM producto WHERE productoID = ?";
        Object[] parameters = {productoID};
        delete(query, parameters);
    }
}