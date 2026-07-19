package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;

import java.util.ArrayList;
import java.util.List;

public class DispensedDrugsRepositoryImpl implements DispensedDrugsRepository {
    private static List<DispensedDrugs> dispensedDrugsList = new ArrayList<>();

    private static int count;

    @Override
    public DispensedDrugs save(DispensedDrugs dispensedDrugs) {
        if(isNew(dispensedDrugs))
            saveNew(dispensedDrugs);
        else updateDispensedDrugs(dispensedDrugs);
        return dispensedDrugs;
    }

    private void updateDispensedDrugs(DispensedDrugs dispensedDrugs) {
        deleteById(dispensedDrugs.getId());
        dispensedDrugsList.add(dispensedDrugs);
    }

    private void saveNew(DispensedDrugs dispensedDrugs) {
        dispensedDrugs.setId(++count);
        dispensedDrugsList.add(dispensedDrugs);
    }

    private boolean isNew(DispensedDrugs dispensedDrugs) {
        if (dispensedDrugs.getId() == 0) {
            return true;
        }
        return false;
    }

    public void deleteById(int id) {
        DispensedDrugs dispensedDrugsToDelete = findById( id);
        delete(dispensedDrugsToDelete);
    }

    @Override
    public void delete(DispensedDrugs dispensedDrugs) {
        dispensedDrugsList.remove(dispensedDrugs);
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
            if( dispensedDrugs.getId() == id) {
                return dispensedDrugs;
            }
        }
        return null;
    }

}
