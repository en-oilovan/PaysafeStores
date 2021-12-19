package com.android.paysafe.di

import com.android.paysafe.BuildConfig
import com.android.paysafe.utils.SERVER_DATE_FORMAT
import com.android.paysafe.utils.TIMEOUT
import com.android.paysafe.remote.StoreApi
import com.android.paysafe.remote.StoreRepository
import com.android.paysafe.ui.stores.StoreListViewModel
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object AppModules {

    private val viewModelModule = module {
        viewModel { StoreListViewModel(get()) }
    }

    private val repositoryModule = module {
        single {
            StoreRepository(get())
        }
    }

    private val apiModule = module {
        fun provideStoreApi(retrofit: Retrofit) = retrofit.create(StoreApi::class.java)

        single { provideStoreApi(get()) }
    }

    private val retrofitModule: Module = module {
        fun provideHttpLoggingInterceptor() =
            HttpLoggingInterceptor { Timber.d(it) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        fun provideRetrofit(client: OkHttpClient): Retrofit {
            val scheduler = Schedulers.from(Executors.newCachedThreadPool())
            val gson = GsonBuilder().setDateFormat(SERVER_DATE_FORMAT).create()

            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(scheduler))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build()
        }

        //single { AuthHeaderInterceptor(get()) }
        single { provideHttpLoggingInterceptor() }
        single { provideOkHttpClient(get()) }
        single { provideRetrofit(get()) }
    }

    val appModules = listOf(viewModelModule, repositoryModule, apiModule, retrofitModule)
}