public class weightedQuickUnionWithPathCompression {
    private int[] array;
    private int[] size;

    public weightedQuickUnionWithPathCompression(int N) {
        array = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            array[i] = i;
            size[i] = 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        if(p == array[p]){
            return p;
        } else {
            array[p] = find(array[p]);
            return array[p];
        }
    }

    public void connect(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if(size[pRoot] > size[qRoot]) {
            array[qRoot] = pRoot;
            size[qRoot] += size[pRoot];
        } else {
            array[pRoot] = qRoot;
            size[pRoot] += size[qRoot];
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        return new String(builder);
    }
    public static void main(String[] args) {
        weightedQuickUnionWithPathCompression uf = new weightedQuickUnionWithPathCompression(10);
        uf.connect(1,2);
        uf.connect(3,4);
        uf.connect(7,8);
        uf.connect(3,8);
        uf.connect(1,8);
        uf.connect(3,7);
        System.out.println(uf.toString());
    }
}
