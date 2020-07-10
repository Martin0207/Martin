package com.martin.lib_base.pojo.bean

import java.util.*

class TypeBean {
    /**
     * name : 每日推荐
     * flag : 1
     * column_id : 0
     * sort : 1
     * view : 1
     * data : [{"id":330,"name":"朋友, 女朋友","image":"cartoon-cover/20190807/1565139628998.jpg","serialize":1,"last_chapter_sort":32,"resource_type":1,"area_id":2},{"id":328,"name":"别人的老婆","image":"cartoon-cover/20190731/1564534898927.jpg","serialize":1,"last_chapter_sort":36,"resource_type":1,"area_id":2},{"id":318,"name":"人夫的悸动","image":"cartoon-cover/20190706/156240269563.jpg","serialize":1,"last_chapter_sort":42,"resource_type":1,"area_id":2},{"id":283,"name":"KTV情人","image":"cartoon-cover/20190422/1555903320616.jpg","serialize":1,"last_chapter_sort":63,"resource_type":1,"area_id":2},{"id":234,"name":"偷窥","image":"cartoon-cover/20190510/1557471208472.jpg","serialize":1,"last_chapter_sort":160,"resource_type":1,"area_id":2},{"id":168,"name":"阿姨的秘密情事","image":"cartoon-cover/20190420/1555751288383.jpg","serialize":1,"last_chapter_sort":61,"resource_type":1,"area_id":2}]
     */
    var name: String? = null
    var flag = 0
    var column_id = 0
    var sort = 0
    var view: String? = null
    var data: ArrayList<DataBean>? = null

    class DataBean {
        /**
         * id : 330
         * name : 朋友, 女朋友
         * image : cartoon-cover/20190807/1565139628998.jpg
         * serialize : 1
         * last_chapter_sort : 32
         * resource_type : 1
         * area_id : 2
         */
        var id = 0
        var name: String? = null
        var image: String? = null
        var serialize = 0
        var last_chapter_sort = 0
        var resource_type = 0
        var area_id = 0

    }
}