You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents the coordinates of the bottom-left point and the side length of a square parallel to the x-axis.

Find the minimum y-coordinate value of a horizontal line such that the total area covered by squares above the line equals the total area covered by squares below the line.

Answers within 10-5 of the actual answer will be accepted.

Note: Squares may overlap. Overlapping areas should be counted only once in this version.

 

Example 1:

Input: squares = [[0,0,1],[2,2,1]]

Output: 1.00000

Explanation:



Any horizontal line between y = 1 and y = 2 results in an equal split, with 1 square unit above and 1 square unit below. The minimum y-value is 1.

Example 2:

Input: squares = [[0,0,2],[1,1,1]]

Output: 1.00000

Explanation:



Since the blue square overlaps with the red square, it will not be counted again. Thus, the line y = 1 splits the squares into two equal parts.

 

Constraints:

1 <= squares.length <= 5 * 104
squares[i] = [xi, yi, li]
squares[i].length == 3
0 <= xi, yi <= 109
1 <= li <= 109
The total area of all the squares will not exceed 1015.


SOLUTION:::
class Solution {
    public double separateSquares(int[][] squares) {
        int n = squares.length;
        long[] xs = new long[2 * n];
        int idx = 0;
        for (int[] s : squares) {
            long x = s[0];
            long l = s[2];
            xs[idx++] = x;
            xs[idx++] = x + l;
        }
        Arrays.sort(xs, 0, idx);
        int m = 0;
        for (int i = 0; i < idx; ++i) {
            if (i == 0 || xs[i] != xs[i - 1]) {
                xs[m++] = xs[i];
            }
        }
        long[] xCoord = Arrays.copyOf(xs, m);

        class Event {
            long y;
            int type;
            long x1, x2;
            Event(long y, int type, long x1, long x2) {
                this.y = y;
                this.type = type;
                this.x1 = x1;
                this.x2 = x2;
            }
        }

        Event[] events = new Event[2 * n];
        int eIdx = 0;
        for (int[] s : squares) {
            long x = s[0];
            long y = s[1];
            long l = s[2];
            events[eIdx++] = new Event(y, +1, x, x + l);
            events[eIdx++] = new Event(y + l, -1, x, x + l);
        }
        Arrays.sort(events, 0, eIdx, (a, b) -> Long.compare(a.y, b.y));

        class SegTree {
            int size;
            int[] cnt;
            long[] len;
            long[] X;

            SegTree(long[] X) {
                this.X = X;
                this.size = X.length - 1;
                int N = 4 * size;
                cnt = new int[N];
                len = new long[N];
            }

            void update(int ql, int qr, int val) {
                update(1, 0, size, ql, qr, val);
            }

            private void update(int node, int l, int r, int ql, int qr, int val) {
                if (ql >= r || qr <= l) return;
                if (ql <= l && r <= qr) {
                    cnt[node] += val;
                } else {
                    int mid = (l + r) >>> 1;
                    update(node << 1, l, mid, ql, qr, val);
                    update(node << 1 | 1, mid, r, ql, qr, val);
                }
                if (cnt[node] > 0) {
                    len[node] = X[r] - X[l];
                } else {
                    if (r - l == 1) {
                        len[node] = 0;
                    } else {
                        len[node] = len[node << 1] + len[node << 1 | 1];
                    }
                }
            }

            long totalLen() {
                return len[1];
            }
        }

        if (m <= 1) {
            long minY = Long.MAX_VALUE;
            for (int[] s : squares) minY = Math.min(minY, s[1]);
            return (double) minY;
        }

        SegTree st = new SegTree(xCoord);

        List<long[]> strips = new ArrayList<>();
        long prevY = events[0].y;
        for (int i = 0; i < eIdx; ++i) {
            Event ev = events[i];
            long y = ev.y;
            if (y != prevY) {
                long width = st.totalLen();
                if (width > 0) {
                    strips.add(new long[]{prevY, y, width});
                }
                prevY = y;
            }
            int lIdx = Arrays.binarySearch(xCoord, ev.x1);
            int rIdx = Arrays.binarySearch(xCoord, ev.x2);
            st.update(lIdx, rIdx, ev.type);
        }

        double totalArea = 0.0;
        for (long[] strip : strips) {
            long y1 = strip[0], y2 = strip[1], w = strip[2];
            totalArea += (y2 - y1) * (double) w;
        }
        double target = totalArea / 2.0;

        double pref = 0.0;
        for (long[] strip : strips) {
            long y1 = strip[0], y2 = strip[1], w = strip[2];
            double areaStrip = (y2 - y1) * (double) w;
            if (pref + areaStrip >= target - 1e-9) {
                double remaining = target - pref;
                double dy = remaining / w;
                return y1 + dy;
            }
            pref += areaStrip;
        }

        long maxY = prevY;
        return (double) maxY;
    }
}
