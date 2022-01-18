package ru.sprng.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sprng.demo.entities.Item;
import ru.sprng.demo.entities.ItemProjection;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {



    //SELECT i FROM Item i WHERE i.title = 1?
    Item findByTitle(String title);
    List<Item> findByCostBetween(int min, int max);
    //List<Item> findByNameAndSurname(String name, String surname); //поиск имени и фамилии
    //List<Item> findByActiveTrue(String name, String surname); //Если есть какое то boolean поле
    //List<Item> findByCostOrderByTitleDesc (String name, String surname); //найти все Item по стоимости и отсортировать их в порядке убывания по названию

//    @Query(value = "Здесь вписывается JPQL запрос", nativeQuery = true)
//    List<Item> myMethodName();

//    @Query(value = "SELECT i FROM Item WHERE i.title LIKE %?1") //Мы хотим вытащить какие-то Айтимы связанные с таблицей Айтим которые заканчиваются на 1
//    List<Item> findByTitleEndWith(String substr);
}

