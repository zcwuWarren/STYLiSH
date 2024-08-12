package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.OrderDao;
import org.example.stylish.dto.ListDto;
import org.example.stylish.dto.OrderDto;
import org.example.stylish.dto.PaymentResponseDto;
import org.example.stylish.dto.RecipientDto;
import org.example.stylish.exception.ProductNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrderDetail(OrderDto orderDto) {
        String sql = "INSERT INTO orderDetail (shipping, payment, subtotal, freight, total) VALUES (:shipping, :payment, :subtotal, :freight, :total)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("shipping", orderDto.getShipping());
        param.addValue("payment", orderDto.getPayment());
        param.addValue("subtotal", orderDto.getSubtotal());
        param.addValue("freight", orderDto.getFreight());
        param.addValue("total", orderDto.getTotal());

        namedParameterJdbcTemplate.update(sql, param);
        // get order id
        String sqlQueryOrderId = "SELECT orderId FROM orderDetail LIMIT 1";
        MapSqlParameterSource orderIdParam = new MapSqlParameterSource();
        Integer orderId = namedParameterJdbcTemplate.queryForObject(sqlQueryOrderId, orderIdParam, Integer.class);

        return orderId;
    }

    @Override
    public void createRecipient(RecipientDto recipientDto) {
        String sql = "INSERT INTO recipient VALUES (:name, :phone, :email, :address, :time)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", recipientDto.getName());
        param.addValue("phone", recipientDto.getPhone());
        param.addValue("email", recipientDto.getEmail());
        param.addValue("address", recipientDto.getAddress());
        param.addValue("time", recipientDto.getTime());
        namedParameterJdbcTemplate.update(sql, param); // OK
    }

    @Override
    public void createListOfOrder(ListDto listDto) {
        //insert into listOfOrder
        String sql = "INSERT INTO listOfOrder VALUES (:productId, :name, :price, :size, :qty)";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productId", listDto.getId());
        param.addValue("name", listDto.getName());
        param.addValue("price", listDto.getPrice());
        param.addValue("size", listDto.getSize());
        param.addValue("qty", listDto.getQty());
        namedParameterJdbcTemplate.update(sql, param); // OK
    }

    @Override
    public void checkProductExistence(ListDto listDto) {
        String sql = "SELECT COUNT(*) FROM product WHERE id = :productId";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("productId", listDto.getId());
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, param, Integer.class);
//        System.out.println(listDto.getId());
//        System.out.println(count);

        if (count == 0) {
            throw new ProductNotExistException("Product does not exist");
        }
    }

    // update payment status in database
    @Override
    public void updatePaymentStatus(PaymentResponseDto paymentResponseDto) {
        String sql = "UPDATE orderDetail SET paymentStatus = :status";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("status", 0);
        namedParameterJdbcTemplate.update(sql, param);
    }
}
