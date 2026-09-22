class SegmentTree {
    private int k;
    private int n;
    private int[][] cnt;
    private int[] prod;

    public SegmentTree(int[] a, int k) {
        this.k = k;
        this.n = a.length;
        this.cnt = new int[4 * n][k];
        this.prod = new int[4 * n];
        build(a, 1, 0, n - 1);
    }

    private void pull(int u) {
        int l = u << 1;
        int r = l | 1;
        prod[u] = (prod[l] * prod[r]) % k;

        for (int i = 0; i < k; i++) {
            cnt[u][i] = cnt[l][i];
        }
        for (int i = 0; i < k; i++) {
            cnt[u][(prod[l] * i) % k] += cnt[r][i];
        }
    }

    private void build(int[] a, int u, int l, int r) {
        if (l == r) {
            int m = a[l] % k;
            prod[u] = m;
            cnt[u][m] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(a, u << 1, l, mid);
        build(a, (u << 1) | 1, mid + 1, r);
        pull(u);
    }

    public void update(int u, int l, int r, int idx, int val) {
        if (l == r) {
            int m = val % k;
            prod[u] = m;
            for (int i = 0; i < k; i++) {
                cnt[u][i] = 0;
            }
            cnt[u][m] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(u << 1, l, mid, idx, val);
        } else {
            update((u << 1) | 1, mid + 1, r, idx, val);
        }
        pull(u);
    }

    public void query(int u, int l, int r, int ql, int qr, int[] resCnt, int[] curProd) {
        if (ql <= l && r <= qr) {
            for (int i = 0; i < k; i++) {
                resCnt[(curProd[0] * i) % k] += cnt[u][i];
            }
            curProd[0] = (curProd[0] * prod[u]) % k;
            return;
        }
        int mid = (l + r) >> 1;
        if (ql <= mid) {
            query(u << 1, l, mid, ql, qr, resCnt, curProd);
        }
        if (qr > mid) {
            query((u << 1) | 1, mid + 1, r, ql, qr, resCnt, curProd);
        }
    }
}

class Solution {
    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        SegmentTree tree = new SegmentTree(nums, k);
        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int st = queries[i][2];
            int x = queries[i][3];

            tree.update(1, 0, n - 1, idx, val);

            int[] resCnt = new int[k];
            int[] curProd = new int[]{1};
            tree.query(1, 0, n - 1, st, n - 1, resCnt, curProd);

            ans[i] = resCnt[x];
        }

        return ans;
    }
}