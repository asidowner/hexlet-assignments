package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {
    // BEGIN
    private static final String SAVE_TEMPLATE = "INSERT INTO products (title, price) VALUES (?, ?);";
    private static final String FETCH_ONE_TEMPLATE = "SELECT id, title, price FROM products WHERE id = ?;";
    private static final String FETCH_ALL_TEMPLATE = "SELECT id, title, price FROM products;";

    public static void save(Product product) throws SQLException {
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(SAVE_TEMPLATE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<Product> getEntities() throws SQLException {
        try (var conn = dataSource.getConnection();
             var prepareStatement = conn.prepareStatement(FETCH_ALL_TEMPLATE)) {
            var resultSet = prepareStatement.executeQuery();
            var result = new ArrayList<Product>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");

                var product = new Product(title, price);
                product.setId(id);
                result.add(product);

            }
            return result;
        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        try (var conn = dataSource.getConnection();
             var prepareStatement = conn.prepareStatement(FETCH_ONE_TEMPLATE)) {
            prepareStatement.setLong(1, id);
            var resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                var title = resultSet.getString("title");
                var price = resultSet.getInt("price");

                var product = new Product(title, price);
                product.setId(id);

                return Optional.of(product);
            }
            return Optional.empty();
        }

    }
    // END
}
