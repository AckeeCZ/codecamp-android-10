package cz.codecamp.rxjava.mvp.view;

import android.support.annotation.NonNull;

import java.util.List;

import cz.codecamp.rxjava.domain.model.User;
import cz.codecamp.rxjava.mvp.view.base.IBaseView;

/**
 * Interface for buffer demo
 * Created by Jan Stanek[jan.stanek@ackee.cz] on {11.5.16}
 **/
public interface IDebounceDemoView extends IBaseView {
    void setDataToView(@NonNull List<User> users);
}
