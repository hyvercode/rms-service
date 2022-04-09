package com.hyvercode.rms.helper.util;

import com.hyvercode.rms.helper.model.Pagination;
import com.hyvercode.rms.helper.base.BasePaginationRequest;
import com.google.common.base.CaseFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public final class PageableUtil {
    private static final String ASC_VALUE = "ASC";

    private PageableUtil() {
    }

    public static PageRequest createPageRequestNative(BasePaginationRequest req, Integer defaultPageSize, Integer defaultPageNumber, String defaultSortBy, String defaultSortType) {
        if (req.getSortBy() != null && req.getSortBy().matches("([a-z]+[A-Z]+\\w+)+")) {
            req.setSortBy(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, req.getSortBy()));
        }

        if (defaultSortBy.matches("([a-z]+[A-Z]+\\w+)+")) {
            defaultSortBy = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, defaultSortBy);
        }

        if (req.getPageSize() != null) {
            defaultPageSize = req.getPageSize();
        }

        if (req.getPageNumber() != null) {
            defaultPageNumber = req.getPageNumber();
        }

        if (req.getSortBy() != null) {
            defaultSortBy = req.getSortBy();
        }

        if (req.getSortType() != null) {
            defaultSortType = req.getSortType();
        }

        return PageRequest.of(defaultPageNumber - 1, defaultPageSize, "ASC".equalsIgnoreCase(defaultSortType) ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{defaultSortBy});
    }

    public static PageRequest createPageRequest(BasePaginationRequest req, Integer defaultPageSize, Integer defaultPageNumber, String defaultSortBy, String defaultSortType) {
        if (req.getSortBy() != null && req.getSortBy().contains("_")) {
            req.setSortBy(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, req.getSortBy()));
        }

        if (defaultSortBy.contains("_")) {
            defaultSortBy = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, defaultSortBy);
        }

        if (req.getPageSize() != null) {
            defaultPageSize = req.getPageSize();
        }

        if (req.getPageNumber() != null) {
            defaultPageNumber = req.getPageNumber();
        }

        if (req.getSortBy() != null) {
            defaultSortBy = req.getSortBy();
        }

        if (req.getSortType() != null) {
            defaultSortType = req.getSortType();
        }

        return PageRequest.of(defaultPageNumber - 1, defaultPageSize, "ASC".equalsIgnoreCase(defaultSortType) ? Sort.Direction.ASC : Sort.Direction.DESC, new String[]{defaultSortBy});
    }

    public static <T> Pagination pageToPagination(Page<T> page) {
        return Pagination.builder().pageSize(page.getSize()).currentPage(page.getNumber() + 1).totalPages(page.getTotalPages()).totalRecords(page.getTotalElements()).isFirstPage(page.isFirst()).isLastPage(page.isLast()).build();
    }
}
