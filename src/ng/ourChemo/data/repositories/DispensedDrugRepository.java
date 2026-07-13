package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrug;

public interface DispensedDrugRepository {
    DispensedDrug save(DispensedDrug DispensedDrug);
    void delete(DispensedDrug DispensedDrug);
    void deleteAll();
    long count();
    DispensedDrug findById(int id);
}
