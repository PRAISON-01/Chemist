package ng.ourChemo.controllers;

import ng.ourChemo.dtos.requests.AddDrugRequest;
import ng.ourChemo.dtos.requests.DeleteDrugRequest;
import ng.ourChemo.dtos.requests.DispenseDrugRequest;
import ng.ourChemo.dtos.requests.UpdateDrugRequest;
import ng.ourChemo.dtos.responses.AddDrugResponse;
import ng.ourChemo.dtos.responses.DeleteDrugResponse;
import ng.ourChemo.dtos.responses.DispenseDrugResponse;
import ng.ourChemo.dtos.responses.UpdateDrugResponse;
import ng.ourChemo.services.ChemistService;
import ng.ourChemo.services.ChemistServiceImpl;

public class ChemistServiceController {
    private ChemistService chemistService = new ChemistServiceImpl();

    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest) {
        return chemistService.addDrug(addDrugRequest);
    }

    public UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest) {
        return chemistService.updateDrug(updateDrugRequest);
    }

    public DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest) {
        return chemistService.deleteDrug(deleteDrugRequest);
    }

    public DispenseDrugResponse dispenseDrug(DispenseDrugRequest dispenseDrugRequest) {
        return chemistService.dispenseDrug(dispenseDrugRequest);
    }
}