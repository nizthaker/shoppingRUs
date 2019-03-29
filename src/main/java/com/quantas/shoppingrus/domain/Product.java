package com.quantas.shoppingrus.domain;

public class Product {
    private SKU sku;

    public Product(SKU sku) {
        this.sku = sku;
    }

    public SKU getSku() {
        return sku;
    }
}
