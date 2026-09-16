package org.turkiye.offlinechat.util

/**
 * Created by ferhat.ozcelik on 12-02-2023.
 */

object Constants {

    const val SERVICE_TYPE = "_http._tcp."
    const val PORT = 55555

    /**
     * Base URL used by the (optional) REST layer. Replace with a real endpoint when
     * the HTTP API is enabled; Retrofit requires the trailing slash.
     */
    const val BASE_URL = "http://localhost/"
}