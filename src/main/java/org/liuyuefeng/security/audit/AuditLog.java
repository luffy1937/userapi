package org.liuyuefeng.security.audit;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String method;
    private String path;
    private Integer status;
    private String username;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdTime;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifyTime;
}
