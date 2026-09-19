class Solution {
    public boolean checkOverlap(int r, int xc, int yc, int x1, int y1, int x2, int y2) {
        int x = xc < x1 ? x1 : (xc > x2 ? x2 : xc);
        int y = yc < y1 ? y1 : (yc > y2 ? y2 : yc);
        int dx = xc - x;
        int dy = yc - y;
        return dx * dx + dy * dy <= r * r;
    }
}