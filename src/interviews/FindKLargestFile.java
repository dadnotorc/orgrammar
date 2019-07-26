package interviews;

import java.io.File;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find the kth largest file in the given a path of a directory.
 *
 * For example, if the given path is /home/Documents , it can contains
 *  multiple files and directories inside. Say abc.txt, directory1, file2.txt...
 * You have to traverse each directory and return the kth largest file.
 *
 * Amazon
 */
public class FindKLargestFile {
    /**
     * Given a path to a root folder, find k-th largest file under that path,
     *  including sub directories.
     *
     * @param path path to the root folder
     * @param k k-th largest file to find
     * @return the found file
     */
    public File getFile(String path, int k) {
        if (path == null || path.length() == 0 || k < 1) {
            return null;
        }

        // bigger file is stored in front of smaller file
        // So when poll(), the largest file will be removed
        PriorityQueue<File> q = new PriorityQueue<>(new Comparator<File>() {
            public int compare(File f1, File f2) {
                if (f1.length() < f2.length()) {
                    return 1;
                }
                else if (f1.length() > f2.length()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });

        File file = new File(path);
        parseFolder(file, q, k);
        return q.poll();
    }

    /**
     * Parse the given root folder, find all files under that folder,
     *  including its sub directories. The found files will be listed in the queue.
     * The queue only store up to k files, from the smallest to the k-th largest.
     * @param root root directory
     * @param q the queue for storing found files
     * @param k k-th largest file to find
     */
    private void parseFolder(File root, PriorityQueue q, int k) {
        for (File f: root.listFiles()) {
            if (f.isDirectory()) {
                parseFolder(f, q, k);
            } else {
                q.offer(f);
                if (q.size() > k) {
                    q.poll();
                }
            }
        }
    }
}
