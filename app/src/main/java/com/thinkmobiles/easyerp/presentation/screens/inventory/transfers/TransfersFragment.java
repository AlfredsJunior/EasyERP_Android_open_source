package com.thinkmobiles.easyerp.presentation.screens.inventory.transfers;

import com.thinkmobiles.easyerp.domain.inventory.TransfersRepository;
import com.thinkmobiles.easyerp.presentation.adapters.inventory.TransfersAdapter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.FilterablePresenter;
import com.thinkmobiles.easyerp.presentation.base.rules.master.filterable.MasterFilterableFragment;
import com.thinkmobiles.easyerp.presentation.base.rules.master.selectable.SelectableAdapter;
import com.thinkmobiles.easyerp.presentation.managers.GoogleAnalyticHelper;
import com.thinkmobiles.easyerp.presentation.screens.inventory.transfers.details.TransferDetailsFragment_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Lynx on 3/7/2017.
 */

@EFragment
public class TransfersFragment extends MasterFilterableFragment implements TransfersContract.TransfersView {

    private TransfersContract.TransfersPresenter presenter;

    @Bean
    protected TransfersRepository transfersRepository;
    @Bean
    protected TransfersAdapter transfersAdapter;

    @AfterInject
    @Override
    public void initPresenter() {
        new TransfersPresenter(this, transfersRepository);
    }

    @Override
    public void setPresenter(TransfersContract.TransfersPresenter presenter) {
        this.presenter = presenter;
    }

    @AfterViews
    protected void initAnalytics() {
        GoogleAnalyticHelper.trackScreenView(this, getResources().getConfiguration());
    }

    @Override
    public String getScreenName() {
        return "Transfers list screen";
    }

    @Override
    protected SelectableAdapter getAdapter() {
        return transfersAdapter;
    }

    @Override
    public void openDetailsScreen(String transferID) {
        if (transferID != null) {
            getMasterDelegate().replaceFragmentContentDetail(TransferDetailsFragment_.builder()
                    .id(transferID)
                    .build());
        } else {
            getMasterDelegate().replaceFragmentContentDetail(null);
        }
    }

    @Override
    protected FilterablePresenter getPresenter() {
        return presenter;
    }
}
