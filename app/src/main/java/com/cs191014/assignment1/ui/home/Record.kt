package com.cs191014.assignment1.ui.home

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Record(val id: Int, val name: String, val description: String?, val image: String?) : Serializable {

    companion object {

        fun createRecordList(numRecords: Int): ArrayList<Record> {
            val records = ArrayList<Record>()
            for (i in 1..numRecords) {
                records.add(
                    Record(
                        i+1,
                        "Record $i",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Quam nulla porttitor massa id neque aliquam vestibulum morbi. Etiam sit amet nisl purus in mollis nunc sed id. Leo in vitae turpis massa sed elementum tempus. Egestas dui id ornare arcu odio ut sem. Blandit libero volutpat sed cras ornare arcu. Ullamcorper sit amet risus nullam eget felis. Ornare aenean euismod elementum nisi. Nullam non nisi est sit. Ac turpis egestas integer eget aliquet nibh.",
                        "https://picsum.photos/${200+i}"
                    )
                )
            }
            return records
        }
    }
}