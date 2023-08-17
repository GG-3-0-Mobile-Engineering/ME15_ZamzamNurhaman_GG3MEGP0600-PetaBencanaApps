package com.example.petabencana.utils.helper

import com.example.petabencana.data.enums.DisasterType

object DisasterTypeHelper {

    fun getDisasterTypewithEnum(disaster : String) : String{
        val disasterFormat = disaster.replace(" ","")
        return DisasterType.valueOf(disasterFormat).type
    }
}