package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

  @Autowired
  ItemRepository itemRepository;


  @Test
  @DisplayName("상품 저장 테스트")
  public void createItemTest() {
    Item item = new Item();
    item.setItemNm("test product");
    item.setPrice(10000);
    item.setItemDetail("test item detail");
    item.setItemSellStatus(ItemSellStatus.SELL);
    item.setStockNumber(100);
    item.setRegTime(LocalDateTime.now());
    item.setUpdateTime(LocalDateTime.now());

    Item savedItem = itemRepository.save(item);
    System.out.println(savedItem.toString());
  }


  public void createItemList() {
    for (int i = 0; i < 10; ++i) {
      Item item = new Item();
      item.setItemNm("test product" + i);
      item.setPrice(10000 + i);
      item.setItemDetail("test item detail" + i);
      item.setItemSellStatus(ItemSellStatus.SELL);
      item.setStockNumber(100);
      item.setRegTime(LocalDateTime.now());
      item.setUpdateTime(LocalDateTime.now());
      Item savedItem = itemRepository.save(item);
//      System.out.println(savedItem.toString());
    }
  }


  @Test
  @DisplayName("상품명 조회 테스트")
  public void findByItemNmTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByItemNm("test product1");
    for (Item item : itemList)
      System.out.println(item);
  }


  @Test
  @DisplayName("상품명, 상품상세설명 or 테스트")
  public void findByItemNmOrItemDetailTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByItemNmOrItemDetail("test product1",
                                                                  "test item detail5");
    for (Item item : itemList)
      System.out.println(item);
  }


  @Test
  @DisplayName("가격 Less Than 테스트")
  public void findByPriceLessThanTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByPriceLessThan(10005);
    for (Item item : itemList)
      System.out.println(item);
  }


  @Test
  @DisplayName("가격 내림차순 조회 테스트")
  public void findByPriceLessThanOrderByPriceDescTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
    for (Item item : itemList)
      System.out.println(item);
  }


  @Test
  @DisplayName("@Query를 이용한 상품 조회 테스트")
  public void findByItemDetailTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByItemDetail("test item detail");
    for (Item item : itemList)
      System.out.println(item);
  }


  @Test
  @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
  public void findByItemDetailByNativeTest() {
    createItemList();
    List<Item> itemList = itemRepository.findByItemDetailByNative("test item detail");
    for (Item item : itemList)
      System.out.println(item);
  }

}