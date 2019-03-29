package com.quantas.shoppingrus.pricingRule;

import com.quantas.shoppingrus.constant.Price;
import com.quantas.shoppingrus.domain.Product;
import com.quantas.shoppingrus.domain.SKU;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PricingRules {
    //Calculate total price for selected product in the cart
    public Double applyPriceRule(SKU sku, List<Product> items) {
        switch (sku) {
            case ATV:
                List<Product> atvItems = items.stream().filter(i -> i.getSku() == SKU.ATV).collect(Collectors.toList());
                //3 for 2 deal on Apple TV
                if (Objects.nonNull(atvItems) && atvItems.size() >= 3) {
                    return (((atvItems.size() / 3) * 2) + (atvItems.size() % 3)) * Price.ATV_VALUE;
                } else if (Objects.nonNull(atvItems)) {
                    return (atvItems.size() * Price.ATV_VALUE);
                }
                return 0.0;
            case IPD:
                List<Product> ipdItems = items.stream().filter(i -> i.getSku() == SKU.IPD).collect(Collectors.toList());
                //apply bulk discount for super Ipad if more than four
                if (Objects.nonNull(ipdItems) && ipdItems.size() > 4) {
                    return ipdItems.size() * Price.IPD_DISCOUNT_VALUE;
                } else if (Objects.nonNull(ipdItems)) {
                    return (ipdItems.size() * Price.IPD_VALUE);
                }
                return 0.0;
            case MBP:
                List<Product> mbpItems = items.stream().filter(i -> i.getSku() == SKU.MBP).collect(Collectors.toList());
                if (Objects.nonNull(mbpItems)) {
                    return (mbpItems.size() * Price.MBP_VALUE);
                }
                return 0.0;
            case VGA:
                 List<Product> vgaItems = items.stream().filter(i -> i.getSku() == SKU.VGA).collect(Collectors.toList());
                 List<Product> mbpItems1 = items.stream().filter(i -> i.getSku() == SKU.MBP).collect(Collectors.toList());
                 //free VGA for each MackBook
                if (Objects.nonNull(vgaItems)) {
                    final int vgaItemsToPrice = vgaItems.size() - (Objects.nonNull(mbpItems1) ? mbpItems1.size() : 0);
                    return (vgaItemsToPrice <= 0 ? 0.00 : vgaItemsToPrice * Price.VGA_VALUE);
                }
                return 0.0;
        }
        return 0.0;

    }

}
