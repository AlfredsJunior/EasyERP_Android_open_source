package com.thinkmobiles.easyerp.presentation.holders.data.crm;

import com.thinkmobiles.easyerp.data.model.crm.companies.CompanyListItem;
import com.thinkmobiles.easyerp.presentation.base.rules.MasterFlowSelectableDHHelper;

/**
 * Created by Lynx on 2/2/2017.
 */

public class CompanyDH extends MasterFlowSelectableDHHelper<String> {

    private CompanyListItem data;
    private String companyImageBase64;

    public CompanyDH(CompanyListItem data, String companyImageBase64) {
        this.data = data;
        this.companyImageBase64 = companyImageBase64;
    }

    public CompanyListItem getData() {
        return data;
    }

    public String getCompanyImageBase64() {
        return companyImageBase64;
    }

    @Override
    public String getId() {
        return data.id;
    }
}
