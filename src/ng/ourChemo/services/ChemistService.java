package ng.ourChemo.services;

import ng.ourChemo.dtos.requests.AddDrugRequest;
import ng.ourChemo.dtos.requests.DeleteDrugRequest;
import ng.ourChemo.dtos.requests.DispenseDrugRequest;
import ng.ourChemo.dtos.requests.UpdateDrugRequest;
import ng.ourChemo.dtos.responses.AddDrugResponse;
import ng.ourChemo.dtos.responses.DeleteDrugResponse;
import ng.ourChemo.dtos.responses.DispenseDrugResponse;
import ng.ourChemo.dtos.responses.UpdateDrugResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ChemistService {
    AddDrugResponse addDrug(AddDrugRequest addDrugRequest);
    UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest);
    DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest);
    DispenseDrugResponse dispenseDrug(DispenseDrugRequest dispenseDrugRequest);


//    private String drugName;
//    private String brand;
//    private LocalDate expiryDate;
//    private BigDecimal price;
//    private int id;
}
