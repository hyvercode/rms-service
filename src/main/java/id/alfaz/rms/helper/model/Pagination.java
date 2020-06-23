package id.alfaz.rms.helper.model;

import java.io.Serializable;

public class Pagination implements Serializable {
    private Integer pageSize;
    private Integer currentPage;
    private Integer totalPages;
    private Long totalRecords;
    private Boolean isFirstPage;
    private Boolean isLastPage;

    public static Pagination.PaginationBuilder builder() {
        return new Pagination.PaginationBuilder();
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Integer getCurrentPage() {
        return this.currentPage;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public Long getTotalRecords() {
        return this.totalRecords;
    }

    public Boolean getIsFirstPage() {
        return this.isFirstPage;
    }

    public Boolean getIsLastPage() {
        return this.isLastPage;
    }

    public void setPageSize(final Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(final Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(final Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalRecords(final Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setIsFirstPage(final Boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public void setIsLastPage(final Boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Pagination)) {
            return false;
        } else {
            Pagination other = (Pagination)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$pageSize = this.getPageSize();
                Object other$pageSize = other.getPageSize();
                if (this$pageSize == null) {
                    if (other$pageSize != null) {
                        return false;
                    }
                } else if (!this$pageSize.equals(other$pageSize)) {
                    return false;
                }

                Object this$currentPage = this.getCurrentPage();
                Object other$currentPage = other.getCurrentPage();
                if (this$currentPage == null) {
                    if (other$currentPage != null) {
                        return false;
                    }
                } else if (!this$currentPage.equals(other$currentPage)) {
                    return false;
                }

                Object this$totalPages = this.getTotalPages();
                Object other$totalPages = other.getTotalPages();
                if (this$totalPages == null) {
                    if (other$totalPages != null) {
                        return false;
                    }
                } else if (!this$totalPages.equals(other$totalPages)) {
                    return false;
                }

                label62: {
                    Object this$totalRecords = this.getTotalRecords();
                    Object other$totalRecords = other.getTotalRecords();
                    if (this$totalRecords == null) {
                        if (other$totalRecords == null) {
                            break label62;
                        }
                    } else if (this$totalRecords.equals(other$totalRecords)) {
                        break label62;
                    }

                    return false;
                }

                label55: {
                    Object this$isFirstPage = this.getIsFirstPage();
                    Object other$isFirstPage = other.getIsFirstPage();
                    if (this$isFirstPage == null) {
                        if (other$isFirstPage == null) {
                            break label55;
                        }
                    } else if (this$isFirstPage.equals(other$isFirstPage)) {
                        break label55;
                    }

                    return false;
                }

                Object this$isLastPage = this.getIsLastPage();
                Object other$isLastPage = other.getIsLastPage();
                if (this$isLastPage == null) {
                    if (other$isLastPage != null) {
                        return false;
                    }
                } else if (!this$isLastPage.equals(other$isLastPage)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pagination;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result1 = 1;
        Object $pageSize = this.getPageSize();
        int result = result1 * 59 + ($pageSize == null ? 43 : $pageSize.hashCode());
        Object $currentPage = this.getCurrentPage();
        result = result * 59 + ($currentPage == null ? 43 : $currentPage.hashCode());
        Object $totalPages = this.getTotalPages();
        result = result * 59 + ($totalPages == null ? 43 : $totalPages.hashCode());
        Object $totalRecords = this.getTotalRecords();
        result = result * 59 + ($totalRecords == null ? 43 : $totalRecords.hashCode());
        Object $isFirstPage = this.getIsFirstPage();
        result = result * 59 + ($isFirstPage == null ? 43 : $isFirstPage.hashCode());
        Object $isLastPage = this.getIsLastPage();
        result = result * 59 + ($isLastPage == null ? 43 : $isLastPage.hashCode());
        return result;
    }

    public String toString() {
        Integer var10000 = this.getPageSize();
        return "Pagination(pageSize=" + var10000 + ", currentPage=" + this.getCurrentPage() + ", totalPages=" + this.getTotalPages() + ", totalRecords=" + this.getTotalRecords() + ", isFirstPage=" + this.getIsFirstPage() + ", isLastPage=" + this.getIsLastPage() + ")";
    }

    public Pagination(final Integer pageSize, final Integer currentPage, final Integer totalPages, final Long totalRecords, final Boolean isFirstPage, final Boolean isLastPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.isFirstPage = isFirstPage;
        this.isLastPage = isLastPage;
    }

    public Pagination() {
    }

    public static class PaginationBuilder {
        private Integer pageSize;
        private Integer currentPage;
        private Integer totalPages;
        private Long totalRecords;
        private Boolean isFirstPage;
        private Boolean isLastPage;

        PaginationBuilder() {
        }

        public Pagination.PaginationBuilder pageSize(final Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Pagination.PaginationBuilder currentPage(final Integer currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Pagination.PaginationBuilder totalPages(final Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Pagination.PaginationBuilder totalRecords(final Long totalRecords) {
            this.totalRecords = totalRecords;
            return this;
        }

        public Pagination.PaginationBuilder isFirstPage(final Boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
            return this;
        }

        public Pagination.PaginationBuilder isLastPage(final Boolean isLastPage) {
            this.isLastPage = isLastPage;
            return this;
        }

        public Pagination build() {
            return new Pagination(this.pageSize, this.currentPage, this.totalPages, this.totalRecords, this.isFirstPage, this.isLastPage);
        }

        public String toString() {
            return "Pagination.PaginationBuilder(pageSize=" + this.pageSize + ", currentPage=" + this.currentPage + ", totalPages=" + this.totalPages + ", totalRecords=" + this.totalRecords + ", isFirstPage=" + this.isFirstPage + ", isLastPage=" + this.isLastPage + ")";
        }
    }
}
