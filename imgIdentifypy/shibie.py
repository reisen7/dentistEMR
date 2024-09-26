import cv2
import numpy as np
import os
import sys
import pymysql


def getlocalConnection():
    conn = pymysql.connect(host="localhost", user="root", passwd="root", port=3306, db="doctorkillpatient", charset="utf8")
    return conn


def getAidByPid(paccount):
    conn = getlocalConnection()
    cursor = conn.cursor()
    sql = "select * from adminuser where id = %s "
    cursor.execute(sql,(paccount,))
    conn.commit()
    data = cursor.fetchall()
    cursor.close()
    conn.close()
    print(data)
    if len(data)>0:
        # if data[0][8] == 321:
            One = True
        # else:
        #     One = bool("")
    # else :
        # One = bool ("")
    return One

# 加载训练数据集
recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read('D:/Python_WorkSpace/imgIdentify/trainer.yml')
# 准备识别的图片
# img=cv2.imread('data/kobe.6.jpeg')
# img=cv2.imread('images/31.jpg')
cap = cv2.VideoCapture(0)
# 循环读取摄像头
while cap.isOpened():
    # 读取摄像头的当前帧
    flag, img = cap.read()
    # 显示
    cv2.imshow("pic", img)
    # 当按下回车的时候保存图片
    if ord('\r') == cv2.waitKey(1) & 0xFF:
        cv2.destroyWindow("pic")
        break

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
haar = "D:/Python_WorkSpace/imgIdentify/haarcascade_frontalface_default.xml"
# 加载特征数据
face_detector = cv2.CascadeClassifier(haar)
faces = face_detector.detectMultiScale(gray)
account, confidence = 0, 0
for x, y, w, h in faces:
    cv2.rectangle(img, (x, y, w, h), color=(255, 0, 0), thickness=2)
    # 人脸识别
    account, confidence = recognizer.predict(gray[y:y + h, x:x + w])
print(account)
aaccount = getAidByPid(account)
print(aaccount)
# 显示
cv2.imshow('result', img)
# 等待键盘输入
cv2.waitKey(0)
# 释放内存
cv2.destroyAllWindows()
# 释放所有资源
cap.release()
