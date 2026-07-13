package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugRepositoryImpl implements DispensedDrugRepository {
    private List<DispensedDrug> dispensedDrugList = new ArrayList<>();
    private int count;
    @Override
    public DispensedDrug save(DispensedDrug dispensedDrug) {
        if(isNew(dispensedDrug))
            saveNew(dispensedDrug);
        else updateDispensedDrug(dispensedDrug);
        return dispensedDrug;
    }

    private void updateDispensedDrug(DispensedDrug dispensedDrug) {

    }

    private void saveNew(DispensedDrug dispensedDrug) {
        dispensedDrug.setId(++count);
        dispensedDrugList.add(dispensedDrug);
    }

    private boolean isNew(DispensedDrug dispensedDrug) {
        return dispensedDrug.getId() == 0;
    }

    @Override
    public void delete(DispensedDrug dispensedDrug) {
        dispensedDrugList.remove(dispensedDrug);
        --count;
    }

    @Override
    public void deleteAll() {
        dispensedDrugList.clear();
    }

    @Override
    public long count() {
        return dispensedDrugList.size();
    }

    @Override
    public DispensedDrug findById(int id) {
        for(DispensedDrug dispensedDrug : dispensedDrugList) {
            if(dispensedDrug.getId() == id) {
                return dispensedDrug;
            }
        }
        return null;
    }
}
