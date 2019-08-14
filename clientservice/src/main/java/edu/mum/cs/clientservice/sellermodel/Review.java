package edu.mum.cs.clientservice.sellermodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private Long id;
    private String description;
    private Double rating;
    @DateTimeFormat(pattern = "MM-DD-YYYY")
    private Date date;
    private String status;
    private Long  accountId;
    private Long productId;
}
