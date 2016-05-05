package cz.codecamp.mvp.view;

import java.util.List;

import cz.codecamp.mvp.domain.Quake;
import cz.codecamp.mvp.view.base.IBaseView;

/**
 * View for {@link cz.codecamp.mvp.ui.fragment.EarthQuakeFragment}
 *
 * @author Michal Kučera [michal.kucera@ackee.cz]
 * @since {05/05/16}
 **/
public interface IEarthQuakeView extends IBaseView {

    void showData(List<Quake> quakeList);
}
