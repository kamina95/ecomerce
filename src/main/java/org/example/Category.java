package org.example;

public class Category {
    private String categoryName;
    private String categoryId;

    public Category(String categoryName, String categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    @Override
    public String toString(){return categoryId + "    " + categoryName;}
}
