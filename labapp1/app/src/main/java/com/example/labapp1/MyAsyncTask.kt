package com.example.labapp1


import android.os.AsyncTask
import android.widget.TextView


class MyAsyncTask() : AsyncTask<MainActivity, Int, Int>() {
    var activity: MainActivity? = null

    override fun doInBackground(vararg argactivity: MainActivity?): Int {
        activity = argactivity[0]!!
        val textView: TextView = activity!!.findViewById(R.id.textView)
        val iters: Int = activity!!.valueSet
        for (i in 1 .. iters){
            Thread.sleep(500)
            publishProgress(i)
            if(isCancelled()) {
                break
            }
        }
        return 0
    }


    override fun onCancelled(result: Int?) {
        super.onCancelled(result)
        activity!!.task = null
        val textView: TextView = activity!!.findViewById(R.id.textView)
        textView.text = "Cancelled"
    }

    override fun onPostExecute(result: Int?) {
        activity!!.task = null
        val textView: TextView = activity!!.findViewById(R.id.textView)
        textView.text = "Done"
        super.onPostExecute(result)
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onProgressUpdate(vararg values: Int?) {
        val textView: TextView = activity!!.findViewById(R.id.textView)
        textView.text = values[0]!!.toString()
        super.onProgressUpdate(*values)
    }
}