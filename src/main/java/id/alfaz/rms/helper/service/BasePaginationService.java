package id.alfaz.rms.helper.service;

import id.alfaz.rms.helper.base.BasePaginationRequest;
import id.alfaz.rms.helper.base.BasePaginationResponse;
import org.springframework.beans.factory.annotation.Value;

public abstract class BasePaginationService<T extends BasePaginationRequest, V extends BasePaginationResponse> implements BaseService<T, V> {
    @Value("${default.page.size}")
    private Integer pageSize;
    @Value("${default.page.number}")
    private Integer pageNumber;
    @Value("${default.page.sortBy}")
    private String sortBy;
    @Value("${default.page.sortType}")
    private String sortType;

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public String getSortType() {
        return this.sortType;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(final Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSortBy(final String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortType(final String sortType) {
        this.sortType = sortType;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BasePaginationService)) {
            return false;
        } else {
            BasePaginationService<?, ?> other = (BasePaginationService)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$pageSize = this.getPageSize();
                    Object other$pageSize = other.getPageSize();
                    if (this$pageSize == null) {
                        if (other$pageSize == null) {
                            break label59;
                        }
                    } else if (this$pageSize.equals(other$pageSize)) {
                        break label59;
                    }

                    return false;
                }

                Object this$pageNumber = this.getPageNumber();
                Object other$pageNumber = other.getPageNumber();
                if (this$pageNumber == null) {
                    if (other$pageNumber != null) {
                        return false;
                    }
                } else if (!this$pageNumber.equals(other$pageNumber)) {
                    return false;
                }

                Object this$sortBy = this.getSortBy();
                Object other$sortBy = other.getSortBy();
                if (this$sortBy == null) {
                    if (other$sortBy != null) {
                        return false;
                    }
                } else if (!this$sortBy.equals(other$sortBy)) {
                    return false;
                }

                Object this$sortType = this.getSortType();
                Object other$sortType = other.getSortType();
                if (this$sortType == null) {
                    if (other$sortType != null) {
                        return false;
                    }
                } else if (!this$sortType.equals(other$sortType)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BasePaginationService;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result1 = 1;
        Object $pageSize = this.getPageSize();
        int result = result1 * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $pageNumber = this.getPageNumber();
        result = result * 59 + ($pageNumber == null ? 43 : $pageNumber.hashCode());
        Object $sortBy = this.getSortBy();
        result = result * 59 + ($sortBy == null ? 43 : $sortBy.hashCode());
        Object $sortType = this.getSortType();
        result = result * 59 + ($sortType == null ? 43 : $sortType.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getPageSize();
        return "BasePaginationService(pageSize=" + var10000 + ", pageNumber=" + this.getPageNumber() + ", sortBy=" + this.getSortBy() + ", sortType=" + this.getSortType() + ")";
    }

    public BasePaginationService(final Integer pageSize, final Integer pageNumber, final String sortBy, final String sortType) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    public BasePaginationService() {
    }
}
