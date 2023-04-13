package Launch;

import java.io.*;
import java.util.ArrayList;

public class FileRead {
    
    public ArrayList<String> list(int difficult){
        ArrayList<String> list = new ArrayList<>();
        File file = new File("src\\LeaderBoard\\LeaderBoard.txt");
        String[] difLine ={"Beginner", "Intermediater", "Expert", "null"};
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (!line.equals(difLine[difficult - 1])) {
                line = reader.readLine();
            }
            while (line != null) {
                if (line.equals(difLine[difficult - 1])) {
                    line = reader.readLine();
                }
                if (line.equals(difLine[difficult])) {
                    break;
                }
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("error");
        }
        return list;
    }

    public Boolean addName(int difficult, String name, int score) {
        ArrayList<String> lboard = new ArrayList<>();
        String fileName = "src\\LeaderBoard\\LeaderBoard.txt";
        File file = new File(fileName);
        String[] difLine ={"Beginner", "Intermediater", "Expert", "null"};
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lboard.add(line);
                line = reader.readLine();
            }
            int startIndex = 0, endIndex = 0;
            while (!lboard.get(startIndex).equals(difLine[difficult - 1])) {
                startIndex++;
            }
            while (!lboard.get(endIndex).equals(difLine[difficult])) {
                endIndex++;
            }
            if (startIndex != endIndex - 1) {
                for (int i = endIndex - 1; i > startIndex; i--) {
                    String[] ranking= lboard.get(i).split(" ");
                    if (i == startIndex + 1) {
                        lboard.add(i, name + " " + score);
                        break;
                    } else {
                        if (!(score < Integer.parseInt(ranking[1]))) {
                            lboard.add(i, name + " " + score);
                            break;
                        }
                    }
                }
            } else {
                lboard.add(endIndex, name + " " + score);
            }
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < lboard.size(); i++) {
                fw.write(lboard.get(i) + "\n");
            }
            fw.close();
            reader.close();
        } catch (Exception e) {
        }
        return true;
    }
}
