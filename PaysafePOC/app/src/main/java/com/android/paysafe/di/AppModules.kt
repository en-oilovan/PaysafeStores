package com.android.paysafe.di

import com.android.paysafe.BuildConfig
import com.android.paysafe.utils.TIMEOUT
import com.android.paysafe.data.StoreApi
import com.android.paysafe.data.StoreRepository
import com.android.paysafe.ui.stores.StoreListViewModel
import com.android.paysafe.utils.LocationManager
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object AppModules {

    private val viewModelModule = module {
        viewModel { StoreListViewModel(get(), get()) }
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

        val certificatePinner = CertificatePinner.Builder()
            .add(
                "www.paysafecard.com",
                "sha256/OAt/zfrM/X/ZDXhpHPU6i/ZZd0fZAQKVqpZSmRdg7V4=",
                "sha256/yMKGNy9ETFo1MzQT5X95eJfzTcthHykS+MbEdkLLZyg=",
                "sha256/4RqkrIrErrVo/xOhdRj/PH+oayfIHW0q+GDLMu1KtEg=",
                "sha256/XbvMTGE+o0q2THhvRYZcx4ewplxh/VuVsYBcljBiiqE=",
                "sha256/kGf17cbOkRz2/gJG7neCH/EmAsq740gtqzx5Rmgn9wI=",
                "sha256/l48xflikR+ED5yb5fNCQP1o+p4c15U7YDTuj35pj39I=",
                "sha256/0enbD8qdYHHaavFafUYuyzoEzyAWODJyUDXpXpKYVBk="
            ).build()

        fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
            OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        fun provideRetrofit(client: OkHttpClient): Retrofit {
            val scheduler = Schedulers.from(Executors.newCachedThreadPool())
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(scheduler))
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .build()
        }

        single { provideHttpLoggingInterceptor() }
        single { provideOkHttpClient(get()) }
        single { provideRetrofit(get()) }
    }

    private val locationModule = module {
        single { LocationManager(androidApplication()) }
    }

    val appModules = listOf(viewModelModule, repositoryModule, apiModule, retrofitModule, locationModule)
}