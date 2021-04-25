package com.kravchenko.hibernate;

import com.kravchenko.hibernate.services.CustomerProductService;
import com.kravchenko.hibernate.utils.HibernateUtil;
import org.springframework.stereotype.Component;

@Component
public class GetDataFromService {

    public GetDataFromService() {
    }

    public void getData () {
        try {
            CustomerProductService customerProductService = new CustomerProductService();
//            System.out.println(customerProductService.getCustomerByProduct(1L));
//            System.out.println(customerProductService.getProductsByCustomer(2L));
//            System.out.println(customerProductService.getAllCustomers());
//            System.out.println(customerProductService.getCustomer(1L));
            System.out.println(customerProductService.getProduct(1L));
        }
        finally {
            HibernateUtil.closeSessionFactory();
        }
    }
}
