package Supermercado;


public class Producto {
    private int productoID;
    private String productoNombre;
    private String productoDescripcion;
    private String productoFechaVenc;
    private double productoPrecio;

    public Producto(int productoID, String productoNombre, String productoDescripcion, String productoFechaVenc, double productoPrecio) {
        this.productoID = productoID;
        this.productoNombre = productoNombre;
        this.productoDescripcion = productoDescripcion;
        this.productoFechaVenc = productoFechaVenc;
        this.productoPrecio = productoPrecio;
    }

     public int getproductoID() {
        return productoID;
    }

    public void setproductoID(int productoID) {
        this.productoID = productoID;
    }

    public String getproductoNombre() {
        return productoNombre;
    }

    public void setproductoNombre(String nombre) {
        this.productoNombre = productoNombre;
    }

    public String getproductoDescripcion() {
        return productoDescripcion;
    }

    public void setproductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }

    public String getproductoFechaVenc() {
        return productoFechaVenc;
    }

    public void setproductoFechaVenc(String productoFechaVenc) {
        this.productoFechaVenc = productoFechaVenc;
    }

    public double getproductoPrecio() {
        return productoPrecio;
    }

    public void setproductoPrecio(int productoPrecio) {
        this.productoPrecio = productoPrecio;
    }
}