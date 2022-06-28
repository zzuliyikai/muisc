package com.hanshow.myapplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yikai on 2022/6/12.
 * Describe:
 */
public class EventManager {
    public IEventManager iEventManager;


    private Set<IEventManager> hashSet = new HashSet<>();

    public void addEventListener(IEventManager eventListener) {
        hashSet.add(eventListener);
    }

    public void removeEventListener(IEventManager eventListener) {
        hashSet.remove(eventListener);
    }

    interface IEventManager {
        void onEvent(int event);
    }

}
