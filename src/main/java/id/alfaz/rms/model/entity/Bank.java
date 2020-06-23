package id.alfaz.rms.model.entity;

import id.alfaz.rms.helper.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@Table(name = "bank")
@NoArgsConstructor
@AllArgsConstructor
public class Bank extends BaseEntity {
    @Id
    @Column(name = "bank_id")
    @Size(max = 5)
    private String bankId;
    @Column(name = "country_code")
    @Size(max = 3)
    private String countryCode;

    @Column(name = "bank_name")
    @Size(max = 30)
    private String bankName;

    @Column(name = "bank_image_link")
    @Size(max = 2048)
    private String bankImageLink;

    @Column(name = "active")
    @Size(max = 1)
    private String active;
}
