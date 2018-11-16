package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private boolean exist;
    private Node deleted;
    /* Removes all of the mappings from this map. */

    @Override
    public void clear() {
        root = null;
        size = 0;
        exist = false;
    }
    /* Creates an empty BSTMap. */
    public BSTMap() {
        clear();
    }



    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null) {
            return null;
        } else if(key.compareTo(p.key) == 0) {
            return p.value;
        } else if(key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        } else {
            return getHelper(key, p.left);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null) {
            return new Node(key, value);
        } else if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
            return p;
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
            return p;
        } else {
            exist = true;
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
        if (!exist) {
            size += 1;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return this.size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void getSetHelper(Node root, Set<K> hashset) {
        if(root != null) {
            hashset.add(root.key);
            getSetHelper(root.left, hashset);
            getSetHelper(root.right, hashset);
        }
    }
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        getSetHelper(root, set);
        return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node elevate(Node node) {
        if(node.left != null && node.right != null) {
            node.left.right = node.right;
            return node.left;
        } else if(node.left != null) {
            return node.left;
        } else {
            return null;
        }
    }

    private Node removeHelper(K key, Node root) {
        if(key.compareTo(root.key) == 0) {
            deleted = root;
            return elevate(root);
        } else if(key.compareTo(root.key) > 0) {
            root.right = removeHelper(key, root.right);
            return root;
        } else {
            root.left = removeHelper(key, root.left);
            return root;
        }
    }
    @Override
    public V remove(K key) {
        root = removeHelper(key, root);
        V delValue = deleted.value;
        deleted = null;
        return delValue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    private Node removeHelper(K key, Node root, V value) {
        if(key.compareTo(root.key) == 0 && value == root.value) {
            deleted = root;
            return elevate(root);
        } else if(key.compareTo(root.key) > 0) {
            root.right = removeHelper(key, root.right);
            return root;
        } else {
            root.left = removeHelper(key, root.left);
            return root;
        }
    }
    @Override
    public V remove(K key, V value) {
        removeHelper(key, root, value);
        V delValue = deleted.value;
        return delValue;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
