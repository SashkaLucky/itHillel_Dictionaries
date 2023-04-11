package org.example;
import org.example.File.FileData;
import org.example.File.FileNavigator;

public class Main {
    public static void main(String[] args) {
        FileNavigator navigator = new FileNavigator();

        FileData file1 = new FileData("file1.txt", 100, "/path/to/file/");
        FileData file2 = new FileData("file2.txt", 50, "/path/to/file/");
        FileData file3 = new FileData("file3.txt", 200, "/another/path/");

        navigator.add(file1);
        navigator.add(file2);
        navigator.add(file3);

        System.out.println("Files at /path/to/file/: " + navigator.find("/path/to/file/"));
        System.out.println("Files with size <= 100 bytes: " + navigator.filterBySize(100));
        System.out.println("Sorted files by size: " + navigator.sortBySize());

        navigator.remove("/path/to/file/");

        System.out.println("Files at /path/to/file/ after removal: " + navigator.find("/path/to/file/"));
    }
}



