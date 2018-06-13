package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryList {

    private List<Category> listOfCategories;

    public CategoryList() {
    }

    public CategoryList(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
    }

    public void show() {
        for (Category tmp : listOfCategories) {
            System.out.println(tmp);
        }
    }

    public List<Category> getListOfCategories() {

        return listOfCategories;
    }

    public void setListOfCategories(ArrayList<Category> listOfCategories) {

        this.listOfCategories = listOfCategories;
    }
}
