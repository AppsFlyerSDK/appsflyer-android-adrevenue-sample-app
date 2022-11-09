package com.appsflyer.af_adrevenue.viewModel

import androidx.lifecycle.*
import com.appsflyer.af_adrevenue.data.AdTypeData
import com.appsflyer.af_adrevenue.R
import com.appsflyer.af_adrevenue.enums.AdNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerViewModel(adNetwork: AdNetwork) :
    ViewModel() {

    class Factory(adNetwork: AdNetwork) : ViewModelProvider.Factory {
        private val mAdNetwork: AdNetwork
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecyclerViewModel(mAdNetwork) as T
        }
        init {
            mAdNetwork = adNetwork
        }
    }

    private val adMobList = listOf(
        AdTypeData("Banner", R.drawable.banner),
        AdTypeData("Interstitial", R.drawable.socialadreach),
        AdTypeData("Interstitial video", R.drawable.youtube),
        AdTypeData("Rewarded", R.drawable.socialmedia),
        AdTypeData("Rewarded interstitial", R.drawable.advertising),
        AdTypeData("Native ad", R.drawable.ads),
    )
    private val ironSourceList = listOf(
        AdTypeData("Banner", R.drawable.banner),
        AdTypeData("Interstitial", R.drawable.socialadreach),
        AdTypeData("Interstitial video", R.drawable.youtube),
    )

     val adTypesData: MutableLiveData<List<AdTypeData>> by lazy {
        MutableLiveData<List<AdTypeData>>().also {
            loadAdTypes(adNetwork)
        }
    }

    private fun loadAdTypes(adNetwork: AdNetwork) {
        viewModelScope.launch(Dispatchers.IO) {
            val adTypesAdList = if (adNetwork == AdNetwork.ADMOB) {
                adMobList
            } else {
                ironSourceList
            }
            adTypesData.postValue(adTypesAdList)
        }
    }
}