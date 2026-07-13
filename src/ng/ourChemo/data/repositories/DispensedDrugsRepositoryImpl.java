package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository {
    private int count;
    private List<DispensedDrugs> dispensedDrugsList = new ArrayList<>();

    @Override
    public DispensedDrugs save(DispensedDrugs dispensedDrugs) {
        if(isNew(dispensedDrugs))
            saveNew(dispensedDrugs);
        else
            updateDispensedDrugs(dispensedDrugs);
        return dispensedDrugs;
    }

    private void saveNew(DispensedDrugs dispensedDrugs) {
        dispensedDrugs.setId(++count);
        dispensedDrugsList.add(dispensedDrugs);
    }

    private void updateDispensedDrugs(DispensedDrugs dispensedDrugs) {

    }

    private boolean isNew(DispensedDrugs dispensedDrugs) {
        if(dispensedDrugs.getId() == 0) {
            return true;
        }
        return false;
    }



    @Override
    public void delete(DispensedDrugs dispensedDrugs) {
        dispensedDrugsList.remove(dispensedDrugs);
        --count;
    }

    @Override
    public void deleteAll() {
        dispensedDrugsList.clear();
    }

    @Override
    public long count() {
        return dispensedDrugsList.size();
    }

    @Override
    public DispensedDrugs findById(int id) {
        for(DispensedDrugs dispensedDrugs : dispensedDrugsList) {
            if(dispensedDrugs.getId() == id) {
                return dispensedDrugs;
            }
        }
        return null;
    }
}
