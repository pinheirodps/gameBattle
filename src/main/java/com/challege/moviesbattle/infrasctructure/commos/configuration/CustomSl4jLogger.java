package com.challege.moviesbattle.infrasctructure.commos.configuration;

import feign.Logger;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@NoArgsConstructor
public class CustomSl4jLogger extends Logger {


    @Override
    protected void log(String configKey, String format, Object... args) {
        String toFormat = methodTag(configKey) + format;
        log.debug(String.format(toFormat, args));
    }
}
