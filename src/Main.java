import java.io.*;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress gp1 = new GameProgress(100, 25, 1, 2.0);
        GameProgress gp2 = new GameProgress(99, 125, 2, 4.1);
        GameProgress gp3 = new GameProgress(67, 225, 5, 8.3);

        File savegames = new File("C://Games/Games/savegames");

        File sgpFile1 = new File(savegames, "savegp1.sgp");
        File sgpFile2 = new File(savegames, "savegp2.sgp");
        File sgpFile3 = new File(savegames, "savegp3.sgp");
        try {
            sgpFile1.createNewFile();
            sgpFile2.createNewFile();
            sgpFile3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter inFile1 = new FileWriter(sgpFile1, false);
             FileWriter inFile2 = new FileWriter(sgpFile2, false);
             FileWriter inFile3 = new FileWriter(sgpFile3, false)) {
            inFile1.write(String.valueOf(gp1));
            inFile2.write(String.valueOf(gp2));
            inFile3.write(String.valueOf(gp3));
            inFile1.flush();
            inFile2.flush();
            inFile3.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String zipFile = "C://Games/Games/savegames/saves.zip";
        String[] srcFiles = {"C://Games/Games/savegames/savegp1.sgp", "C://Games/Games/savegames/savegp2.sgp", "C://Games/Games/savegames/savegp3.sgp"};

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.length; i++) {
                File srcFile = new File(srcFiles[i]);
                ZipEntry zipEntry = new ZipEntry(srcFile.getName());
                zos.putNextEntry(zipEntry);
                zos.write(Files.readAllBytes(srcFile.toPath()));
                zos.closeEntry();
            }
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sgpFile1.delete();
        sgpFile2.delete();
        sgpFile3.delete();

    }
}