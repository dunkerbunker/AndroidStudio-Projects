package com.example.fetchdatafromweb

class Model {
    // modal class with properties
    // 1) page
    // 2) per_page
    // 3) total
    // 4) total_pages
    // 5) data

    private var page: Int = 0
    private var per_page: Int = 0
    private var total: Int = 0
    private var total_pages: Int = 0
    private var data: ArrayList<Data> = ArrayList()

    // getter and setter methods
    fun getPage(): Int {
        return page
    }
    fun setPage(page: Int) {
        this.page = page
    }
    fun getPer_page(): Int {
        return per_page
    }
    fun setPer_page(per_page: Int) {
        this.per_page = per_page
    }
    fun getTotal(): Int {
        return total
    }
    fun setTotal(total: Int) {
        this.total = total
    }
    fun getTotal_pages(): Int {
        return total_pages
    }
    fun setTotal_pages(total_pages: Int) {
        this.total_pages = total_pages
    }
    fun getData(): ArrayList<Data> {
        return data
    }
    fun setData(data: ArrayList<Data>) {
        this.data = data
    }

    class Data {
        private var id: Int = 0
        private var email: String = ""
        private var first_name: String = ""
        private var last_name: String = ""
        private var avatar: String = ""

        fun getId(): Int {
            return id
        }
        fun setId(id: Int) {
            this.id = id
        }

        fun getEmail(): String {
            return email
        }
        fun setEmail(email: String) {
            this.email = email
        }

        fun getFirst_name(): String {
            return first_name
        }
        fun setFirst_name(first_name: String) {
            this.first_name = first_name
        }

        fun getLast_name(): String {
            return last_name
        }
        fun setLast_name(last_name: String) {
            this.last_name = last_name
        }

        fun getAvatar(): String {
            return avatar
        }
        fun setAvatar(avatar: String) {
            this.avatar = avatar
        }
    }
}