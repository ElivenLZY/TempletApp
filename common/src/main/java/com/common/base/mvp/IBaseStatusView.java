package com.common.base.mvp;

/**
 * Created by gmfbilu on 2017/2/24.
 */

public interface IBaseStatusView extends IBaseView {

    void showContentV();

    void showLoadingV();

    void showErrorV();

    void showEmptyV();

    void showNoNetworkV();

    void showNoLoginV();

}
