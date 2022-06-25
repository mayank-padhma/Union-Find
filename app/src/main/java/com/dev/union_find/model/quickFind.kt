package com.dev.union_find.model

class quickFind {
    var arr = IntArray(21){it};
    var sz = IntArray(21){1};

    private fun root(a: Int): Number{
        var ab = a;
        while (arr[ab]!=ab){
            arr[ab] = arr[arr[ab]]
            ab = arr[ab]
        }
        return ab;
    }
    fun isConnected(a: Int, b:Int): Boolean{
        return root(a)==root(b)
    }
    fun union(a: Int, b: Int){
        val i: Int = root(a) as Int
        val j: Int = root(b) as Int
        if (i == j) return
        if (sz[i] < sz[j]) {
            arr[i] = j
            sz[j] += sz[i]
        } else {
            arr[j] = i
            sz[i] += sz[j]
        }
    }
}