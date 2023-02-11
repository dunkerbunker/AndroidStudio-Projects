package com.example.task_list_app

object DataObject {
    // list to store all date required
    var listdata = mutableListOf<CardInfo>()

    // functions to add data to list
    fun setData(title: String, priority: String, date: String) {
        listdata.add(CardInfo(title, priority, date))
    }

    // function to get data from list
    fun getAllData(): List<CardInfo> {
        return listdata
    }

    // function to delete data from list
    fun deleteAll(){
        listdata.clear()
    }

    // function to get specific data from list
    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    // function to delete specific data from list
    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    // function to update specific data from list
    fun updateData(pos:Int,title:String,priority:String, date:String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].date=date
    }

}