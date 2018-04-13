package com.facebank.usersupport.dto;

import com.facebank.usersupport.model.CustomerPictureModel;
import com.facebank.usersupport.model.CustomerProblemDescriptionModel;
import com.facebank.usersupport.model.CustomerProblemSolveModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJ
 * @create
 **/
public class CustomerProblemDto {

    private List<CustomerPictureModel> customerPictureModels = new ArrayList<>();

    private List<CustomerProblemDescriptionModel> customerProblemDescriptionModels = new ArrayList<>();

    private List<CustomerProblemSolveModel> customerProblemSolves = new ArrayList<>();

    private CustomerServiceDto customerServiceDto;

    private Long id;

    private Long typeId;

    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CustomerPictureModel> getCustomerPictureModels() {
        return customerPictureModels;
    }

    public void setCustomerPictureModels(List<CustomerPictureModel> customerPictureModels) {
        this.customerPictureModels = customerPictureModels;
    }

    public List<CustomerProblemDescriptionModel> getCustomerProblemDescriptionModels() {
        return customerProblemDescriptionModels;
    }

    public void setCustomerProblemDescriptionModels(List<CustomerProblemDescriptionModel> customerProblemDescriptionModels) {
        this.customerProblemDescriptionModels = customerProblemDescriptionModels;
    }

    public List<CustomerProblemSolveModel> getCustomerProblemSolves() {
        return customerProblemSolves;
    }

    public void setCustomerProblemSolves(List<CustomerProblemSolveModel> customerProblemSolves) {
        this.customerProblemSolves = customerProblemSolves;
    }

    public CustomerServiceDto getCustomerServiceDto() {
        return customerServiceDto;
    }

    public void setCustomerServiceDto(CustomerServiceDto customerServiceDto) {
        this.customerServiceDto = customerServiceDto;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
