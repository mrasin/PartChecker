package files;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Category;
import model.CategoryList;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonRead {

    public CategoryList readFromJson() throws Exception {

        File inputFile = new File("c:\\Users\\Mateusz\\Desktop\\MyFirstJavaProject\\myFirstProject\\categories.json");
        ObjectMapper mapper = new ObjectMapper();
        CategoryList list = mapper.readValue(inputFile, CategoryList.class);

        return list;
    }

    public void writeToJson(CategoryList categoryList)throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File("categories_test.json"), categoryList);
    }
}
