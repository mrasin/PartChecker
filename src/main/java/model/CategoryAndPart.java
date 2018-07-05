package model;

public class CategoryAndPart implements Comparable<CategoryAndPart> {

    private String category;
    private String part;

    public CategoryAndPart() {
    }

    public CategoryAndPart(String category, String part) {
        this.category = category;
        this.part = part;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public int compareTo(CategoryAndPart o) {
        if(this.category.compareTo(o.category) < 0) {
            return -1;
        } else if (this.category.compareTo(o.category) > 0) {
            return 1;
        } else {
            if (this.part.compareTo(o.part) < 0) {
                return -1;
            } else if (this.part.compareTo(o.part) > 0) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        String result = category + ", " + part;
        return result;
    }
}
