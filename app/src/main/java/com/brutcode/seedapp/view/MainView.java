package com.brutcode.seedapp.view;

import com.brutcode.seedapp.model.Content;

/**
 * Created by Danilo on 06/07/2016.
 */
public interface MainView extends BaseView{
    void presentContent(boolean contains, Content content);
    void runOnUi(Runnable runnable);
}
