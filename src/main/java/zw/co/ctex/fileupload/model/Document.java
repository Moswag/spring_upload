package zw.co.ctex.fileupload.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : Webster Moswa
 * @since : 28/01/2020, Tue
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Entity
@Data
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String docName;
}
