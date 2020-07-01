package com.java_simple.codes

import java.lang.StringBuilder


/**
 * Created by shishoufeng on 2020/4/27.
 *
 * desc : 日志打印相关
 *
 *
 */

fun out(string: String){
    println(string)
}

fun out(any: Any){
    println(any)
}

fun <K, V> out(map: Map<K, V>) {
    val it = map.iterator()
    val sb = StringBuilder()
    sb.append("[")
    while (it.hasNext()) {
        val entry = it.next()
        sb.append("(")
            .append(entry.key)
            .append(",")
            .append(entry.value)
            .append(") ,")
    }
    sb.append("]")

    out(sb.toString())
}