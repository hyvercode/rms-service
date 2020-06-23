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
@Table(name = "currency")
@NoArgsConstructor
@AllArgsConstructor
public class Currency extends BaseEntity {
    @Id
    @Column(name = "currency_id")
    @Size(max = 3)
    private String currencyId;
    @Column(name = "currency_name")
    @Size(max = 30)
    private String currencyName;
    @Column(name = "currency_symbol")
    @Size(max = 5)
    private String currencySymbol;
    @Size(max = 1)
    @Column(name = "active")
    private String active;
}
