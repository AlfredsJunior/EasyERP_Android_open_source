package com.thinkmobiles.easyerp.domain.crm;

import android.net.Uri;

import com.thinkmobiles.easyerp.data.api.Rest;
import com.thinkmobiles.easyerp.data.model.crm.common.alphabet.ResponseGetAlphabet;
import com.thinkmobiles.easyerp.data.model.crm.common.images.ResponseGetCustomersImages;
import com.thinkmobiles.easyerp.data.model.crm.companies.ResponseGetCompanies;
import com.thinkmobiles.easyerp.data.model.crm.companies.detail.ResponseGetCompanyDetails;
import com.thinkmobiles.easyerp.data.model.crm.filter.ResponseFilters;
import com.thinkmobiles.easyerp.data.services.CompaniesService;
import com.thinkmobiles.easyerp.data.services.CustomerService;
import com.thinkmobiles.easyerp.data.services.FilterService;
import com.thinkmobiles.easyerp.presentation.base.NetworkRepository;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.CompaniesContract;
import com.thinkmobiles.easyerp.presentation.screens.crm.companies.details.CompanyDetailsContract;
import com.thinkmobiles.easyerp.presentation.utils.Constants;
import com.thinkmobiles.easyerp.presentation.utils.filter.FilterHelper;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Lynx on 2/2/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class CompaniesRepository extends NetworkRepository implements CompaniesContract.CompaniesModel, CompanyDetailsContract.CompanyDetailsModel {

    private CompaniesService companiesService;
    private CustomerService customerService;
    private FilterService filterService;

    public CompaniesRepository() {
        companiesService = Rest.getInstance().getCompaniesService();
        customerService = Rest.getInstance().getCustomerService();
        filterService = Rest.getInstance().getFilterService();
    }

    @Override
    public Observable<ResponseGetAlphabet> getAlphabet() {
        return getNetworkObservable(companiesService.getCompaniesAlphabet("Companies"));
    }

    @Override
    public Observable<ResponseGetCustomersImages> getCompanyImages(ArrayList<String> companyIdList) {
        return getNetworkObservable(customerService.getCustomerImages(companyIdList));
    }

    @Override
    public Observable<ResponseGetCompanyDetails> getCompanyDetails(String companyID) {
        return getNetworkObservable(companiesService.getCompanyDetails(companyID));
    }

    @Override
    public Observable<ResponseGetCompanies> getCompanies(FilterHelper helper, String letter, int page) {
        Uri.Builder builder = helper.createUrl(Constants.GET_COMPANIES, "Companies", page);
        if (!letter.equalsIgnoreCase("All")) {
            builder.appendQueryParameter("filter[letter][key]", "name.first")
                    .appendQueryParameter("filter[letter][value]", letter)
                    .appendQueryParameter("filter[letter][type]", "letter");
        }
        return getNetworkObservable(companiesService.getCompanies(builder.build().toString()));
    }

    @Override
    public Observable<ResponseFilters> getFilters() {
        return getNetworkObservable(filterService.getListFilters("Companies"));
    }
}
