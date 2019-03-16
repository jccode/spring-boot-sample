package org.springframework.cloud.sleuth.instrument.quartz;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Quartz tracing.
 *
 * @author 01372461
 */
@ConfigurationProperties("spring.sleuth.quartz")
public class SleuthQuartzProperties {

    private boolean enabled = true;

    /**
     * Pattern for the fully qualified name of a class that should be skipped.
     */
    private String skipPattern = "";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSkipPattern() {
        return skipPattern;
    }

    public void setSkipPattern(String skipPattern) {
        this.skipPattern = skipPattern;
    }
}
