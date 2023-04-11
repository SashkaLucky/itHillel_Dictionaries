package org.example.File;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileNavigator {
    private Map<String, List<FileData>> fileMap;

    public FileNavigator() {
        this.fileMap = new HashMap<>();
    }

    public void add(FileData file) {
        String path = file.getPath();
        String[] pathSegments = path.split("/");
        StringBuilder sb = new StringBuilder();
        for (String segment : pathSegments) {
            if (!segment.isEmpty()) {
                sb.append("/").append(segment);
                if (!this.fileMap.containsKey(sb.toString())) {
                    this.fileMap.put(sb.toString(), new ArrayList<>());
                }
            }
        }
        if (sb.toString().equals(path)) {
            List<FileData> fileList = this.fileMap.get(path);
            if (fileList != null) {
                fileList.add(file);
            } else {
                List<FileData> newFileList = new ArrayList<>();
                newFileList.add(file);
                this.fileMap.put(path, newFileList);
            }
        } else {
            System.out.println("Error: inconsistent path");
        }
    }

    public List<FileData> find(String path) {
        return this.fileMap.get(path);
    }

    public List<FileData> filterBySize(long maxSize) {
        List<FileData> result = new ArrayList<>();
        for (List<FileData> fileList : this.fileMap.values()) {
            for (FileData file : fileList) {
                if (file.getSize() <= maxSize) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    public void remove(String path) {
        this.fileMap.remove(path);
    }

    public List<FileData> sortBySize() {
        List<FileData> result = new ArrayList<>();
        for (List<FileData> fileList : this.fileMap.values()) {
            result.addAll(fileList);
        }
        result.sort(Comparator.comparingLong(FileData::getSize));
        return result;
    }
}
