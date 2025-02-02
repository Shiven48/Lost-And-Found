package com.app.Service;

import org.springframework.stereotype.Component;

@Component
public class PaginationAndSorting {
    private static final int MINIMUM_PAGE_NUMBER = 0;
    private static final int MINIMUM_PAGE_SIZE = 1;

    public void validatePaginateAndSort(int pageNumber, int pageSize, String sortField, String direction) {
        validatePaginationParams(pageNumber, pageSize);
        validateSortField(sortField);
        validateDirection(direction);
    }

    public void validatePaginate(int pageNumber, int pageSize){
        validatePaginationParams(pageNumber, pageSize);
    }

    private void validatePaginationParams(int pageNumber, int pageSize) {
        if (pageNumber < MINIMUM_PAGE_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("Page number must be greater than or equal to %d", MINIMUM_PAGE_NUMBER)
            );
        }

        if (pageSize < MINIMUM_PAGE_SIZE) {
            throw new IllegalArgumentException(
                    String.format("Page size must be greater than or equal to %d", MINIMUM_PAGE_SIZE)
            );
        }
    }

    private void validateSortField(String sortField) {
        if (sortField == null) {
            return;
        }

        if (sortField.trim().isEmpty()) {
            throw new IllegalArgumentException("Sort field cannot be empty");
        }
    }

    private void validateDirection(String direction){
        if(direction == null){
            return;
        }

        if(direction.trim().isEmpty()){
            throw new IllegalArgumentException("Please give valid direction(ASC or DESC)");
        }
    }
}
