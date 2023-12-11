package com.example.profinder

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper (context: Context): SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "ProFinder.db"

        //Table User Accounts
        private val TABLE_ACCOUNTS = "Accounts"
            //user details
            private val KEY_ACCOUNTS_ID = "accountsID"
            private val KEY_EMAIL = "email"
            private val KEY_USERNAME = "username"
            private val KEY_PASSWORD= "password"
            private val KEY_FIRSTNAME = "firstname"
            private val KEY_LASTNAME = "lastname"
            private val KEY_ACCTYPE = "accountType" //checker if company type acc or normal user

        //Table for all jobs in the app - ITO UNG DATING JOBSCREATED
        private val TABLE_JOBS = "Jobs"
            private const val KEY_JOB_ID = "jobDetailID"
            private const val KEY_ACC_ID_FK = "accountsID_FK" // Foreign Key to link with the Accounts tbl
            private const val KEY_DETAIL_TITLE = "jobDetailTitle"
            private const val KEY_DETAIL_OFFEROR = "jobDetailOfferor"
            private const val KEY_DETAIL_SALARY = "jobDetailSalary"
            private const val KEY_DETAIL_LOCATION = "jobDetailLoc"
            private const val KEY_DETAIL_STATUS = "jobDetailStatus"
            private const val KEY_DETAIL_JOBTYPE = "jobDetailType"
            private const val KEY_DETAIL_DATEPOSTED = "jobDatePosted"
            private const val KEY_DETAIL_JOBDESC = "jobDetailDesc"
            private const val KEY_DETAIL_JOBRESPONSIBILITIES = "jobDetailResponsib"
            private const val KEY_DETAIL_QUALIFICATIONS = "jobDetailQuali"
            private const val KEY_DETAIL_BENEFITS = "jobDetailsBenefits"

        //Kulang nalang is Applicants Table hehe (yung sa JobsPosted tsaka JobsApplied may iba na me naisip kung pano)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_ACCOUNTS_TABLE = ("CREATE TABLE " + TABLE_ACCOUNTS + "("
                +   KEY_ACCOUNTS_ID +   " INTEGER PRIMARY KEY autoincrement,"
                +   KEY_EMAIL +         " STRING,"
                +   KEY_USERNAME +      " STRING,"
                +   KEY_FIRSTNAME +     " STRING,"
                +   KEY_LASTNAME +      " STRING,"
                +   KEY_PASSWORD +       " STRING,"
                +   KEY_ACCTYPE +        " STRING)")
        db?.execSQL(CREATE_ACCOUNTS_TABLE)

        val CREATE_JOB_TABLE = ("CREATE TABLE " + TABLE_JOBS + "("
                +   KEY_JOB_ID +                        " INTEGER PRIMARY KEY autoincrement,"
                +   KEY_ACC_ID_FK +                     " INTEGER,"
                +   KEY_DETAIL_TITLE +                  " STRING,"
                +   KEY_DETAIL_OFFEROR +                " STRING,"
                +   KEY_DETAIL_SALARY +                 " STRING,"
                +   KEY_DETAIL_LOCATION +               " STRING,"
                +   KEY_DETAIL_STATUS +                 " STRING,"
                +   KEY_DETAIL_JOBTYPE +                " STRING,"
                +   KEY_DETAIL_JOBDESC +                " STRING,"
                +   KEY_DETAIL_JOBRESPONSIBILITIES +    " STRING,"
                +   KEY_DETAIL_QUALIFICATIONS +         " STRING,"
                +   KEY_DETAIL_BENEFITS +               " STRING,"
                +   "FOREIGN KEY($KEY_ACC_ID_FK) REFERENCES $TABLE_ACCOUNTS($KEY_ACCOUNTS_ID))")
        db?.execSQL(CREATE_JOB_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS + ", " + TABLE_JOBS)
        onCreate(db)
    }

    //ACCOUNT, LOGIN, SIGNUP FUNCTIONS
    fun addAccount(email: String, username: String, firstname: String, lastname: String, password: String, accType: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_EMAIL, email)
        contentValues.put(KEY_USERNAME, username)
        contentValues.put(KEY_FIRSTNAME, firstname)
        contentValues.put(KEY_LASTNAME, lastname)
        contentValues.put(KEY_PASSWORD, password)
        contentValues.put(KEY_ACCTYPE, accType)

        val result = db.insert(TABLE_ACCOUNTS, null, contentValues)
        db.close()
        return result
    }

    fun userLogin(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(KEY_USERNAME, KEY_PASSWORD)
        val selection = "$KEY_USERNAME = ? AND $KEY_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)

        try {
            val cursor: Cursor = db.query(TABLE_ACCOUNTS, columns, selection, selectionArgs, null, null, null)

            val count = cursor.count
            cursor.close()
            db.close()

            return count > 0
        } catch (e: SQLiteException) {
            e.printStackTrace()
            db.close()
            return false
        }
    }

    //CREATE, VIEW, UPDATE, DELETE JOBS
    fun insertJob(job: JobsDataClass) : Long{ //INSERT JOBS
        val db = this.writableDatabase
        val values = ContentValues().apply{
            put(KEY_ACC_ID_FK, job.userIdFK)
            put(KEY_DETAIL_TITLE, job.jobTitle)
            put(KEY_DETAIL_OFFEROR, job.offeror)
            put(KEY_DETAIL_SALARY, job.jobSalary)
            put(KEY_DETAIL_LOCATION, job.jobLoc)
            put(KEY_DETAIL_STATUS, job.jobStatus)
            put(KEY_DETAIL_JOBTYPE, job.jobType)
            put(KEY_DETAIL_JOBDESC, job.jobDesc)
            put(KEY_DETAIL_JOBRESPONSIBILITIES, job.jobResponsibility)
            put(KEY_DETAIL_QUALIFICATIONS, job.jobQualifications)
            put(KEY_DETAIL_BENEFITS, job.jobBenefits)
        }
        return db.insert(TABLE_JOBS, null, values)
    }

    // VIEW JOBS
    @SuppressLint("Range")
    fun viewAllJobs(): List<JobsDataClass> {
        val jobsList = mutableListOf<JobsDataClass>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_JOBS"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val job = JobsDataClass(
                    cursor.getInt(cursor.getColumnIndex(KEY_JOB_ID)),
                    cursor.getInt(cursor.getColumnIndex(KEY_ACC_ID_FK)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_TITLE)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_OFFEROR)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_SALARY)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_LOCATION)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_STATUS)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_JOBTYPE)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_JOBDESC)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_JOBRESPONSIBILITIES)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_QUALIFICATIONS)),
                    cursor.getString(cursor.getColumnIndex(KEY_DETAIL_BENEFITS))
                )
                jobsList.add(job)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return jobsList
    }

    /*fun updateJob(job: JobsDataClass): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_DETAIL_TITLE, job.jobTitle)
        contentValues.put(KEY_DETAIL_OFFEROR, job.offeror)
        contentValues.put(KEY_DETAIL_SALARY, job.jobSalary)
        contentValues.put(KEY_DETAIL_LOCATION, job.jobLoc)
        contentValues.put(KEY_DETAIL_JOBTYPE, job.jobType)
        contentValues.put(KEY_DETAIL_JOBDESC, job.jobDesc)
        contentValues.put(KEY_DETAIL_JOBRESPONSIBILITIES, job.jobResponsibility)
        contentValues.put(KEY_DETAIL_QUALIFICATIONS, job.jobQualifications)
        contentValues.put(KEY_DETAIL_BENEFITS, job.jobBenefits)


        val update = db.update(TABLE_JOBS, contentValues, "$KEY_JOB_ID=?", null)
        db.close()
        return update
    }*/

    //  UPDATE EXISTING JOBS
    /*fun updateJob(job: JobsDataClass) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_DETAIL_TITLE, job.jobTitle)
            put(KEY_DETAIL_OFFEROR, job.offeror)
            put(KEY_DETAIL_SALARY, job.jobSalary)
            put(KEY_DETAIL_LOCATION, job.jobLoc)
            put(KEY_DETAIL_JOBTYPE, job.jobType)
            put(KEY_DETAIL_JOBDESC, job.jobDesc)
            put(KEY_DETAIL_JOBRESPONSIBILITIES, job.jobResponsibility)
            put(KEY_DETAIL_QUALIFICATIONS, job.jobQualifications)
            put(KEY_DETAIL_BENEFITS, job.jobBenefits)
        }

        val whereClause = "$KEY_JOB_ID = ?"
        val whereArgs = arrayOf(job.jobId.toString())

        try {
            val rowsAffected = db.update(TABLE_JOBS, values, whereClause, whereArgs)
            Log.d(TAG, "Rows affected: $rowsAffected")
        } catch (e: Exception) {
            Log.e(TAG, "Error updating recipe", e)
        } finally {
            db.close()
        }
    }*/

    //RETRIEVE VALUES FROM DB PARA MAY LAMAN FIELDS SA EDIT
    /*fun getJobById(jobId : Int): JobsDataClass? {
        val db = readableDatabase
        val selection = "$KEY_JOB_ID = ?"
        val selectionArgs = arrayOf(jobId.toString())

        val cursor = db.query(TABLE_JOBS, null, selection, selectionArgs, null, null, null)

        var jobs: JobsDataClass? = null

        if (cursor.moveToFirst()) {
            jobs = JobsDataClass(
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_JOB_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ACC_ID_FK)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_TITLE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_OFFEROR)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_SALARY)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_LOCATION)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_JOBTYPE)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_JOBDESC)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_JOBRESPONSIBILITIES)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_QUALIFICATIONS)),
                cursor.getString(cursor.getColumnIndexOrThrow(KEY_DETAIL_BENEFITS))
            )
        }
        cursor.close()
        db.close()
        return jobs
    }*/


    //DELETE JOBS
    /*fun deleteJob(recipeId: Int): Int {
        return writableDatabase.use { db ->
            db.delete(
                TABLE_JOBS,
                "$KEY_JOB_ID = ?",
                arrayOf(recipeId.toString())
            )
        }
    }*/

}