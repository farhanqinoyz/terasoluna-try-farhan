package com.pelatihan.terasoluna.domain.model;

import java.util.Date;

public class Cart {
  private String itemId;
  private int cartQuantity;
  private Date lastModified;

  public Cart() {

  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public int getCartQuantity() {
    return cartQuantity;
  }

  public void setCartQuantity(int cartQuantity) {
    this.cartQuantity = cartQuantity;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @Override
  public String toString() {
    return "Cart{" +
        "itemId='" + itemId + '\'' +
        ", cartQuantity=" + cartQuantity +
        ", lastModified=" + lastModified +
        '}';
  }
}
