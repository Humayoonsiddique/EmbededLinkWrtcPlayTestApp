package com.example.embededlinkwrtcplaytestapp.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var streaminUrlWitHtmlLiveData = MutableLiveData<String>("")
    lateinit var text:String


    fun setLiveData (embededString: String){

        //preparing webview data

        text = "<html ><body dir=\"rtl\"; style=\"text-align:justify; width: '100%'\">"//use for justify your text
        text += embededString
        text += "</body></html>"
        streaminUrlWitHtmlLiveData.value = text

    }
}