package com.welsee.tools;


import java.io.Serializable;
import java.util.List;


public class PageData<T> implements Serializable {
    protected final int pageSize;
    protected final int firstIndex;
    protected final int lastIndex;
    protected final List<T> items;
    protected final int totalItems;
    protected final int totalPages;
    protected int currentPage;
    protected final int pageItemsCount;

    public int getPageSize() {
        return this.pageSize;
    }

    public int getFirstIndex() {
        return this.firstIndex;
    }

    public int getLastIndex() {
        return this.lastIndex;
    }

    public List<T> getItems() {
        return this.items;
    }

    public int getTotalItems() {
        return this.totalItems;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getPageItemsCount() {
        return this.pageItemsCount;
    }

    public PageData() {
        this(1, 0, PageRequest.defaultPageSize, (List) null);
    }

    public PageData(int page, int size) {
        this(page, size, PageRequest.defaultPageSize, (List) null);
    }

    public PageData(int page, int size, int pageSize) {
        this(page, size, pageSize, (List) null);
    }

    public PageData(PageRequest req, int size, List<T> items) {
        this(req.getPage(), size, req.getSize(), items);
    }

    public PageData(int page, int size, int pageSize, List<T> items) {
        if (pageSize <= 0) {
            pageSize = PageRequest.defaultPageSize;
        }

        this.pageSize = pageSize;
        this.totalItems = size;
        this.items = items;
        this.totalPages = this.totalItems % pageSize == 0 ? this.totalItems / pageSize : this.totalItems / pageSize + 1;
        if (page < 1) {
            page = 1;
        }

        if (page > this.totalPages) {
            page = this.totalPages;
        }

        this.currentPage = page;
        this.firstIndex = calcFirstItemIndexOfPage(page, pageSize, size);
        int last = this.isLastPage() ? this.totalItems - 1 : this.firstIndex + pageSize - 1;
        int itemsPerPage = last - this.firstIndex + 1;
        if (last < 0) {
            last = 0;
            itemsPerPage = 0;
        }

        this.lastIndex = last;
        this.pageItemsCount = itemsPerPage;
    }

    public boolean hasNextPage() {
        return this.currentPage < this.totalPages - 1;
    }

    public boolean isLastPage() {
        return this.currentPage == this.totalPages;
    }

    public boolean hasPreviousPage() {
        return this.currentPage > 1;
    }

    public boolean isFirstPage() {
        return this.currentPage == 1 || this.currentPage == 0;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('[');
        result.append(this.currentPage).append(',');
        result.append(this.totalPages).append(',');
        result.append(this.totalItems).append(',');
        result.append(this.firstIndex).append(',');
        result.append(this.lastIndex).append(',');
        result.append(this.pageSize).append(']');
        return result.toString();
    }

    public static int calcPageOfItem(int itemIndex, int pageSize) {
        return itemIndex / pageSize + 1;
    }

    public static int calcFirstItemIndexOfPage(int page, int pageSize, int total) {
        if (total == 0) {
            return 0;
        } else {
            if (page < 1) {
                page = 1;
            }

            int first = (page - 1) * pageSize;
            if (first >= total) {
                first = (total - 1) / pageSize * pageSize;
            }

            return first;
        }
    }

    public static int calcFirstItemIndexOfPage(PageRequest pageRequest, int total) {
        return calcFirstItemIndexOfPage(pageRequest.getPage(), pageRequest.getSize(), total);
    }
}
