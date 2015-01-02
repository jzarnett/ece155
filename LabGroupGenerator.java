import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LabGroupGenerator {


    private static final String prefix = "files/ECE-155-10";
    private static final String suffix = ".csv";
    private static final String labFile = "files/labgroups.csv";
    private static final String matchFile = "files/groups.txt";

    private static HashMap<String, String> students = new HashMap<String, String>();
    private static List<String> studentIDs = new ArrayList<String>();
    private static List<String> userIDs = new ArrayList<String>();
    private static List<LabGroup> labGroups = new ArrayList<LabGroup>();

    private static File outputFile = new File("files/mkdir.txt");

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

        for (int count = 1; count < 4; ++count) {
            BufferedReader br = new BufferedReader(new FileReader(prefix + count + suffix));


            String currentLine = br.readLine();
            while (currentLine != null) {
                String[] line = currentLine.split(",");
                String studentID = line[0];
                studentID = studentID.replace('"', ' ');
                studentID = studentID.trim();

                String userID = line[9];
                userID = userID.replace('"', ' ');
                userID = userID.trim();
                userID = userID.toLowerCase();


                students.put(studentID, userID);
                studentIDs.add(studentID);
                userIDs.add(userID);

                currentLine = br.readLine();
            }

            // Okay we read all the students into the hash map. Yay.

        }

        BufferedReader lr = new BufferedReader(new FileReader(labFile));

        String currentGroup = lr.readLine();

        while (currentGroup != null) {
            String[] group = currentGroup.split(",");

            LabGroup g = new LabGroup();
            g.section = group[0];
            g.groupNumber = group[1].length() == 1 ? "0" + group[1] : group[1];
            g.member1 = students.get(group[2]);
            if (group.length > 3) {
                g.member2 = students.get(group[3]);
            }
            if (group.length > 4) {
                g.member3 = students.get(group[4]);
            }

            labGroups.add(g);

            currentGroup = lr.readLine();
        }


        // At the end we need to output all of this:

        for (LabGroup l : labGroups) {
            bw.write("mkdir groups/group-" + l.section +  "-" + l.groupNumber + "\n");
        }

        for (String u : userIDs) {
            bw.write("mkdir students/" + u + "\n");
        }

        bw.write("\n\n");

        for (LabGroup l : labGroups) {
            bw.write("[/w15/groups/group-" + l.section + "-" + l.groupNumber + "]\n");
            bw.write(l.member1 + "=rw\n");
            if (l.member2 != null) {
                bw.write(l.member2 + "=rw\n");
            }
            if (l.member3 != null) {
                bw.write(l.member3 + "=rw\n");
            }
            bw.write("@w15-students=\n\n");
        }


        for (String uID : userIDs) {
            bw.write("[/w15/students/" + uID + "]\n");
            bw.write(uID + "=rw\n");
            bw.write("@w15-students=\n\n");
        }


        bw.write("\n\n");
        for (String userID : userIDs) {
            bw.write(userID + ", ");
        }


        bw.close();


        BufferedWriter bw2 = new BufferedWriter(new FileWriter(matchFile));
        for (LabGroup l : labGroups) {
            String line = l.section + "-" + l.groupNumber + ": " + l.member1;
            if (l.member2 != null) {
                line += " " + l.member2;
            }
            if (l.member3 != null) {
                line += " " + l.member3;
            }
            line += "\n";

            bw2.write(line);

        }

        bw2.close();

    }

    private static class LabGroup {

        public String section;
        public String groupNumber;
        public String member1;
        public String member2;
        public String member3;

    }


}
