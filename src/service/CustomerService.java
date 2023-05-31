package service;

import api.HotelResource;
import model.Coustomer;

import java.util.*;

public class CustomerService {

    private static CustomerService INSTANCE;

    public static CustomerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomerService();
        }

        return INSTANCE;
    }

    private CustomerService() {
    }


    public static void addCustomer (String customerEmail, String firstName, String lastname){
        if(HotelResource.coustmers.containsKey(customerEmail)){
            System.out.println("Email already exists");
        }else
            HotelResource.coustmers.put(customerEmail, new Coustomer(firstName, lastname, customerEmail));

    }
    public static Coustomer getCustomer(String customerEmail){
        return HotelResource.coustmers.get(customerEmail);
    }

    public static Collection<Coustomer> getAllCustomers(){
        return HotelResource.coustmers.values();
    }
}
