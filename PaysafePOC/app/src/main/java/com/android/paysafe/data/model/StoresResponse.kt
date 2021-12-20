package com.android.paysafe.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "stores")
data class StoresResponse(@field:ElementList(inline = true) @param:ElementList(inline = true) val stores: ArrayList<StoreModel> = arrayListOf())