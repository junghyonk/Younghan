package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository=new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item=new Item("itemA",10000,10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(saveItem.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll(){
        //given
        Item item1=new Item("item1",10000,10);
        Item item2=new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result=itemRepository.findAll();

        //then
        Assertions.assertThat(result.get(0)).isEqualTo(item1);
    }

    @Test
    void update(){
        //given
        Item item1=new Item("item1",10000,10);

        Item savedItem = itemRepository.save(item1);
        Long itemId= savedItem.getId();
        //when
        Item updateItem=new Item("item2",20000,19);
        itemRepository.update(itemId,updateItem);

        //then
        Item findItem = itemRepository.findById(itemId);
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}
