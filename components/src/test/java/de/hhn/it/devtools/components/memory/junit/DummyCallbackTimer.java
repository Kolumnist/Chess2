package de.hhn.it.devtools.components.memory.junit;

import de.hhn.it.devtools.apis.memory.TimerListener;

public class DummyCallbackTimer implements TimerListener {
    private static final org.slf4j.Logger logger =
            org.slf4j.LoggerFactory.getLogger(de.hhn.it.devtools.components.memory.junit.DummyCallbackTimer.class);

    @Override
    public void currentTime(int time) {
        logger.info("Callback called with time " + time);
    }
}
