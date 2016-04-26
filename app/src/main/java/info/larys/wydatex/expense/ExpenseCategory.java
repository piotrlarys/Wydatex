package info.larys.wydatex.expense;

/**
 * Created by Piotr on 2016-04-06.
 */
public enum ExpenseCategory {

    FOOD("jedzenie"), ENTERTAIMENT("Rozrywka"), HYGIENE("Higienia"), OTHER("Inne");

    private String name;

    ExpenseCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static int getId(String categoryName) {
        int counter = 0;
        for (ExpenseCategory category: values()) {
            if (category.name().equals(categoryName))
                return counter;
            counter++;
        }
        return counter - 1;
    }
}
