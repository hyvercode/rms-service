package id.alfaz.rms.helper.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name = "created_by",length = 50,nullable = false)
    private String createdBy;
    @Column(name = "created_at",nullable = false)
    private Timestamp createdAt;
    @Column(name = "updated_by",length = 50,nullable = false)
    private String updatedBy;
    @Column(name = "updated_at",nullable = false)
    private Timestamp updatedAt;



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdateddAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updateddAt) {
        this.updatedAt = updateddAt;
    }
}
