package com.quantas.shoppingrus.service;
import com.quantas.shoppingrus.domain.Product;
import com.quantas.shoppingrus.domain.SKU;
import com.quantas.shoppingrus.pricingRule.PricingRules;

import java.text.NumberFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

    @Test
    public void shouldSetThreeForTwoDealOnAppleTv() {
        Product atv = new Product(SKU.ATV);
        Product vga = new Product(SKU.VGA);
        PricingRules pricingRules = new PricingRules();

        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(atv);
        checkout.scan(vga);

       Double total = checkout.total();
       NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setGroupingUsed(false);
       assertEquals(currencyFormat.format(total), "$249.00");
    }
    @Test
    public void shouldDiscountIpadForMoreThanFour() {
        Product atv = new Product(SKU.ATV);
        Product ipd = new Product(SKU.IPD);
        PricingRules pricingRules = new PricingRules();

        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(atv);
        checkout.scan(ipd);
        checkout.scan(ipd);
        checkout.scan(ipd);

        Double total = checkout.total();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setGroupingUsed(false);
        assertEquals(currencyFormat.format(total), "$2718.95");
    }
    @Test
    public void shouldFreeVgaForMacBook() {
        Product mbp = new Product(SKU.MBP);
        Product ipd = new Product(SKU.IPD);
        Product vga = new Product(SKU.VGA);
        PricingRules pricingRules = new PricingRules();

        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(mbp);
        checkout.scan(vga);
        checkout.scan(ipd);

        Double total = checkout.total();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setGroupingUsed(false);
        assertEquals(currencyFormat.format(total), "$1949.98");
    }

}
