package com.elca.app.exercise.model;
import com.elca.app.exercise.utils.MyUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ListCompany {

    private List<Company> lstCompany;

    public ListCompany(List<Company> lstCompany) {
        this.lstCompany = lstCompany;
    }

    public int calTotalCapitalOfHeadQuarter(String country){
        return lstCompany.stream()
                .filter(e -> e.getHeadQuarter() != null && e.getHeadQuarter() == true && e.getCountry().equals(country))
                .map(e -> e.getCapital())
                .reduce(0, Integer::sum);
    }

    public List<String> getListNameOfCompanyAtCountry(String country){
        return  lstCompany.stream()
                .filter(e->e.getCountry().equals(country))
                .sorted((a,b) -> b.getCapital() - a.getCapital())
                .map(e -> e.getName())
                .collect(Collectors.toList());
    }

    public void printAllCompany(){
        lstCompany.stream().forEach( c -> {
            MyUtils.logger.info(c);
        });
    }

    public List<Company> getLstCompany() {
        return lstCompany;
    }

    public void setLstCompany(List<Company> lstCompany) {
        this.lstCompany = lstCompany;
    }
}
