package solution.codebox.hotkeyword.api

import android.os.AsyncTask
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class KeywordApi {
    companion object {
        private val KEY_WORD_URL = "https://gist.githubusercontent.com/talenguyen/38b790795722e7d7b1b5db051c5786e5/raw/63380022f5f0c9a100f51a1e30887ca494c3326e/keywords.json"

        interface FetchDataListener<T>{
            fun onResult(result: T)
        }

        fun fetchKeyword(fetchDataListener: FetchDataListener<ArrayList<String>?>){
            KeywordAsyncTask(object : KeywordAsyncTask.Listener{
                override fun onResult(keywords: ArrayList<String>?) {
                    fetchDataListener.onResult(keywords)
                }
            }).execute()
        }

        private class KeywordAsyncTask(val listener: Listener) : AsyncTask<Void, Void, ArrayList<String>?>() {
            interface Listener {
                fun onResult(keywords: ArrayList<String>?)
            }

            override fun doInBackground(vararg params: Void?): ArrayList<String>? {
                var urlConnection: HttpURLConnection? = null
                var reader: BufferedReader? = null

                var keywordJSONString: String? = null
                try {
                    val requestURL = URL(KEY_WORD_URL)
                    urlConnection = requestURL.openConnection() as HttpURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.connect()

                    val inputStream = urlConnection.inputStream
                    val stringBuffer = StringBuffer()
                    if (null == inputStream) {
                        return null
                    }

                    reader = BufferedReader(InputStreamReader(inputStream))
                    var line = reader.readLine()
                    while (null != line) {
                        stringBuffer.append(line + "\n")
                        line = reader.readLine()
                    }

                    if (stringBuffer.isEmpty()) return null
                    keywordJSONString = stringBuffer.toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    return null
                } finally {
                    urlConnection?.disconnect()
                    if (null != reader) {
                        try {
                            reader.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
                return if (null != keywordJSONString)
                    convertFromArrayStringToStringArray(keywordJSONString)
                else
                    null
            }

            override fun onPostExecute(result: ArrayList<String>?) {
                listener.onResult(result)
            }

        }

        private fun convertFromArrayStringToStringArray(arrayString: String): ArrayList<String>? {
            val stringArray = ArrayList<String>()

            val jsonArray = JSONArray(arrayString)

            for (i in 0 until jsonArray.length()) {
                stringArray.add(jsonArray.getString(i))
            }

            return stringArray
        }
    }
}