package assignments.assignment14_multithreading;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Simple File Download (Thread Interruption and Monitoring):

Create a thread to download a file from a URL.
Implement thread interruption to gracefully stop the download if needed (e.g., using a flag).
Monitor the download progress and provide feedback (e.g., percentage downloaded).
 */

public class Problem1 {
    public static void main(String[] args) {
        FileDownloader fileDownloader = new FileDownloader("https://files.eric.ed.gov/fulltext/EJ1080501.pdf", ".//assignments//assignment14_multithreading//downloaded.pdf");
        fileDownloader.start();
        try {
            Thread.sleep(5000);
            System.out.println("Starting Download");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fileDownloader.interrupt();
    }
} // download file

class FileDownloader extends Thread {
    private String fileURL;
    private String destination;
    private volatile boolean isDownloading = true;  // Volatile to ensure visibility across threads

    public FileDownloader(String fileURL, String destination) {
        this.fileURL = fileURL;
        this.destination = destination;
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            URL url = new URL(fileURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // Check if the connection is successful
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Failed to connect: " + connection.getResponseMessage());
                return;
            }

            int fileLength = connection.getContentLength();

            inputStream = connection.getInputStream();
            outputStream = new FileOutputStream(destination);

            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;
            int percentCompleted = 0;

            while (isDownloading && (bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                int newPercentCompleted = (int) (totalBytesRead * 100 / fileLength);
                if (newPercentCompleted > percentCompleted) {
                    percentCompleted = newPercentCompleted;
                    System.out.println("Download progress: " + percentCompleted + "%");
                }

                // Check if the thread has been interrupted
                if (Thread.interrupted()) {
                    System.out.println("Download interrupted");
                    isDownloading = false;
                }
            }

            if (isDownloading) {
                System.out.println("Download completed");
            } else {
                System.out.println("Download stopped at " + percentCompleted + "%");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}