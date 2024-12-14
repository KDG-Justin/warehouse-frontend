package be.kdg.sa.land.util;

import be.kdg.sa.land.domain.Seller;

import java.util.Random;

public class RandomSellerCreation {
    private static final String[] NAMES = {
            "John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis",
            "Diana Evans", "Frank Green", "Grace Hill", "Harry Jones", "Isabella King"
    };

    private static final String[] ADDRESSES = {
            "123 Main St", "456 Oak St", "789 Maple Ave", "101 Pine Rd", "202 Cedar Blvd",
            "303 Elm St", "404 Birch Ln", "505 Spruce Dr", "606 Walnut Pl", "707 Ash Ct"
    };


    public static Seller createRandomSeller(){
        Random random = new Random();
        int index = random.nextInt(10);

        return new Seller(NAMES[index], ADDRESSES[index]);
    }
}
