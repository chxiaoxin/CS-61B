public class quickUnion {
    private int[] array;

    public quickUnion(int N) {
        array = new int[N];
        for(int i = 0; i < N; i++) {
            array[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while(array[p] != p) {
            p = array[p];
        }
        return p;
    }

    public void connect(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        array[pRoot] = qRoot;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            builder.append(array[i]);
        }
        return new String(builder);
    }
    public static void main(String[] args) {
        quickUnion uf = new quickUnion(10);
        uf.connect(1,2);
        uf.connect(3,4);
        uf.connect(7,8);
        uf.connect(3,8);
        uf.connect(1,8);
        System.out.println(uf.toString());
    }
}
