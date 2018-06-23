package app;

import dto.CategoryDTO;
import dto.ElementDTO;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        ClientREST clientREST = new ClientREST();

        clientREST.getAllCategories()
                .forEach(categoryDTO -> {
                    System.out.println(categoryDTO);
                    categoryDTO.getElementDTOS().forEach(System.out::println);
                });

        Optional<CategoryDTO> category1 = clientREST.getCategoryById(1);
        category1.ifPresent(categoryDTO -> {
                    System.out.println(categoryDTO);
                    categoryDTO.getElementDTOS().forEach(System.out::println);
                }
        );

        Optional<CategoryDTO> category2 = clientREST.getCategoryById(9);
        category2.ifPresent(categoryDTO -> {
                    System.out.println(categoryDTO);
                    categoryDTO.getElementDTOS().forEach(System.out::println);
                }
        );

        clientREST.addElement(
                1,
                new ElementDTO("TestElemRest", 20, 3, 90)
        );

        Optional<CategoryDTO> category3 = clientREST.getCategoryById(1);
        category3.ifPresent(categoryDTO -> {
                    System.out.println(categoryDTO);
                    categoryDTO.getElementDTOS().forEach(System.out::println);
                }
        );

    }
}
