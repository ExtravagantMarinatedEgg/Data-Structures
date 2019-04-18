package unionFind;

//QuickUnion 树结构孩子指向父亲  操作时间复杂度Oh
//合并深度 深度小的合并到深度大的上 总深度不变 相等深度合并深度加一
//路径压缩
//            parent[p] = parent[parent[p]];
public class UnionFion5 implements UF {

    private int[] parent;
    //sz[i] 表示以i为根的集合中元素个数
    private int[] rank;
    //为排名 低的在高的下面（近似理解成为深度）

    public UnionFion5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p) {

        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        while (p != parent[p]) {
            p = parent[p];
            //路径压缩
            parent[p] = parent[parent[p]];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
