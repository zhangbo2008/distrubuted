package com.hc.hcbasic.designMode.graphicdesignpattern.watcher;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 1:19
 */
public interface Watched {
    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatchers();
}
