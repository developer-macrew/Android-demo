package com.example.dashboard.utils

import android.media.session.MediaSession.Token
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class AddHeaderInterceptor : Interceptor {

    private val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiN2I3ZGMyMmNmNmVmMzY4YTkxODdjNjUyMTc5NzM4ODdlYzIzZjU1MmY3M2I4OGVhNzVlMGU3MjNhY2E5NDgzYmM4YzdmNTUxMzBhZDZhNTgiLCJpYXQiOjE2OTEwNjMxNzQsIm5iZiI6MTY5MTA2MzE3NCwiZXhwIjoxNzIyNjg1NTc0LCJzdWIiOiIxMzIiLCJzY29wZXMiOltdfQ.DYcvC8EMN7N_pA2JZ1DktV6HBUP9TMkEGZBqtHGQcioyGNX68X8BzD_TvB_KWes5X0eAecI4fvtUnRnI67_Y1FHozeG8PwskZ1C_06lhR2saWDeicx30DHjaUd6l1nn0xUxNO8Vy0WTilBQKdeLv6ack98nBXlOL1mCVd-lgE-RZvEwxG1E8C9dUFxJkNy1dOkvFy36OjZro7XnGsOWLGIG7XOun-wbPJn5cyQRWa4H2MD98bOrR-V611HtBcV24yHb5OaMs_qfOT1b5r2OnjH0FTdVdUUlO3qGamsMP0nx9ACqHKcxocsVxgcCBDs8BLLD_OHWcXAIMrPG0KWm6VMOl0OwaM5htuub3Rosbr2jwN3MLPMrhy9MAvPeMKtWtGc1aOUllpBWbYoY-p-67ESSI4WRozO9tgukErWBhbpWVcLk3P6VzuHTY-IzaGvUsqRVtmdJcgQI78JzMDrytYWVjxPOBEUJ4jyKaI9FP7ZrcUuOG5zVOpQRMjrh2bZJe4AawqiKz1nQJZBHO7UencP107IijbGNrk4QPjBeIDgOiD7piGt5ZDM-XoifOyd96h15xrTdIs6rz-kKAPyKmSHNDHraZZEJbZY8F2Q15hcIic7oPHKKZG7IDalU54MMj0f8Y3X1gLnWD-xMazbFIbpEv3c3R25J6F5tOMyFkOHg"
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //Basic Auth
        val builder = chain.request().newBuilder()
        //builder.addHeader(Const.AUTH, Const.AUTH_TOKEN)
        //builder.addHeader(Const.AUTH, "Basic dW5pZXZlbnRhcHA6R1NiOSF4dC43W0xYKmM4OU9PRA==")
        builder.addHeader("Authorization", "Bearer $token")
        return chain.proceed(builder.build())
    }
}