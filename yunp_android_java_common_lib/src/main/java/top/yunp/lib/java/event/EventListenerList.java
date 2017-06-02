/**
 * Copyright [plter] [xtiqin]
 * http://plter.sinaapp.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * This is a part of plter-lib on
 * https://github.com/plter/plter-lib
 */

package top.yunp.lib.java.event;

import top.yunp.lib.java.lang.Array;
import top.yunp.lib.java.lang.ArrayItem;
import top.yunp.lib.java.lang.ArrayLoopCallback;

public class EventListenerList<E extends Event, EventTarget> {

    public void add(EventListener<E, EventTarget> listener) {
        eList.push(listener);
    }

    public void add(EventListener<E, EventTarget> listener, int index) {
        eList.insert(listener, index);
    }

    public void remove(EventListener<E, EventTarget> listener) {
        eList.remove(listener);
    }

    public void remove(final String name) {
        eList.each(new ArrayLoopCallback<EventListener<E, EventTarget>>() {

            @Override
            public void onRead(EventListener<E, EventTarget> currentValue,
                               ArrayItem<EventListener<E, EventTarget>> currentItem) {
                if (currentValue.getName() != null && currentValue.getName().equals(name)) {
                    eList.removeItem(currentItem);
                    break_();
                }
            }
        });
    }

    public void remove() {
        eList.clear();
    }


    private boolean _dispatchSuc = true;

    public boolean dispatch(final E event, final EventTarget target) {

        _dispatchSuc = true;

        eList.each(new ArrayLoopCallback<EventListener<E, EventTarget>>() {

            @Override
            public void onRead(EventListener<E, EventTarget> currentValue,
                               ArrayItem<EventListener<E, EventTarget>> currentItem) {
                if (currentValue.getName() == null ||
                        event.getName() == null ||
                        event.getName().equals(currentValue.getName())) {

                    if (!currentValue.onReceive(event, target)) {
                        _dispatchSuc = false;
                    }

                    if (event.isStopped()) {
                        break_();
                    }
                }
            }
        });
        return _dispatchSuc;
    }


    public boolean dispatch(E event) {
        return dispatch(event, null);
    }

    private final Array<EventListener<E, EventTarget>> eList = new Array<>();

}
