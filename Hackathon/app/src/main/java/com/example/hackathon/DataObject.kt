package com.example.hackathon

object DataObject {
    // list to store all date required
    var listdata = mutableListOf<ScriptModel>()

    // functions to add data to list
    fun setData(title: String, regularity: String, date: String, script: String, type: String) {
        listdata.add(ScriptModel(title, regularity, date, script, type))
    }

    // function to get data from list
    fun getAllData(): List<ScriptModel> {
        return listdata
    }

    // function to delete data from list
    fun deleteAll(){
        listdata.clear()
    }

    // function to get specific data from list
    fun getData(pos:Int): ScriptModel {
        return listdata[pos]
    }

    // function to delete specific data from list
    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    // function to update specific data from list
    fun updateData(pos:Int,title: String, regularity: String, date: String, script: String, type: String)
    {
        listdata[pos].title=title
        listdata[pos].date=date
        listdata[pos].regularity=regularity
        listdata[pos].script=script
        listdata[pos].type=type
    }

}