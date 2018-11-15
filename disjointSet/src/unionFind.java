public class unionFind {
    private int[] array;

    public unionFind(int N) {
        array = new int[N];
        for(int i = 0; i < N; i++) {
            array[i] = i;
        }
    }

    public boolean isConnected(int p, int q) {
        return array[p] == array[q];
    }

    public void connect(int p, int q) {
        int pRoot = array[p];
        int qRoot = array[q];
        for(int i = 0; i < array.length; i++) {
            if(array[i] == pRoot) {
                array[i] = qRoot;
            }
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
        unionFind uf = new unionFind(10);
        uf.connect(1,2);
        uf.connect(3,4);
        uf.connect(7,8);
        uf.connect(3,8);
        uf.connect(1,8);
        System.out.println(uf.toString());
    }
}
