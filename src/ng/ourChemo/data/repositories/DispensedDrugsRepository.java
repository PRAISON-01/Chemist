package ng.ourChemo.data.repositories;

import ng.ourChemo.data.models.DispensedDrugs;

public interface DispensedDrugsRepository {
    DispensedDrugs save(DispensedDrugs dispensedDrugs);
    void delete(DispensedDrugs dispensedDrugs);
    void deleteAll();
    long count();
    DispensedDrugs findById(int id);
}
