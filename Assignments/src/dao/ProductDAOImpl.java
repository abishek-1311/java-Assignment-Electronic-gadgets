package dao;

import dao.ProductDAO;
import entity.Product;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Connection con;

    public ProductDAOImpl() {
        try {
            con = DBConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Connection Failed: " + e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (ProductID, ProductName, Description, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.executeUpdate();
            System.out.println("Product added successfully.");
        } catch (SQLException e) {
            System.err.println("Add Product Failed: " + e.getMessage());
        }
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );
            }
        } catch (SQLException e) {
            System.err.println("Get Product Failed: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Fetch Products Failed: " + e.getMessage());
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET Description = ?, Price = ? WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getDescription());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getProductId());
            ps.executeUpdate();
            System.out.println("Product updated.");
        } catch (SQLException e) {
            System.err.println("Update Product Failed: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Product deleted.");
        } catch (SQLException e) {
            System.err.println("Delete Product Failed: " + e.getMessage());
        }
    }
}
