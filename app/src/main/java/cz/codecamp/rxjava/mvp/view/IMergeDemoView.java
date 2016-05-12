package cz.codecamp.rxjava.mvp.view;

import android.support.annotation.NonNull;

import java.util.List;

import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.mvp.view.base.IBaseView;

/**
 * View for Merge demo
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {12.5.16}
 **/
public interface IMergeDemoView extends IBaseView {
    void setDataToView(@NonNull List<User> users);
}
