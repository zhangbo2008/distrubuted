package com.hc.distributed.designMode.graphicdesignpattern.watcher;

/**
 * ${space}
 *
 * @author HC
 * @create 2019-05-02 1:20
 */
public class WatcherTest
{
    public static void main(String[] args)
    {
        Transporter transporter = new Transporter();

        Police police = new Police();
        Security security = new Security();

        transporter.addWatcher(police);
        transporter.addWatcher(security);

        transporter.notifyWatchers();
    }
}

