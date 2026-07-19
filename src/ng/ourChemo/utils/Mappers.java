package ng.ourChemo.utils;

import ng.ourChemo.data.models.Drug;
import ng.ourChemo.data.models.User;
import ng.ourChemo.dtos.requests.AddDrugRequest;
import ng.ourChemo.dtos.requests.RegisterUserRequest;
import ng.ourChemo.dtos.responses.AddDrugResponse;
import ng.ourChemo.dtos.responses.RegisterUserResponse;

public class Mappers {
    public static User mapToUserRequest(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUserName(registerUserRequest.getUserName().toLowerCase());
        user.setFullName(registerUserRequest.getFullName());
        user.setPassword(registerUserRequest.getPassword());
        return user;
    }

    public static RegisterUserResponse mapToUserResponse(User user) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setUserName(user.getUserName().toLowerCase());
        response.setFullName(user.getFullName());
        return response;
    }

    public static Drug mapDrugRequest(AddDrugRequest addDrugRequest) {
        Drug drug = new Drug();
        drug.setDrugName(addDrugRequest.getDrugName());
        drug.setBrand(addDrugRequest.getBrand());
        drug.setPrice(addDrugRequest.getPrice());
        drug.setDescription(addDrugRequest.getDescription());
        drug.setExpiryDate(addDrugRequest.getExpiryDate());
        drug.setQuantity(addDrugRequest.getQuantity());
        return drug;
    }

    public static AddDrugResponse mapDrugResponse(Drug drug) {
        AddDrugResponse addDrugResponse = new AddDrugResponse();

        addDrugResponse.setId(drug.getId());
        addDrugResponse.setDrugName(drug.getDrugName());
        addDrugResponse.setBrand(drug.getDrugName());
        addDrugResponse.setPrice(drug.getPrice());
        addDrugResponse.setDescription(drug.getDescription());
        addDrugResponse.setExpiryDate(drug.getExpiryDate());
        addDrugResponse.setQuantity(drug.getQuantity());
        return addDrugResponse;
    }


}
