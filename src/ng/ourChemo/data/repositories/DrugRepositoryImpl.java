package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {
    private List<Drug> drugList = new ArrayList<>();
    private int count;

    @Override
    public Drug save(Drug drug) {
        if(isNew(drug))
            saveNew(drug);
        else
            updateDrug(drug);

        return drug;
    }

    private boolean isNew(Drug drug) {
        return drug.getId() == 0;
    }

    private void updateDrug(Drug drug) {
    }

    private void saveNew(Drug drug) {
        drug.setId(++count);
        drugList.add(drug);
    }

    @Override
    public void delete(Drug drug) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return count;
    }

    @Override
    public Drug findById(int id) {
        for(Drug drug : drugList) {
            if(drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }
}
