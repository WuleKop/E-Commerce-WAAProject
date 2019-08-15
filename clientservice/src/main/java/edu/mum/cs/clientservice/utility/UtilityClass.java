package edu.mum.cs.clientservice.utility;

import edu.mum.cs.clientservice.adminmodel.User;
import edu.mum.cs.clientservice.sellermodel.ProductOrder;
import org.springframework.stereotype.Component;

import java.util.List;

public class UtilityClass {

    public String choosenOne(String wholeUrl){
        return wholeUrl.split("\n")[0];
    }

    public Double totalAmount(Double amount,Integer quantity){
        return  (amount* quantity);
    }

    public static Double subTotal(List<ProductOrder> productOrderList){
        Double total = 0.0;
        for(ProductOrder productOrder:productOrderList){
          total += (productOrder.getProduct().getPrice() * productOrder.getQuantity());
        }
        return  total;
    }

    public static Integer totalQuantity(List<ProductOrder> orders){
        return  orders.stream().mapToInt(x-> x.getQuantity()).sum();
    }

    public static void sendingEmailMessage(User user){
        String m = "Hello <strong>" + user.getName() + " " + user.getLastName()+" <br/>"
                + "</strong>, we would like to inform you that <strong> you are successfully registered"
                + "</strong>  <br/> you will have to wait for our ADMIN to approve your account,<br/>"
                +"Don't worry this will not delay";
        String subject = "Message from " + "Maharishi University ecommerce System";

        try {
            EmailSender.registrationConfirmation(user.getEmail(), subject, Template.sendingEmail(m));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendingEmailApprove(User user){
        String m = "Hello <strong>" + user.getName() + " " + user.getLastName()+" <br/>"
                + "</strong>, we would like to inform you that <strong> your account has been APPROVED ,"
                + "</strong>  <br/> starting from now you can start post your product to our system.<br/>"
                +"Thank you.";
        String subject = "Message from Maharishi University ecommerce System";

        try {
            EmailSender.registrationConfirmation(user.getEmail(), subject, Template.sendingEmail(m));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void sendingNotification(User user){
        String m = "Hello <strong>" + user.getName() + " " + user.getLastName()+" <br/>"
                + "</strong>, I would like to inform you that  <strong> WE HAVE ADDED NEW INTERSTING PRODUCT ,"
                + "</strong>  <br/> please be the first one to enjoy our new product on by clicking on the link.<br/>"
                +"Thank you.";
        String subject = "NEW PRODUCT UPLOADED";

        try {
            EmailSender.registrationConfirmation(user.getEmail(), subject, Template.sendingEmail(m));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
