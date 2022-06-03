package com.challenge.moviesbattle.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;


@Slf4j
public class RunApiExternal {

    public static void run() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows

        if (SystemUtils.IS_OS_WINDOWS){
            processBuilder.command("cmd.exe", "/c", "java -jar imdb-rest-api.jar");
        } else {
            processBuilder.command("bash", "-c", "java -jar imdb-rest-api.jar");
        }
        try {
            processBuilder.start();

        } catch (IOException e) {
           log.error(e.getMessage());

        }

    }

    public static void main(String[] args) {
//        stop();
    }

    public static void stop() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        if (SystemUtils.IS_OS_WINDOWS){
            processBuilder.command("stopProcessWin.bat");
        } else {
            processBuilder.command("stopProcessLinux.sh");
        }

        try {
            processBuilder.start();

        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
