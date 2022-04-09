package com.hyvercode.rms.helper.base;

public class BasePaginationRequest extends BaseRequest {
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortType;

    public BasePaginationRequest(final Integer pageSize, final Integer pageNumber, final String sortBy, final String sortType) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortType = sortType;
    }

    public BasePaginationRequest() {
    }

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
        } else if (!(o instanceof BasePaginationRequest)) {
            return false;
        } else {
            BasePaginationRequest other = (BasePaginationRequest)o;
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
        return other instanceof BasePaginationRequest;
    }

    public int hashCode() {
        Boolean PRIME = true;
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
        return "BasePaginationRequest(pageSize=" + var10000 + ", pageNumber=" + this.getPageNumber() + ", sortBy=" + this.getSortBy() + ", sortType=" + this.getSortType() + ")";
    }
}
