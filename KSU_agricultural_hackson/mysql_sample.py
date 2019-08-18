#!/usr/bin/python 
import  MySQLdb
import os 
import shutil

db  =  MySQLdb . connect ( 
#    host = "" ,
    host = "", 
    user = "" ,         
    passwd = "" ,  
    db = ""
) 
sql_str = "SELECT * FROM TABLE"
cur.execute(sql_str)
for row in cur.fetchall():
    print (row)
