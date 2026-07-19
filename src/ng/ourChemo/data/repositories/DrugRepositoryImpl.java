package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.Drug;

import java.util.ArrayList;
import java.util.List;

public class DrugRepositoryImpl implements DrugRepository {
    private static List<Drug> drugsList = new ArrayList<>();

    private static int count;

    @Override
    public Drug save(Drug drug) {
        if(drug.getId() == 0) {
            drug.setId(++count);
            drugsList.add(drug);
        }
        else{
            for(int counter = 0; counter < drugsList.size(); counter++) {
                if(drugsList.get(counter).getId() == drug.getId()) {
                    drugsList.set(counter, drug);
                    break;
                }
            }
        }
        return drug;
    }

//    private void updateDrug(Drug drug) {
//        deleteById(drug.getId());
//        drugsList.add(drug);
//    }

//    private void saveNew(Drug drug) {
//        drug.setId(++count);
//        drugsList.add(drug);
//    }

//    private boolean isNew(Drug drug) {
//        if (drug.getId() == 0) {
//            return true;
//        }
//        return false;
//    }
    @Override
    public void deleteById(int id) {
        Drug drugToDelete = findById( id);
        delete(drugToDelete);
    }

    @Override
    public void delete(Drug drug) {
        drugsList.remove(drug);
    }

    @Override
    public void deleteAll() {
        drugsList.clear();
        count = 0;
    }

    @Override
    public long count() {
        return drugsList.size();
    }

    @Override
    public Drug findById(int id) {
        for(Drug drug : drugsList) {
            if( drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    public Drug findDrugName(String drugName) {
        for(var drug : drugsList) if(drug.getDrugName().equalsIgnoreCase(drugName)) return drug;
        return null;
    }
}
