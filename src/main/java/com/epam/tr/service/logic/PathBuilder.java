package com.epam.tr.service.logic;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.StringJoiner;

import static java.util.Objects.nonNull;

@Component
public class PathBuilder {
    public String createPath(String drive, MultiValueMap<String, String> allRequestParams) {
        String fullPath = new String();
        if (nonNull(drive)) {
            List<String> folders = allRequestParams.get("folder");
            StringJoiner joiner = new StringJoiner("\\");
            if (nonNull(folders)) {
                for (String folder : folders) {
                    joiner.add(folder);
                }
            }
            String pathToFile = joiner.toString();
            fullPath = drive + ":\\" + pathToFile;
        }
        return fullPath;
    }
}
