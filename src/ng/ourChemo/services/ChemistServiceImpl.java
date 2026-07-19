package ng.ourChemo.services;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.repositories.DrugRepository;
import ng.ourChemo.data.repositories.DrugRepositoryImpl;
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

import static ng.ourChemo.utils.Mappers.mapDrugRequest;
import static ng.ourChemo.utils.Mappers.mapDrugResponse;

public class ChemistServiceImpl implements ChemistService{
    private final DrugRepository drugs = new DrugRepositoryImpl();

    @Override
    public AddDrugResponse addDrug(AddDrugRequest addDrugRequest) {
        if(addDrugRequest == null || addDrugRequest.getDrugName() == null || addDrugRequest.getPrice() == null || addDrugRequest.getBrand() == null || addDrugRequest.getDescription() == null || addDrugRequest.getQuantity() <= 0) throw new IllegalArgumentException("missing drug data!!!");
        if(addDrugRequest.getExpiryDate().isBefore(LocalDate.now())) throw new IllegalArgumentException("Drug does not exist!!!");
        if(addDrugRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Price can not be Zero and cannot be Negative!!!");
        if(addDrugRequest.getQuantity() <= 0) throw new IllegalArgumentException("Quantity must be greater than zero!!!");
        Drug drug = mapDrugRequest(addDrugRequest);
        drugs.save(drug);
        return mapDrugResponse(drug);
    }

    @Override
    public UpdateDrugResponse updateDrug(UpdateDrugRequest updateDrugRequest) {
        if(updateDrugRequest == null || updateDrugRequest.getDrugName() == null || updateDrugRequest.getPrice() == null || updateDrugRequest.getBrand() == null || updateDrugRequest.getDescription() == null) throw new IllegalArgumentException("missing drug data!!!");
        if(updateDrugRequest.getExpiryDate().isBefore(LocalDate.now())) throw new IllegalArgumentException("Drug has expired!!!");
        if(updateDrugRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Price can not be Zero and cannot be Negative!!!");

        Drug drug = drugs.findById(updateDrugRequest.getId());
        if(drug == null) throw new IllegalArgumentException("Drug does not exist!!!");
        drug.setDrugName(updateDrugRequest.getDrugName());
        drug.setBrand(updateDrugRequest.getBrand());
        drug.setPrice(updateDrugRequest.getPrice());
        drug.setDescription(updateDrugRequest.getDescription());
        drug.setExpiryDate(updateDrugRequest.getExpiryDate());

        drugs.save(drug);

        UpdateDrugResponse updateDrugResponse = new UpdateDrugResponse();

        updateDrugResponse.setDrugName(drug.getDrugName());
        updateDrugResponse.setBrand(drug.getBrand());
        updateDrugResponse.setId(drug.getId());
        updateDrugResponse.setDrugName(drug.getDrugName());
        updateDrugResponse.setDescription(drug.getDescription());
        updateDrugResponse.setExpiryDate(drug.getExpiryDate());
        updateDrugResponse.setPrice(drug.getPrice());

        return updateDrugResponse;
    }

    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest deleteDrugRequest) {
        Drug drug = drugs.findById(deleteDrugRequest.getId());
        if(drug == null) throw new IllegalArgumentException("Drug does not exist!!!");
        if(deleteDrugRequest.getId() != drug.getId())throw new IllegalArgumentException("Drug Does not exist / invalid drug id!!!");
        drugs.deleteById(deleteDrugRequest.getId());

        DeleteDrugResponse deleteDrugResponse = new DeleteDrugResponse();
        deleteDrugResponse.setId(deleteDrugRequest.getId());
        deleteDrugResponse.setMessage("Drug deleted successfully!!!");
        return deleteDrugResponse;
    }

    @Override
    public DispenseDrugResponse dispenseDrug(DispenseDrugRequest dispenseDrugRequest) {
        Drug drug = drugs.findById(dispenseDrugRequest.getId());
        if(drug == null) throw new IllegalArgumentException("Drug Does not exist!!!");
//        IO.print(drug.getQuantity());
        drug.setQuantity(drug.getQuantity() - dispenseDrugRequest.getQuantity());

        drugs.save(drug);

        DispenseDrugResponse dispenseDrugResponse = new DispenseDrugResponse();
        dispenseDrugResponse.setId(drug.getId());
        dispenseDrugResponse.setQuantity(drug.getQuantity());
        dispenseDrugResponse.setDescription(drug.getDescription());
        dispenseDrugResponse.setDrugName(drug.getDrugName());
        dispenseDrugResponse.setMessage("Drug dispensed successfully");
        dispenseDrugResponse.setBrand(drug.getBrand());
        dispenseDrugResponse.setPrice(drug.getPrice());

        return dispenseDrugResponse;
    }
}
