package neumont.edu.csc150.c.models;

public class Encryption {

    private int finalShiftCount = 7;
    private final String[][] cipher = {
            {"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}, {"i"}, {"j"}, {"k"}, {"l"}, {"m"}, {"n"}, {"o"}, {"p"}, {"q"}, {"r"}, {"s"}, {"t"}, {"u"}, {"v"}, {"w"}, {"x"}, {"y"}, {"z"}
    };

    public String encrypt(String pw) {
        String epw = "";
        for (char ch : pw.toCharArray()) {
            if (String.valueOf(ch).matches("[a-z]")) {
                for (int j = 0; j < cipher.length; j++) {
                    if (cipher[j][0].equals(String.valueOf(ch))) {
                        int reduceShift = (j + finalShiftCount) % 26;
                        epw += cipher[reduceShift][0];
                    } else {
                        continue;
                    }
                }
            }
        }
        return epw;
    }

    public String decrypt(String epw) {
        String pw = "";
        for (char ch : epw.toCharArray()) {
            if (String.valueOf(ch).matches("[a-z]")) {
                for (int j = 0; j < cipher.length; j++) {
                    if (cipher[j][0].equals(String.valueOf(ch))) {
                        int reduceShift = j - finalShiftCount;
                        if (reduceShift < 0)
                            reduceShift = (this.cipher.length + reduceShift) % 26;
                        pw += cipher[reduceShift][0];
                    } else {
                        continue;
                    }
                }
            }
        }
        return pw;
    }
}
