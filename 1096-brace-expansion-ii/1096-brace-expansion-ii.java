import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    private String s;
    private int p;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        p = 0;
        return new ArrayList<>(parseExpr());
    }

    private Set<String> parseItem() {
        Set<String> res = new TreeSet<>();
        if (s.charAt(p) == '{') {
            p++;
            res = parseExpr();
        } else {
            res.add(String.valueOf(s.charAt(p)));
        }
        p++;
        return res;
    }

    private Set<String> parseTerm() {
        Set<String> res = new TreeSet<>();
        res.add("");

        while (p < s.length() && (s.charAt(p) == '{' || Character.isLetter(s.charAt(p)))) {
            Set<String> cur = parseItem();
            Set<String> next = new TreeSet<>();
            for (String a : res) {
                for (String b : cur) {
                    next.add(a + b);
                }
            }
            res = next;
        }
        return res;
    }

    private Set<String> parseExpr() {
        Set<String> res = new TreeSet<>();
        while (true) {
            res.addAll(parseTerm());
            if (p < s.length() && s.charAt(p) == ',') {
                p++;
            } else {
                break;
            }
        }
        return res;
    }
}