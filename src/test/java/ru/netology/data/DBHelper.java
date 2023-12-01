package ru.netology.data;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DBHelper {

    private static final QueryRunner runner = new QueryRunner();
    private static final Gson gson = new Gson();
    private static final RequestSpecification spec = new RequestSpecBuilder().setBaseUri("http://185.119.57.64").setPort(9999)
            .setAccept(ContentType.JSON).setContentType(ContentType.JSON).log(LogDetail.ALL).build();

    private DBHelper() {
    }

    public static void getBody(DataHelper.CardInfo cardInfo, String url, int statusCode) {
        var body = gson.toJson(cardInfo);
        given().spec(spec).body(body)
                .when().post(url)
                .then().statusCode(statusCode);
    }



    @SneakyThrows
    public static Connection getConn() {
        return DriverManager.getConnection("jdbc:postgresql://185.119.57.64:5432/app", "app",
                "pass");

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentEntity {
        private String id;
        private int amount;
        private Timestamp created;
        private String status;
        private String transaction_id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditRequestEntity {
        private String id;
        private String bank_id;
        private Timestamp created;
        private String status;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderEntity {
        private String id;
        private Timestamp created;
        private String credit_id;
        private String payment_id;
    }

    @SneakyThrows
    public static List<PaymentEntity> getPayments() {
        getConn();
        var dbQuery = "SELECT * FROM payment_entity;";
        ResultSetHandler<List<PaymentEntity>> resultHandler = new BeanListHandler<>(PaymentEntity.class);
        return runner.query(getConn(), dbQuery, resultHandler);
    }

    @SneakyThrows
    public static List<CreditRequestEntity> getCreditRequests() {
        getConn();
        var dbQuery = "SELECT * FROM credit_request_entity;";
        ResultSetHandler<List<CreditRequestEntity>> resultHandler = new BeanListHandler<>(CreditRequestEntity.class);
        return runner.query(getConn(), dbQuery, resultHandler);
    }

    @SneakyThrows
    public static List<OrderEntity> getOrders() {
        getConn();
        var dbQuery = "SELECT * FROM order_entity;";
        ResultSetHandler<List<OrderEntity>> resultHandler = new BeanListHandler<>(OrderEntity.class);
        return runner.query(getConn(), dbQuery, resultHandler);
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        getConn();
        var dbStatus = "SELECT status FROM payment_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditStatus() {
        getConn();
        var dbStatus = "SELECT status FROM credit_request_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentID() {
        getConn();
        var dbStatus = "SELECT payment_id FROM order_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditID() {
        getConn();
        var dbStatus = "SELECT credit_id FROM order_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getBankIDForPayment() {
        getConn();
        var dbStatus = "SELECT bank_id FROM payment_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getBankIDForCredit() {
        getConn();
        var dbStatus = "SELECT bank_id FROM credit_request_entity;";
        return runner.query(getConn(), dbStatus, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanDatabase() {
        Connection conn;
        conn = getConn();
        runner.execute(conn,"DELETE FROM credit_request_entity;");
        runner.execute(conn, "DELETE FROM payment_entity;");
        runner.execute(conn, "DELETE FROM order_entity;");
    }
}
