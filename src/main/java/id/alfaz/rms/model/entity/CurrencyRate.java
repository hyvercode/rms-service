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
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@Table(name = "currency_rate")
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRate extends BaseEntity {
    @Id
    @Column(name = "currency_rate_id",length = 36,nullable = false)
    private String currencyRateId;
    @Column(name = "date_rate",nullable = false)
    private Timestamp dateRate;
    @Column(name = "currency_id",length = 3,nullable = false)
    private String currencyId;
    @Column(name = "currencyRate",nullable = false)
    private BigDecimal currencyRate;
    @Column(name = "active",length = 1,nullable = false)
    private String active;
}
