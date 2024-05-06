package assignments.assignment14_multithreading;

/*
 * Simple File Download (Thread Interruption and Monitoring):

Create a thread to download a file from a URL.
Implement thread interruption to gracefully stop the download if needed (e.g., using a flag).
Monitor the download progress and provide feedback (e.g., percentage downloaded).
 */

public class Problem1 {
    public static void main(String[] args) {
        FileDownloader fileDownloader = new FileDownloader("sample url");
        fileDownloader.start();
        try {
            Thread.sleep(5000);
            System.out.println("Starting Download");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fileDownloader.interrupt();
    }
}

class FileDownloader extends Thread {
    private String url;
    private boolean isDownloading = true;
    
    public FileDownloader(String url) {
        this.url = url;
    }
    
    @Override
    public void run() {
        System.out.println("Downloading file from " + url);
        int downloaded = 0;
        while (isDownloading) {
            try {
                Thread.sleep(1000);
                downloaded += 10;
                System.out.println("Download progress " + downloaded + "%");
            } catch (InterruptedException e) {
                System.out.println("Download interrupted");
                isDownloading = false;
            }
        }
    }
}
