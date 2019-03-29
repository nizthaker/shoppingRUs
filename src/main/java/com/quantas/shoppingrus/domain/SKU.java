package com.quantas.shoppingrus.domain;

//Enum for product types
public enum SKU {
      IPD("Super iPad"),
      MBP("MacBook Pro"),
      ATV("Apple TV"),
      VGA("VGA adapter");

      private String description;

      SKU(String description) {
           this.description = description;
      }
      public String getDescription() {
            return description;
      }
}

