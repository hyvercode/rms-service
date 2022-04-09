package com.hyvercode.rms.helper.base;

import com.hyvercode.rms.helper.model.Pagination;

public class BasePaginationResponse extends BaseResponse {
    private Pagination pagination;

    public BasePaginationResponse(final Pagination pagination) {
        this.pagination = pagination;
    }

    public BasePaginationResponse() {
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public void setPagination(final Pagination pagination) {
        this.pagination = pagination;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BasePaginationResponse)) {
            return false;
        } else {
            BasePaginationResponse other = (BasePaginationResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$pagination = this.getPagination();
                Object other$pagination = other.getPagination();
                if (this$pagination == null) {
                    if (other$pagination != null) {
                        return false;
                    }
                } else if (!this$pagination.equals(other$pagination)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BasePaginationResponse;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result1 = 1;
        Object $pagination = this.getPagination();
        int result = result1 * 59 + ($pagination == null ? 43 : $pagination.hashCode());
        return result;
    }

    public String toString() {
        return "BasePaginationResponse(pagination=" + this.getPagination() + ")";
    }
}