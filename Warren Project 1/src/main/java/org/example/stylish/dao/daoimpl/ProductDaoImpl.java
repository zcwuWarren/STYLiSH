package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.ProductDao;
import org.example.stylish.dto.ProductListByKeyword;
import org.example.stylish.dto.ProductListResult;
import org.example.stylish.dto.VariantForJson;
import org.example.stylish.exception.InvalidDetailInputException;
import org.example.stylish.exception.ProductApiException;
import org.example.stylish.model.*;
import org.example.stylish.rowmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product create(int id, String category, String description, String title, int price, String texture, String wash, String place, String note, String story, String main_image) {

        String sql = "insert into product (id, category, description, title, price, texture, wash, place, note, story, main_image) values (:id, :category, :description, :title, :price, :texture, :wash, :place, :note, :story, :main_image)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("category", category);
        params.addValue("description", description);
        params.addValue("title", title);
        params.addValue("price", price);
        params.addValue("texture", texture);
        params.addValue("wash", wash);
        params.addValue("place", place);
        params.addValue("note", note);
        params.addValue("story", story);
        params.addValue("main_image", main_image);

        try {
            namedParameterJdbcTemplate.update(sql, params);
            Product product = new Product();
            product.setId(id);
            product.setCategory(category);
            product.setDescription(description);
            product.setTitle(title);
            product.setPrice(price);
            product.setTexture(texture);
            product.setWash(wash);
            product.setPlace(place);
            product.setNote(note);
            product.setStory(story);
            product.setMainImage(main_image);
            return product;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public ProductListResult getProductsByCategory(String category, int offset, int PAGE_SIZE) {

        String sql = "SELECT * FROM product " + (category.equals("all") ? "" : "WHERE category = :category") + " LIMIT :limit OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("category", category).addValue("offset", offset).addValue("limit", PAGE_SIZE);

        List<Product> products = namedParameterJdbcTemplate.query(sql, params, new ProdcutRowMapper());
        // assemble missing pieces into products
        for (Product product : products) {
            long id = product.getId();
            // insert colors
            product.setColors(getColorsByProductId(id));
            // insert sizes
            product.setSizes(getSizeNamesByProductId(id));
            // insert variants
            product.setVariants(getVariantsByProductId(id));
            // insert images
            product.setImages(getImagesByProductId(id));
        }

        String countSql = "SELECT count(*) FROM product " + (category.equals("all") ? "" : "WHERE category = :category");
        // calculate the count of products in a specific category
        int totalProductsByCategory = namedParameterJdbcTemplate.queryForObject(countSql, params, Integer.class);

        ProductListResult productListResult = new ProductListResult(products, totalProductsByCategory);

        return productListResult;

    }

    @Override
    public List<Color> getColorsByProductId(long id) {
        String sql = "SELECT * FROM color WHERE code IN (SELECT code FROM color_set WHERE id = :id)";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        List<Color> colors = namedParameterJdbcTemplate.query(sql, params, new ColorRowMapper());

        return colors;
    }

    @Override
    public List<String> getSizeNamesByProductId(long id) {
        String sql = "SELECT * FROM size WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        List<Size> sizes = namedParameterJdbcTemplate.query(sql, params, new SizeRowMapper());
        // due to size table contain two fields (id, size), and we only need size field,  use below method to extract and collect into a list
        List<String> sizeNames = sizes.stream().map(Size::getSize) // Extract the name from each Size object
                .toList(); // Collect the results into a List

        return sizeNames;
    }

    @Override
    public List<VariantForJson> getVariantsByProductId(long id) {
        String sql = "SELECT code, size, stock FROM variant WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        List<VariantForJson> variants = namedParameterJdbcTemplate.query(sql, params, new VariantForJsonRowMapper());
        return variants;
    }

    @Override
    public List<String> getImagesByProductId(long id) {
        String sql = "SELECT * FROM image WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        List<Image> images = namedParameterJdbcTemplate.query(sql, params, new ImageRowMapper());

        List<String> imageUrls = images.stream().map(Image::getImageUrl).toList();

        return imageUrls;
    }

    @Override
    public Product getProductDetailsByProductId(long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);

        List<Product> productDetailsByProductId = namedParameterJdbcTemplate.query(sql, params, new ProdcutRowMapper());

//        Returns the element at the specified position in this list.
        Product productDetailsResult = new Product();
        try {
            productDetailsResult = productDetailsByProductId.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ProductApiException("Product ID not found: " + id);
        } catch (RuntimeException e) {
            throw new InvalidDetailInputException("Internal server error");
        }

//        System.out.println(product);
        productDetailsResult.setColors(getColorsByProductId(id));
        productDetailsResult.setSizes(getSizeNamesByProductId(id));
        productDetailsResult.setVariants(getVariantsByProductId(id));
        productDetailsResult.setImages(getImagesByProductId(id));

        return productDetailsResult;
    }

    @Override // add isKeywordExist to check exception
    public Integer checkKeywordExists(String keyword) {
        String sql = "SELECT count(*) FROM product WHERE title LIKE CONCAT ('%',:keyword,'%')";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("keyword", keyword);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    @Override
    public ProductListByKeyword getProductCountByKeyword(String keyword, int offset, int PAGE_SIZE) {

        String sql = "SELECT * FROM product WHERE title LIKE CONCAT ('%',:keyword,'%')" + " LIMIT :limit OFFSET :offset"; //use LIKE operator to search for a specified pattern in a column
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("keyword", keyword)
                .addValue("offset", offset)
                .addValue("limit", PAGE_SIZE);

        List<ProductSearchResult> productSearchResults = namedParameterJdbcTemplate.query(sql, params, new ProductSearchRowMapper());
        for (ProductSearchResult productSearchResult : productSearchResults) {
            long id = productSearchResult.getId();
            productSearchResult.setColors(getColorsByProductId(id));
            productSearchResult.setSizes(getSizeNamesByProductId(id));
            productSearchResult.setVariants(getVariantsByProductId(id));
            productSearchResult.setImages(getImagesByProductId(id));
        }
        String countSql = "SELECT count(*) FROM product WHERE title LIKE CONCAT ('%',:keyword,'%')";
        int totalProductsByKeyword = namedParameterJdbcTemplate.queryForObject(countSql, params, Integer.class);
        ProductListByKeyword productListByKeyword = new ProductListByKeyword(productSearchResults, totalProductsByKeyword);
        System.out.println(totalProductsByKeyword);
        return productListByKeyword;
    }
}
