package edu.mum.cs.clientservice.adminmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advertissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-DD-YYYY")
    private Date createdDate;



    @Lob
    @Column( length = 100000 )
    private String pictureUrls;

    @Transient
    @JsonIgnore
    private MultipartFile[] pictures;
    private String status;
    }


