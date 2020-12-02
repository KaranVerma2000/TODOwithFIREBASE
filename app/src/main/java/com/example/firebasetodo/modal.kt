package com.example.firebasetodo

import java.io.Serializable

class modal(val id : String, val title : String, val desc: String) : Serializable
{
    constructor() : this("","","")
}