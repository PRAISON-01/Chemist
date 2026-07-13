package ng.ourChemo.data.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DispensedDrugs {
    private int id;
    private List<DispensedDrug> dispenseDrugs = new ArrayList<>();
    private LocalDate date;
    private User dispensedBy;

    public List<DispensedDrug> getDispenseDrugs() {
        return dispenseDrugs;
    }

    public void setDispenseDrugs(List<DispensedDrug> dispenseDrugs) {
        this.dispenseDrugs = dispenseDrugs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getDispensedBy() {
        return dispensedBy;
    }

    public void setDispensedBy(User dispensedBy) {
        this.dispensedBy = dispensedBy;
    }
}
