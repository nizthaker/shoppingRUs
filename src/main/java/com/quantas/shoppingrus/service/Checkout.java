package com.quantas.shoppingrus.service;

import com.quantas.shoppingrus.domain.Product;
import com.quantas.shoppingrus.domain.SKU;
import com.quantas.shoppingrus.pricingRule.PricingRules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Checkout {

    private PricingRules pricingRules;
    private List<Product> items;

    public Checkout() {}

    public  Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.items = new ArrayList();
    }

    public void scan(Product item){
        this.items.add(item);
    }


    public Double total() {
     return Stream.of(SKU.values()).map((i -> pricingRules.applyPriceRule(i, this.items))).reduce(0.0, (a, b) -> a + b);
    }

}
