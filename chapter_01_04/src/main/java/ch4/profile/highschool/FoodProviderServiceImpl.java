package ch4.profile.highschool;

import ch4.profile.Food;
import ch4.profile.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {
    @Override
    public List<Food> providerLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("Coke"));
        lunchSet.add(new Food("Hamburber"));
        lunchSet.add(new Food("French Fries"));
        return lunchSet;
    }
}
