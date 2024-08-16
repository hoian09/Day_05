package com.ivt.spring.jpa.mainrun;

import com.ivt.spring.jpa.config.JPAConfig;
import com.ivt.spring.jpa.entity.BookEntity;
import com.ivt.spring.jpa.entity.OrderDetailsEntity;
import com.ivt.spring.jpa.entity.OrdersEntity;
import com.ivt.spring.jpa.repository.OrdersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

public class MainOrders {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static OrdersRepository ordersRepository = (OrdersRepository) context.getBean("ordersRepository");
    public static void main(String[] args) {
        createOrderDetail();
        showListOrder();
        getOrderAndOrderDetailById(3);
        getOrderByCurDate();
        listOrdersWithTotalAmountMoreThan1000USD();
        listOrdersBuyJavaBook();
    }
    public static void createOrderDetail(){
        OrderDetailsEntity orderDetail = new OrderDetailsEntity();

        orderDetail.setQuantity(5);
        orderDetail.setUnitPrice(1200);
        orderDetail.setProductName("JavaBook");


        List<OrderDetailsEntity> orderDetails = new ArrayList<>();

        orderDetails.add(orderDetail);


        OrdersEntity order = new OrdersEntity();
        order.setOrderDate(LocalDate.parse("2024-08-15"));
        order.setCustomerName("Luu Quoc");
        order.setCustomerAddress("HA,QN");
        order.setOrderDetails(orderDetails);
        orderDetail.setOrders(order);

        ordersRepository.save(order);
    }
    public static void showListOrder() {
        List<OrdersEntity> orderList = (List<OrdersEntity>) ordersRepository.findAll();

        for (OrdersEntity orders : orderList) {
            System.out.println("Order ID: " + orders.getId());
            System.out.println("Order Date: " + orders.getOrderDate());
            System.out.println("Customer Name: " + orders.getCustomerName());
            System.out.println("Customer Address: " + orders.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetailsEntity detail : orders.getOrderDetails()) {
                System.out.println("Product Name: " + detail.getProductName());
                System.out.println("Quantity: " + detail.getQuantity());
                System.out.println("Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("=============");
        }
    }
    public static void getOrderAndOrderDetailById(int orderId){
        List<OrdersEntity> orderList = (List<OrdersEntity>) ordersRepository.findAll();
        for (OrdersEntity orders : orderList) {
            if(orderId == orders.getId()){
                System.out.println("Order ID: " + orders.getId());
                System.out.println("Order Date: " + orders.getOrderDate());
                System.out.println("Customer Name: " + orders.getCustomerName());
                System.out.println("Customer Address: " + orders.getCustomerAddress());

                System.out.println("Order Details:");
                for (OrderDetailsEntity detail : orders.getOrderDetails()) {
                    System.out.println("Product Name: " + detail.getProductName());
                    System.out.println("Quantity: " + detail.getQuantity());
                    System.out.println("Unit Price: " + detail.getUnitPrice());
                }
                System.out.println("=============");
            }
        }
    }
    public static void getOrderByCurDate(){
        List<OrdersEntity> orderList = ordersRepository.getOrderByCurrentDate();
        for (OrdersEntity order : orderList) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetailsEntity detail : order.getOrderDetails()) {
                System.out.println("Product Name: " + detail.getProductName());
                System.out.println("Quantity: " + detail.getQuantity());
                System.out.println("Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("=============");
        }
    }
    public static void listOrdersWithTotalAmountMoreThan1000USD() {
        List<OrdersEntity> orderList = ordersRepository.listOrdersWithTotalAmountMoreThan1000();
        double total = 0;
        for (OrdersEntity order : orderList) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetailsEntity detail : order.getOrderDetails()) {
                System.out.println(" - Product Name: " + detail.getProductName());
                System.out.println("   Quantity: " + detail.getQuantity());
                System.out.println("   Unit Price: " + detail.getUnitPrice());
                total = detail.getQuantity() * detail.getUnitPrice();
                System.out.println("Total: " + total);
            }
            System.out.println("=============");
        }
    }
    public static void  listOrdersBuyJavaBook(){
        List<OrdersEntity> orderList = (List<OrdersEntity>) ordersRepository.getOrdersWithProductNameJavaBook();
        for (OrdersEntity order : orderList) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Customer Address: " + order.getCustomerAddress());

            System.out.println("Order Details:");
            for (OrderDetailsEntity detail : order.getOrderDetails()) {
                System.out.println("Product Name: " + detail.getProductName());
                System.out.println("Quantity: " + detail.getQuantity());
                System.out.println("Unit Price: " + detail.getUnitPrice());
            }
            System.out.println("=============");
        }
    }

}

