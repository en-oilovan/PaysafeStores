package com.android.paysafe.data.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "store")
data class StoreModel(
    @field:Element(name = "posId")
    var posId: String = "",
    @field:Element(name = "name")
    var name: String = "",
    @field:Element(name = "address")
    var address: String = "",
    @field:Element(name = "latitude")
    var latitude: Double = 0.0,
    @field:Element(name = "longitude")
    var longitude: Double = 0.0,
    @field:Element(name = "postalCode")
    var postalCode: String = "",
    @field:Element(name = "city")
    var city: String = "",
    @field:Element(name = "distributorId")
    var distributorId: String = "",
    @field:Element(name = "country")
    var country: String = "",
    @field:Element(name = "posTypeLogo", required = false)
    var posTypeLogo: String? = "",
    @field:Element(name = "productLogo")
    var productLogo: String = "",
    @field:Element(name = "specialText", required = false)
    var specialText: String? = "",
    @field:Element(name = "recommended")
    var recommended: Boolean = false,
    @field:Element(name = "directload")
    var directload: Boolean = false,
    @field:Element(name = "mdirectload")
    var mdirectload: Boolean = false
)