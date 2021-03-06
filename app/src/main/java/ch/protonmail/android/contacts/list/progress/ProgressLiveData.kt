/*
 * Copyright (c) 2020 Proton Technologies AG
 * 
 * This file is part of ProtonMail.
 * 
 * ProtonMail is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ProtonMail is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ProtonMail. If not, see https://www.gnu.org/licenses/.
 */
package ch.protonmail.android.contacts.list.progress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ch.protonmail.android.contacts.list.progress.ProgressState

class ProgressLiveData(progressLiveData:LiveData<Int?>,progressMaxLiveData:LiveData<Int?>):MediatorLiveData<ProgressState?>(){

	var progress:Int?=null
	var progressMax:Int?=null

	init {
		addSource(progressLiveData){
			progress=it
			emit()
		}
		addSource(progressMaxLiveData) {
			progressMax=it
			emit()
		}
	}

	fun emit(){
		val progress=progress
		val progressMax=progressMax
		value=if(progress==null||progressMax==null){
			null
		}else{
			ProgressState(progress,
					progressMax)
		}
	}
}