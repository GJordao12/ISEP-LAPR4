package eapli.helpdesk.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * Instantiates a new Utils.
     */
    private Utils() {
        //Nao usado
    }

    /**
     * Read line from console string.
     *
     * @param strPrompt the str prompt
     * @return the string
     */
    public static String readLineFromConsole(String strPrompt) {
        try {
            System.out.print(strPrompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Confirm boolean.
     *
     * @param sMessage the s message
     * @return the boolean
     */
    public static boolean confirm(String sMessage) {
        String strConfirm;
        do {
            strConfirm = Utils.readLineFromConsole("\n" + sMessage + "\n");
        } while (!Objects.requireNonNull(strConfirm).equalsIgnoreCase("s") && !strConfirm.equalsIgnoreCase("n"));

        return strConfirm.equalsIgnoreCase("s");
    }

    /**
     * Shows and select object.
     *
     * @param list    the list
     * @param sHeader the s header
     * @return the object
     */
    public static Object showsAndSelect(List list, String sHeader) {
        showList(list, sHeader);
        return selectObject(list);
    }

    /**
     * Shows and select index int.
     *
     * @param list    the list
     * @param sHeader the s header
     * @return the int
     */
    public static int showsAndSelectIndex(List list, String sHeader) {
        showList(list, sHeader);
        return selectIndex(list);
    }

    /**
     * Show list.
     *
     * @param list    the list
     * @param sHeader the s header
     */
    public static void showList(List list, String sHeader) {
        System.out.println(sHeader);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println();
        System.out.println("0 - Cancel\n");
    }

    /**
     * Select object object.
     *
     * @param list the list
     * @return the object
     */
    public static Object selectObject(List list) {
        String option;
        int nOption;
        do {
            option = Utils.readLineFromConsole("Option: ");
            assert option != null;
            nOption = Integer.parseInt(option);
        } while (nOption < 0 || nOption > list.size());

        if (nOption == 0) {
            return null;
        } else {
            return list.get(nOption - 1);
        }
    }

    /**
     * Select index int.
     *
     * @param list the list
     * @return the int
     */
    public static int selectIndex(List list) {
        String option;
        int nOption;
        do {
            option = Utils.readLineFromConsole("Option: ");
            assert option != null;
            nOption = Integer.parseInt(option);
        } while (nOption < 0 || nOption > list.size());
        return nOption - 1;
    }
}
