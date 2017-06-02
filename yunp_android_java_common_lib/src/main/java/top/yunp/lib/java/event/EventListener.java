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
 * This is a part of PlterAndroidLib on
 * http://plter.sinaapp.com/?cat=14
 * https://github.com/xtiqin/plter-android-lib
 */

package top.yunp.lib.java.event;

public abstract class EventListener<E extends Event, EventTarget> {


    public EventListener() {
        this(null);
    }

    public EventListener(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean onReceive(E event, EventTarget target);


    private String name = null;
}
